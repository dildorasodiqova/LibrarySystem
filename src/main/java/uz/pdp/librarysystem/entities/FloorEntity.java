package uz.pdp.librarysystem.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "floor") /// bu qavat
public class FloorEntity extends BaseEntity {
    private Integer number; /// qavatining raqami
}
