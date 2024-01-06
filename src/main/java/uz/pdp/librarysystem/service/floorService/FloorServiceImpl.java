package uz.pdp.librarysystem.service.floorService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.FloorCreateDto;
import uz.pdp.librarysystem.dto.responseDto.FloorResponseDto;
import uz.pdp.librarysystem.entities.ClosetEntity;
import uz.pdp.librarysystem.entities.FloorEntity;
import uz.pdp.librarysystem.entities.ShelfEntity;
import uz.pdp.librarysystem.exception.DataAlreadyExistsException;
import uz.pdp.librarysystem.exception.DataNotFoundException;
import uz.pdp.librarysystem.repository.FloorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService{
    private final FloorRepository floorRepository;
    @Override
    public String save(FloorCreateDto dto) {
        Boolean aBoolean = floorRepository.existsAllByNumber(dto.getNumber());
        if (aBoolean){
            throw  new DataAlreadyExistsException("This number already exists");
        }
        floorRepository.save(new FloorEntity(dto.getNumber()));
        return "Successfully";
    }

    @Override
    public String remove(UUID floorId) {
        FloorEntity floorEntity = floorRepository.findById(floorId).orElseThrow(() -> new DataNotFoundException("Floor not found"));
        floorEntity.setIsActive(false);
        floorRepository.save(floorEntity);
        return "Removed";
    }

    @Override
    public List<FloorResponseDto> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<FloorEntity> floor = floorRepository.findAllByIsActiveTrue(pageRequest);
        List<FloorEntity> content = floor.getContent();
        return parse(content);
    }

    @Override
    public FloorResponseDto getById(UUID floorId) {
        FloorEntity floorEntity = floorRepository.findById(floorId).orElseThrow(() -> new DataNotFoundException("Floor not found"));
        return parse(floorEntity);
    }

    @Override
    public UUID findByFloor(Integer floorNumber) {
        FloorEntity floorEntity = floorRepository.findByNumber(floorNumber).orElseThrow(() -> new DataNotFoundException("Data not found"));
        return floorEntity.getId();
    }


    private FloorResponseDto parse(FloorEntity floorEntity){
        return new FloorResponseDto(floorEntity.getId(), floorEntity.getNumber()) ;
    }

    private List<FloorResponseDto> parse(List<FloorEntity> floorEntities){
        List<FloorResponseDto> list = new ArrayList<>();
        for (FloorEntity floorEntity : floorEntities) {
            list.add(new FloorResponseDto(floorEntity.getId(), floorEntity.getNumber()));
        }
        return list;
    }


}
