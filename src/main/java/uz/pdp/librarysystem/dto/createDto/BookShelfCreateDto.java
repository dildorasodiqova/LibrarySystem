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
public class BookShelfCreateDto {
    private UUID bookId;
    private UUID shelfId;
    private Integer count;
    private UUID closetId;
    private UUID floorId;
}
