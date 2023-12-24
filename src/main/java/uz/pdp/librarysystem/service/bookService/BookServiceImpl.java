package uz.pdp.librarysystem.service.bookService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.BookCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookAllDto;
import uz.pdp.librarysystem.dto.responseDto.BookResponseDto;
import uz.pdp.librarysystem.entities.BookEntity;
import uz.pdp.librarysystem.exception.DataNotFoundException;
import uz.pdp.librarysystem.repository.BookRepository;
import uz.pdp.librarysystem.service.bookShelfService.BookShelfService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookShelfService bookShelfService;
    @Override
    public String save(BookCreateDto dto) {
        Optional<BookEntity> book = bookRepository.getBookEntitiesByAuthorAndName(dto.getAuthor(), dto.getName());
        if (book.isPresent()){
            BookEntity bookEntity = book.get();
            bookEntity.setNowCount(bookEntity.getNowCount() + dto.getCount());
            bookEntity.setOldCount(bookEntity.getOldCount() + dto.getCount());
            bookRepository.save(bookEntity);
        }else {
            BookEntity bookEntity = new BookEntity(dto.getName(), dto.getAuthor(), dto.getYearOfWriting(), dto.getCount(), dto.getCount());
            bookRepository.save(bookEntity);
        }
        return "Successfully";
    }

    @Override
    public String delete(UUID bookId) {
          BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        bookEntity.setIsActive(false);
        bookRepository.save(bookEntity);
        return "Book deleted";
    }

    @Override
    public List<BookResponseDto> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BookEntity> allByIsActiveTrue = bookRepository.findAllByIsActiveTrue(pageRequest);
        List<BookEntity> content = allByIsActiveTrue.getContent();
        return parse(content);
    }

    @Override
    public BookResponseDto getById(UUID bookId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException("Book not found"));
        return parse(book);
    }

    @Override
    public List<BookResponseDto> search(String word, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<BookEntity> bookEntities = bookRepository.searchBook(word, pageRequest);
        return parse(bookEntities.getContent());
    }

    private BookResponseDto parse(BookEntity bk){
        List<BookAllDto> dtos = bookShelfService.getBookInfo(bk.getId());
        return new BookResponseDto(bk.getId(), bk.getName(), bk.getAuthor(), bk.getNowCount(), bk.getOldCount(), bk.getYearOfWriting(), bk.getCreatedDate(), dtos);
    }

    private List<BookResponseDto> parse(List<BookEntity> bookEntities) {
        List<BookResponseDto> list = new ArrayList<>();
        for (BookEntity bk : bookEntities) {
            List<BookAllDto> dtos = bookShelfService.getBookInfo(bk.getId());
            list.add(new BookResponseDto(bk.getId(), bk.getName(), bk.getAuthor(), bk.getNowCount(), bk.getOldCount(), bk.getYearOfWriting(), bk.getCreatedDate(), dtos));
        }
        return list;
    }
}
