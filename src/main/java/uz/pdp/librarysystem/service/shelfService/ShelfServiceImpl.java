package uz.pdp.librarysystem.service.shelfService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.ShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ShelfResponseDto;
import uz.pdp.librarysystem.entities.ShelfEntity;
import uz.pdp.librarysystem.entities.UserEntity;
import uz.pdp.librarysystem.exception.DataAlreadyExistsException;
import uz.pdp.librarysystem.exception.DataNotFoundException;
import uz.pdp.librarysystem.repository.ShelfRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor ///this is tokcha
public class ShelfServiceImpl implements ShelfService{
    private final ShelfRepository shelfRepository;

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
