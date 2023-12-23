package uz.pdp.librarysystem.dto.responseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
