package uz.pdp.librarysystem.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingCreateDto {
    private UUID userId;
    private UUID bookId;
    private LocalDate yearOfWriting;
}
