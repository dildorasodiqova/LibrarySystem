package uz.pdp.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.librarysystem.entities.BookEntity;
import uz.pdp.librarysystem.mapper.BookMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    @Transactional
    @Query("""
    SELECT b FROM books b
    WHERE LOWER(b.author) = LOWER(:author) AND LOWER(b.name) = LOWER(:name)
    """)
    Optional<BookEntity> getBookEntitiesByAuthorAndName(String author, String name);

    @Transactional
    @Modifying
    @Query("""
     UPDATE books b SET b.nowCount = :count WHERE b.id = :bookId
    """)
    int updateNowCount(@Param("count")Integer count,@Param("bookId") UUID bookId);

    Page<BookEntity> findAllByIsActiveTrue(Pageable pageable);

    @Transactional
    @Query("""
            SELECT b FROM books b WHERE b.isActive = TRUE AND
            LOWER(b.name) LIKE LOWER(CONCAT('%', :word, '%')) OR
            LOWER(b.author) LIKE LOWER(CONCAT('%', :word, '%'))"""
    )
    Page<BookEntity> searchBook(String word, Pageable pageRequest);


//
//    @Query("""
//            select b.name as name, b.author as author , b.oldCount as oldCount, b.nowCount as nowCount,
//            b.yearOfWriting as yearOfWriting,sh.closetId as closetId, sh.countOfBook as countOfBook,
//             sh.rowNumber as rowNumber, c.floorId as floorId, c.code as code,f.number as number
//            from books b join b.floor f  join b.closet c  join b.shelf sh where b.id = :bookId
//            """)
//    List<BookMapper> mapper(@Param("bookId") UUID bookId);
}
