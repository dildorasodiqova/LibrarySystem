package uz.pdp.librarysystem.service.shelfService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.ShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.dto.responseDto.FloorResponseDto;
import uz.pdp.librarysystem.dto.responseDto.ShelfResponseDto;
import uz.pdp.librarysystem.entities.ClosetEntity;
import uz.pdp.librarysystem.entities.ShelfEntity;
import uz.pdp.librarysystem.exception.DataAlreadyExistsException;
import uz.pdp.librarysystem.exception.DataNotFoundException;
import uz.pdp.librarysystem.repository.ShelfRepository;
import uz.pdp.librarysystem.service.closetService.ClosetService;
import uz.pdp.librarysystem.service.floorService.FloorService;

import java.util.*;

@Service
@RequiredArgsConstructor ///this is tokcha
public class ShelfServiceImpl implements ShelfService{
    private final ShelfRepository shelfRepository;
    private final ClosetService closetService ;
    private final FloorService floorService;

    @Override
    public String save(ShelfCreateDto dto) {
        Optional<ShelfEntity> all = shelfRepository.findAllByClosetIdAndRowNumber(dto.getClosetId(), dto.getRowNumber());
        if (all.isEmpty()){
            shelfRepository.save(new ShelfEntity(dto.getRowNumber(), dto.getClosetId(), dto.getCountOfBook()));
            return "Successfully";
        }else {
            throw new DataAlreadyExistsException("This row number exists in this closet");
        }
    }


    @Override
    public String remove(UUID shelfId) {
        ShelfEntity shelf = shelfRepository.findById(shelfId).orElseThrow(() -> new DataNotFoundException("Shelf not found"));
        shelf.setIsActive(false);
        shelfRepository.save(shelf);
        return "Removed";
    }

    @Override
    public List<ShelfResponseDto> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ShelfEntity> shelf = shelfRepository.findAllByIsActiveTrue(pageRequest);
        List<ShelfEntity> content = shelf.getContent();
        return parse(content);
    }

    @Override
    public ShelfResponseDto getById(UUID shelfId) {
        ShelfEntity shelf = shelfRepository.findById(shelfId).orElseThrow(() -> new DataNotFoundException("Shelf not found"));
        return parse(shelf);
    }

    /**
     * Bugun suhbatda qoshgan methodim. Birinchi qismi shu. yani api orqali shu methodga murojat qiladi
     * @param  floorNumber
     * @return
     */
    @Override
    public List<ShelfResponseDto> getShelfOfCloses(Integer floorNumber) {
        List<ClosetEntity> closes = closetService.getCloses(floorNumber);
        return  getFreePlace(closes);
    }


    @Override
    public List<ShelfResponseDto> getFreePlace(List<ClosetEntity> closes) {  /// 1 ta qavatdagi shkaflar
        List<ShelfResponseDto> list = new ArrayList<>();
        for (ClosetEntity close : closes) {
            FloorResponseDto byId = floorService.getById(close.getFloorId());
            ShelfEntity allByClosetIdAndRowNumber = shelfRepository.findAllByClosetIdAndRowNumber(close.getId(), byId.getNumber()).orElseThrow(()-> new DataNotFoundException(""));
            int nowCount = 20 - allByClosetIdAndRowNumber.getCountOfBook();
            list.add(new ShelfResponseDto(byId.getNumber(), allByClosetIdAndRowNumber.getId(), close.getId(), nowCount));
        }
        return list;
    }


    private ShelfResponseDto parse( ShelfEntity shelf){
        return new ShelfResponseDto(shelf.getRowNumber(), shelf.getId(), shelf.getClosetId(), shelf.getCountOfBook());
    }

    private List<ShelfResponseDto> parse(List<ShelfEntity> shelfEntities){
        List<ShelfResponseDto> list = new ArrayList<>();
        for (ShelfEntity shelfEntity : shelfEntities) {
            list.add(new ShelfResponseDto(shelfEntity.getRowNumber(), shelfEntity.getId(), shelfEntity.getClosetId(), shelfEntity.getCountOfBook()));
        }
        return list;
    }


}
