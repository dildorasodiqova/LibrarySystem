package uz.pdp.librarysystem.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShelfCreateDto {
    private Integer rowNumber;
    private UUID closetId;
    private Integer countOfBook;
}
