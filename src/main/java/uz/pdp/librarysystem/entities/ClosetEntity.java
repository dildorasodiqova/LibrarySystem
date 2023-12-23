package uz.pdp.librarysystem.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "closet") // bu shkaf
public class ClosetEntity extends BaseEntity {
    private UUID floorId;
    private String code; // bu qaysi shkafligini bilish un

}
