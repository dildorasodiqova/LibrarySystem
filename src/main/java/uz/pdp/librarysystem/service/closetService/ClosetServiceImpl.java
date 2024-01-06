package uz.pdp.librarysystem.service.closetService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.ClosetCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.dto.responseDto.FloorResponseDto;
import uz.pdp.librarysystem.dto.responseDto.ShelfResponseDto;
import uz.pdp.librarysystem.entities.ClosetEntity;
import uz.pdp.librarysystem.entities.FloorEntity;
import uz.pdp.librarysystem.exception.DataAlreadyExistsException;
import uz.pdp.librarysystem.exception.DataNotFoundException;
import uz.pdp.librarysystem.repository.ClosetRepository;
import uz.pdp.librarysystem.repository.FloorRepository;
import uz.pdp.librarysystem.service.floorService.FloorService;
import uz.pdp.librarysystem.service.shelfService.ShelfService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor /// shkaf
public class ClosetServiceImpl implements ClosetService{
    private final ClosetRepository closetRepository;
    private final FloorService floorService;
    private final ShelfService shelfService;

    @Override
    public String save(ClosetCreateDto dto) {
        Boolean aBoolean = closetRepository.existsAllByCode(dto.getCode());
        if (aBoolean){
            throw  new DataAlreadyExistsException("This code already exists");
        }
        closetRepository.save(new ClosetEntity(dto.getFloorId(), dto.getCode()));
        return "Successfully";
    }

    @Override
    public String remove(UUID closetId) {
        ClosetEntity closetEntity = closetRepository.findById(closetId).orElseThrow(() -> new DataNotFoundException("Floor not found"));
        closetEntity.setIsActive(false);
        closetRepository.save(closetEntity);
        return "Removed";
    }

    @Override
    public List<ClosetResponseDto> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ClosetEntity> closet = closetRepository.findAllByIsActiveTrue(pageRequest);
        List<ClosetEntity> content = closet.getContent();
        return parse(content);
    }

    @Override
    public ClosetResponseDto getById(UUID closetId) {
        ClosetEntity closetEntity = closetRepository.findById(closetId).orElseThrow(() -> new DataNotFoundException("Floor not found"));
        return parse(closetEntity);
    }

    /**
     *bu methodga bitta qavat raqami kirib keladi . osha qavatning shkaflarini topib keladi.
     * @param floorNumber
     * @return
     */
    @Override
    public List<ClosetEntity> getCloses(Integer floorNumber) {
        UUID floor = floorService.findByFloor(floorNumber);
        return closetRepository.findAllByFloorId(floor);
    }

    private List<ClosetResponseDto> parse(List<ClosetEntity> entities){
        List<ClosetResponseDto> list = new ArrayList<>();
        for (ClosetEntity entity : entities) {
            list.add(new ClosetResponseDto(entity.getId(), entity.getFloorId(), entity.getCode()));
        }
        return list;
    }

    private ClosetResponseDto parse(ClosetEntity entity){
        return new ClosetResponseDto(entity.getId(), entity.getFloorId(), entity.getCode());
    }
}
