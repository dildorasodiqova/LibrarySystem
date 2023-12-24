package uz.pdp.librarysystem.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookShelfResponseDto {
    private UUID bookShelfId;
    private BookResponseDto book;
    private ShelfResponseDto shelf;
    private Integer count;
    private ClosetResponseDto closet;
    private FloorResponseDto floor;
    private LocalDateTime createdDate;
}
