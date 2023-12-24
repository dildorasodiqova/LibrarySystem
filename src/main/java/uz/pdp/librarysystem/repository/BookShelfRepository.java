package uz.pdp.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.librarysystem.entities.BookShelfEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookShelfRepository extends JpaRepository<BookShelfEntity, UUID> {
    List<BookShelfEntity> findAllByBookIdAndIsActiveTrue(UUID bookId);
    Optional<BookShelfEntity> findAllByBookIdAndShelfId(UUID bookId, UUID shelfId);

    @Transactional
    @Modifying
    @Query("""
          update book_shelf b set b.count = :count where b.id =:id
""")
    int updateBookCount(@Param("count") Integer count,@Param("id") UUID id);
}
