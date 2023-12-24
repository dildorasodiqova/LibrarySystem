package uz.pdp.librarysystem.dto.responseDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.librarysystem.entities.ClosetEntity;
import uz.pdp.librarysystem.entities.FloorEntity;
import uz.pdp.librarysystem.entities.ShelfEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookResponseDto {
    private UUID bookId;
    private String name;
    private String author;
    private Integer nowCount;
    private Integer oldCount;
    private LocalDate yearOfWriting;
    private LocalDateTime createdDate;

}
