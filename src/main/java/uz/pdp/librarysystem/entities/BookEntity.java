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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shelfId" , insertable = false, updatable = false)
    private ShelfEntity shelf;
    @Column(name = "shelfId")
    private UUID shelfId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "closetId", insertable = false, updatable = false)
    private ClosetEntity closet;
    @Column(name = "closetId")
    private UUID closetId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "floorId", insertable = false, updatable = false)
    private FloorEntity floor;
    @Column(name = "floorId")
    private UUID floorId;

    public BookEntity(String name, String author, LocalDate yearOfWriting, Integer count, Integer count1, UUID shelfId, UUID closetId, UUID floorId) {
        this.name = name;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
        this.oldCount = count;
        this.nowCount = count1;
        this.shelfId = shelfId;
        this.closetId = closetId;
        this.floorId = floorId;
    }
}
