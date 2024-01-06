package uz.pdp.librarysystem.service.floorService;

import uz.pdp.librarysystem.dto.createDto.ClosetCreateDto;
import uz.pdp.librarysystem.dto.createDto.FloorCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.dto.responseDto.FloorResponseDto;

import java.util.List;
import java.util.UUID;

public interface FloorService {
    String save(FloorCreateDto dto);
    String remove(UUID floorId);
    List<FloorResponseDto> getAll(Integer page, Integer size);
    FloorResponseDto getById(UUID floorId);
    UUID findByFloor(Integer floorNumber);
}
