package uz.pdp.librarysystem.dto.responseDto;
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
public class BookingResponseDto {
    private UUID bookingId;
    private UserResponseDto user;

    private BookResponseDto book;

    private LocalDate createdDate;
    private LocalDate submissionDate;
}
