package uz.pdp.librarysystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "book_shelf")
public class BookShelfEntity extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id",insertable = false, updatable = false)
    private BookEntity book;
    @Column(name = "book_id")
    private UUID bookId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shelf_id", insertable = false, updatable = false )
    private ShelfEntity shelf;
    @Column(name = "shelf_Id")
    private UUID shelfId;

    private Integer count; /// shu kitobdan mana shu tokchada nechta turgani.

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "closet_Id", insertable = false, updatable = false)
    private ClosetEntity closet;
    @Column(name = "closet_Id")
    private UUID closetId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "floor_Id", insertable = false, updatable = false)
    private FloorEntity floor;
    @Column(name = "floor_Id")
    private UUID floorId;

    public BookShelfEntity(UUID bookId, UUID shelfId, Integer count, UUID closetId, UUID floorId) {
        this.bookId = bookId;
        this.shelfId = shelfId;
        this.count = count;
        this.closetId = closetId;
        this.floorId = floorId;
    }
}
