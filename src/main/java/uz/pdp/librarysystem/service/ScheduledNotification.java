package uz.pdp.librarysystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.librarysystem.dto.createDto.MailDto;
import uz.pdp.librarysystem.dto.responseDto.BookingResponseDto;
import uz.pdp.librarysystem.entities.BookingEntity;
import uz.pdp.librarysystem.repository.UserRepository;
import uz.pdp.librarysystem.service.bookingService.BookingService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledNotification {
    private final BookingService bookingService;
    private final MailService mailService;

    /**
     * bu method ogohlantiradi kitobini olib ketishi un
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkSubmissionDate() {
        List<BookingEntity> list = bookingService.checkSubmissionDate();
        if (!list.isEmpty()){
            for (BookingEntity booking : list) {
                MailDto mailDto = new MailDto("Today is the last day you can pick up your reserved book. Otherwise it will not be booked for you.", booking.getUser().getEmail());
                mailService.sendMail(mailDto);
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeBooking(){
        bookingService.delete();
    }



}
