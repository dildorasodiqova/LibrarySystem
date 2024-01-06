package uz.pdp.librarysystem.service.closetService;

import uz.pdp.librarysystem.dto.createDto.ClosetCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.entities.ClosetEntity;

import java.util.List;
import java.util.UUID;

public interface ClosetService {
    String save(ClosetCreateDto dto);
    String remove(UUID closetId);
    List<ClosetResponseDto> getAll(Integer page, Integer size);
    ClosetResponseDto getById(UUID closetId);
    List<ClosetEntity> getCloses(Integer floorNumber);


}
