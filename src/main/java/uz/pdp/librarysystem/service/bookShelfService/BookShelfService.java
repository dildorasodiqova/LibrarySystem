package uz.pdp.librarysystem.service.bookShelfService;

import uz.pdp.librarysystem.dto.createDto.BookShelfCreateDto;

public interface BookShelfService {
    String bookPlacement(BookShelfCreateDto dto);
}
