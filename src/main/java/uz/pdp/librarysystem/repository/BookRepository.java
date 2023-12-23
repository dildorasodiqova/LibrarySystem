package uz.pdp.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.librarysystem.entities.BookEntity;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    @Transactional
    @Query("""
    SELECT b FROM books b
    WHERE LOWER(b.author) = LOWER(:author) AND LOWER(b.name) = LOWER(:name)
    """)
    Optional<BookEntity> getBookEntitiesByAuthorAndName(String author, String name);


    Page<BookEntity> findAllByIsActiveTrue(Pageable pageable);

    @Transactional
    @Query("""
            SELECT b FROM books b WHERE b.isActive = TRUE AND
            LOWER(b.name) LIKE LOWER(CONCAT('%', :word, '%')) OR
            LOWER(b.author) LIKE LOWER(CONCAT('%', :word, '%'))"""
    )
    Page<BookEntity> searchBook(String word, Pageable pageRequest);
}
