package uz.pdp.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.librarysystem.entities.FloorEntity;

import java.util.UUID;

public interface FloorRepository extends JpaRepository<FloorEntity, UUID> {
    Page<FloorEntity> findAllByIsActiveTrue(PageRequest pageRequest);
    Boolean existsAllByNumber(Integer number);
}
