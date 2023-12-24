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
public class BookAllDto {
    private ShelfResponseDto shelf;
    private ClosetResponseDto closet;
    private FloorResponseDto floor;
    private Integer count;
}
