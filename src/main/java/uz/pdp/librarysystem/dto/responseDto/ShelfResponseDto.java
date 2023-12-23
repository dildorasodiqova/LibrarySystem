package uz.pdp.librarysystem.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShelfResponseDto {
    private Integer rowNumber;
    private UUID shelfId;
    private UUID cosetId;
    private Integer countOfBook;
}
