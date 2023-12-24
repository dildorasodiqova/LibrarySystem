package uz.pdp.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.librarysystem.entities.BookingEntity;
import uz.pdp.librarysystem.entities.enums.BookStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    Page<BookingEntity> findAllByIsActiveTrue(PageRequest pageRequest);

    @Transactional
    @Modifying
    @Query("UPDATE booking b SET b.status = 'CANCELED' WHERE b.user.id = :userId AND b.id = :bookingId")
    void cancelBookingOfUser(@Param("userId") UUID userId, @Param("bookingId") UUID bookingId);

    @Transactional
    @Query("SELECT b FROM booking  b WHERE (b.submissionDate = :currentDate)  AND b.status != 'RETURNED'")
    List<BookingEntity> checkSubmissionDate(@Param("currentDate") LocalDate currentDate);


    @Transactional
    @Modifying
    @Query("UPDATE booking b SET b.status = :bookStatus WHERE b.id = :bookingId")
    int updateStatus(@Param("bookingId") UUID bookingId, @Param("bookStatus") BookStatus bookStatus);


    @Transactional
    @Modifying
    @Query("UPDATE booking b SET b.status = 'CANCELED' WHERE b.submissionDate < :currentDate AND b.status != 'CREATED'")
    List<BookingEntity> remove(@Param("currentDate") LocalDate currentDate);

    List<BookingEntity> findAllByUserId(UUID user_id, PageRequest pageRequest);
}
