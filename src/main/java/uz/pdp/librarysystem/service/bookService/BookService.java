package uz.pdp.librarysystem.service.bookService;

import jakarta.persistence.criteria.CriteriaBuilder;
import uz.pdp.librarysystem.dto.createDto.BookCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookResponseDto;
import uz.pdp.librarysystem.repository.BookRepository;

import java.util.List;
import java.util.UUID;

public interface BookService {
    String save(BookCreateDto dto);
    String delete(UUID bookId);
    List<BookResponseDto> getAll(Integer page, Integer size);
    BookResponseDto getById(UUID bookId);
    List<BookResponseDto> search(String word, Integer page, Integer size);
}
