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
public class UserResponseDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String passportNumberAndSeries;
    private String email;
    private String userRole;
    private LocalDateTime createdDate;
}
