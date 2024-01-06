package uz.pdp.librarysystem.repository;

import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.librarysystem.entities.ShelfEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShelfRepository extends JpaRepository<ShelfEntity, UUID> {
    @Query("SELECT s FROM shelf s WHERE s.closetId = :closetId AND s.rowNumber = :rowNumber")
    Optional<ShelfEntity> findAllByClosetIdAndRowNumber(@Param("closetId") UUID closetId, @Param("rowNumber") Integer rowNumber);


    Page<ShelfEntity> findAllByIsActiveTrue(PageRequest pageRequest);
    List<ShelfEntity> findAllByRowNumber(Integer rowNumber);

}
