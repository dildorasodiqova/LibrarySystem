package uz.pdp.librarysystem.service.bookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.BookingCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookResponseDto;
import uz.pdp.librarysystem.dto.responseDto.BookingResponseDto;
import uz.pdp.librarysystem.dto.responseDto.UserResponseDto;
import uz.pdp.librarysystem.entities.BookEntity;
import uz.pdp.librarysystem.entities.BookingEntity;
import uz.pdp.librarysystem.entities.UserEntity;
import uz.pdp.librarysystem.entities.enums.BookStatus;
import uz.pdp.librarysystem.exception.DataNotFoundException;
import uz.pdp.librarysystem.repository.BookRepository;
import uz.pdp.librarysystem.repository.BookingRepository;
import uz.pdp.librarysystem.repository.UserRepository;
import uz.pdp.librarysystem.service.bookService.BookService;
import uz.pdp.librarysystem.service.userService.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final BookService bookService;

    /**
     * bu method faqat band qilib qo'yadi
     *
     * @param dto
     * @return
     */
    @Override
    public String save(BookingCreateDto dto) {
        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new DataNotFoundException("User not found"));
        BookEntity book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new DataNotFoundException("Book not found"));
        bookingRepository.save(new BookingEntity(user, book, LocalDate.now().plusDays(3), BookStatus.CREATED));
        return "Successfully";
    }


    /**
     * bu method userlar bron qilgan yoki olib ketgan kitoblar ro'yhatini ko'rib turadi
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<BookingResponseDto> viewBooks(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BookingEntity> allByIsActiveTrue = bookingRepository.findAllByIsActiveTrue(pageRequest);
        List<BookingEntity> content = allByIsActiveTrue.getContent();
        return parse(content);
    }

    @Override
    public void delete() {
        bookingRepository.remove(LocalDate.now());
    }

    /**
     * Bu method  bron qilingan kitoblar qaytarilmasa o'sha userlarnbing emailiga habar jo'natadi
     * @return
     */
    @Override
    public List<BookingEntity> checkSubmissionDate() {
        return bookingRepository.checkSubmissionDate(LocalDate.now());
    }

    @Override
    public void cancelBookingOfUser(UUID userId, UUID bookingId) {
        bookingRepository.cancelBookingOfUser(userId, bookingId);
    }

    @Override
    public List<BookingResponseDto> bookingOfUser(UUID userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<BookingEntity> all = bookingRepository.findAllByUserId(userId, pageRequest);
        return parse(all);
    }

    @Override
    public String updateStatus(UUID userId, UUID bookingId, BookStatus bookStatus) {
        bookingRepository.updateStatus(userId, bookingId, bookStatus);
        return "successfully";
    }


    private List<BookingResponseDto> parse(List<BookingEntity> bookings) {
        List<BookingResponseDto> list = new ArrayList<>();
        for (BookingEntity booking : bookings) {
            UserResponseDto user = userService.getById(booking.getUser().getId());
            BookResponseDto book = bookService.getById(booking.getBook().getId());
            list.add(new BookingResponseDto(booking.getId(), user, book, booking.getCreatedDate().toLocalDate(), booking.getSubmissionDate()));
        }
        return list;
    }
}
