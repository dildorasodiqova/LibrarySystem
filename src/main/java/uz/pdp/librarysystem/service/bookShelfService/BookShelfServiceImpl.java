package uz.pdp.librarysystem.service.bookShelfService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import uz.pdp.librarysystem.dto.createDto.BookShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ShelfResponseDto;
import uz.pdp.librarysystem.entities.BookShelfEntity;
import uz.pdp.librarysystem.exception.ExceededLimitException;
import uz.pdp.librarysystem.repository.BookShelfRepository;
import uz.pdp.librarysystem.service.closetService.ClosetService;
import uz.pdp.librarysystem.service.floorService.FloorService;
import uz.pdp.librarysystem.service.shelfService.ShelfService;

@Service
@RequiredArgsConstructor
public class BookShelfServiceImpl implements BookShelfService{
    private final BookShelfRepository bookShelfRepository;
    private final ShelfService shelfService;

    @Override
    public String bookPlacement(BookShelfCreateDto dto) {
        ShelfResponseDto shelf = shelfService.getById(dto.getShelfId());
        if (shelf.getCountOfBook() >= 20){
            throw  new ExceededLimitException("The shelf is full of books");
        }else if (shelf.getCountOfBook()+ dto.getCount() > 20){
            int i = 20 - (shelf.getCountOfBook() + dto.getCount());
            int j = shelf.getCountOfBook() + dto.getCount() - 20;
            throw new ExceededLimitException("You can put "+ i +" books on this shelf. \n" + j + "put your book on another shelf.");
        }
        bookShelfRepository.save(new BookShelfEntity(dto.getBookId(), dto.getShelfId(), dto.getCount(), dto.getClosetId(), dto.getFloorId()));
        return "Successfully";
    }
}
