package uz.pdp.librarysystem.service.shelfService;

import uz.pdp.librarysystem.dto.createDto.ClosetCreateDto;
import uz.pdp.librarysystem.dto.createDto.ShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.dto.responseDto.ShelfResponseDto;

import java.util.List;
import java.util.UUID;

public interface ShelfService {
    String save(ShelfCreateDto dto);
    String remove(UUID shelfId);
    List<ShelfResponseDto> getAll(Integer page, Integer size);
    ShelfResponseDto getById(UUID shelfId);

}
