package uz.pdp.librarysystem.service.bookShelfService;

import uz.pdp.librarysystem.dto.createDto.BookShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookAllDto;

import java.util.List;
import java.util.UUID;

public interface BookShelfService {
    String bookPlacement(BookShelfCreateDto dto);

    List<BookAllDto> getBookInfo(UUID id);

    String remove(BookShelfCreateDto dto);
}
