package uz.pdp.librarysystem.entities;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "books")
public class BookEntity extends BaseEntity{
    private String name;
    private String author;
    private LocalDate yearOfWriting;
    private Integer oldCount;
    private Integer nowCount;

}
