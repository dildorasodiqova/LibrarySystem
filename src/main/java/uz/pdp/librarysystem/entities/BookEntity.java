package uz.pdp.librarysystem.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

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



    public BookEntity(String name, String author, LocalDate yearOfWriting, Integer count, Integer count1) {
        this.name = name;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
        this.oldCount = count;
        this.nowCount = count1;

    }
}
