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
@Entity(name = "shelf") // bu tokcha
public class ShelfEntity extends BaseEntity {
     private Integer rowNumber; // bu shkafni qaysi qatoriligi
     private UUID closetId;
     private Integer countOfBook; ///bu tokchaga nechta kitob solingani
}
