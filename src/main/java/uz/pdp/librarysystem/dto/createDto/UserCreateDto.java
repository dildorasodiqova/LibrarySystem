package uz.pdp.librarysystem.dto.createDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String passportNumberAndSeries;
    private String password;
    private String email;
}
