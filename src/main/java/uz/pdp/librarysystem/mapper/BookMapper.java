package uz.pdp.librarysystem.mapper;

import java.time.LocalDate;
import java.util.UUID;

public interface BookMapper {
    String getName();
    String getAuthor();
    LocalDate getYearOfWriting();
    Integer getOldCount();
    Integer getNowCount();


    Integer getRowNumber();    //    ShelfEntity shelf;
    UUID getClosetId();
    Integer getCountOfBook();


    UUID getFloorId();  //     ClosetEntity closet;
    String getCode();


    Integer getNumber();     //    FloorEntity floor;
}
