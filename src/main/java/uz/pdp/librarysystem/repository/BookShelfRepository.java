package uz.pdp.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.librarysystem.entities.BookShelfEntity;

import java.util.UUID;

public interface BookShelfRepository extends JpaRepository<BookShelfEntity, UUID> {
}
