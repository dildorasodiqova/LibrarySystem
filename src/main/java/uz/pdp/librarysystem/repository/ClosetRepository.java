package uz.pdp.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.librarysystem.entities.ClosetEntity;

import java.util.UUID;

public interface ClosetRepository extends JpaRepository<ClosetEntity, UUID> {
    Page<ClosetEntity> findAllByIsActiveTrue(PageRequest pageRequest);
    Boolean existsAllByCode(String code);
}
