package uz.pdp.librarysystem.service.shelfService;

import uz.pdp.librarysystem.dto.createDto.ShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ShelfResponseDto;
import uz.pdp.librarysystem.entities.ClosetEntity;

import java.util.List;
import java.util.UUID;

public interface ShelfService {
    String save(ShelfCreateDto dto);
    String remove(UUID shelfId);
    List<ShelfResponseDto> getAll(Integer page, Integer size);
    ShelfResponseDto getById(UUID shelfId);

    List<ShelfResponseDto> getShelfOfCloses(Integer floorNumber);

    List<ShelfResponseDto> getFreePlace(List<ClosetEntity> closes);


}
