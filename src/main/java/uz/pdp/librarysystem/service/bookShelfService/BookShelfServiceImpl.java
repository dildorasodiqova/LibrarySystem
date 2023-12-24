package uz.pdp.librarysystem.service.bookShelfService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.BookShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.*;
import uz.pdp.librarysystem.entities.BookEntity;
import uz.pdp.librarysystem.entities.BookShelfEntity;
import uz.pdp.librarysystem.exception.BadRequestException;
import uz.pdp.librarysystem.exception.DataNotFoundException;
import uz.pdp.librarysystem.exception.ExceededLimitException;
import uz.pdp.librarysystem.repository.BookRepository;
import uz.pdp.librarysystem.repository.BookShelfRepository;
import uz.pdp.librarysystem.service.bookService.BookService;
import uz.pdp.librarysystem.service.closetService.ClosetService;
import uz.pdp.librarysystem.service.floorService.FloorService;
import uz.pdp.librarysystem.service.shelfService.ShelfService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookShelfServiceImpl implements BookShelfService{
    private final BookShelfRepository bookShelfRepository;
    private final ShelfService shelfService;
    private final ClosetService closetService;
    private final FloorService floorService;
    private final BookRepository bookRepository;

    @Override
    public String bookPlacement(BookShelfCreateDto dto) {
        ShelfResponseDto shelf = shelfService.getById(dto.getShelfId());

        Optional<BookEntity> books = bookRepository.findById(dto.getBookId());
        BookEntity book = books.get();
        if (shelf.getCountOfBook() >= 20){
            throw  new ExceededLimitException("The shelf is full of books");
        }else if (shelf.getCountOfBook()+ dto.getCount() > 20){
            int i = 20 - (shelf.getCountOfBook() + dto.getCount());
            int j = shelf.getCountOfBook() + dto.getCount() - 20;
            throw new ExceededLimitException("You can put "+ i +" books on this shelf. \n" + j + "put your book on another shelf.");
        } else if (book.getNowCount() <= dto.getCount()) {
            throw new ExceededLimitException("There aren't enough books to put on the shelf here");
        }
        bookShelfRepository.save(new BookShelfEntity(dto.getBookId(), dto.getShelfId(), dto.getCount(), dto.getClosetId(), dto.getFloorId()));
        return "Successfully";
    }

    @Override
    public List<BookAllDto> getBookInfo(UUID bookId) {
        List<BookAllDto> list = new ArrayList<>();
        List<BookShelfEntity> all = bookShelfRepository.findAllByBookIdAndIsActiveTrue(bookId);
        for (BookShelfEntity entity : all) {
            ShelfResponseDto shelf = shelfService.getById(entity.getShelfId());
            ClosetResponseDto closet = closetService.getById(entity.getClosetId());
            FloorResponseDto floor = floorService.getById(entity.getFloorId());

            list.add(new BookAllDto(shelf,closet, floor, entity.getCount()));
        }
        return list;
    }

    @Override
    public String remove(BookShelfCreateDto dto) {
        BookShelfEntity book = bookShelfRepository.findAllByBookIdAndShelfId(dto.getBookId(), dto.getShelfId()).orElseThrow(() -> new DataNotFoundException("This book is not exists"));
        if (book.getCount() >= dto.getCount()){
            bookShelfRepository.updateBookCount(book.getCount() - dto.getCount(), book.getId());
            return "Successfully";
        }else {
            throw  new BadRequestException("There is not enough book to remove here");
        }
    }
}
