package uz.pdp.librarysystem.service.bookingService;

import uz.pdp.librarysystem.dto.createDto.BookingCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookingResponseDto;
import uz.pdp.librarysystem.entities.BookingEntity;
import uz.pdp.librarysystem.entities.enums.BookStatus;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    String save(BookingCreateDto dto);
    List<BookingResponseDto> viewBooks(Integer page, Integer size);

    void delete();
    List<BookingEntity> checkSubmissionDate();

    void cancelBookingOfUser(UUID userId, UUID bookingId);

    List<BookingResponseDto> bookingOfUser(UUID uuid, int page, int size);


    String updateStatus(UUID bookingId, BookStatus bookStatus);
}





