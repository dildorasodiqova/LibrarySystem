package uz.pdp.librarysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.librarysystem.dto.createDto.BookingCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookingResponseDto;
import uz.pdp.librarysystem.entities.enums.BookStatus;
import uz.pdp.librarysystem.service.bookingService.BookingService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    @Operation(
            description = "This method is used to add a booking",
            method = "POST method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"USER"})
    )
    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody BookingCreateDto dto) {
        return ResponseEntity.ok(bookingService.save(dto));
    }


    @Operation(
            description = "This method do cancels bookings of user",
            method = "DELETE method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"USER"})
    )
    @PreAuthorize(value = "hasAuthority('USER')")
    @PutMapping("/cancelBookingOfUser/{bookingId}")
    public ResponseEntity<String> cancelBookingOfUser(Principal principal, @PathVariable UUID bookingId) {
        bookingService.cancelBookingOfUser(UUID.fromString(principal.getName()), bookingId);
        return ResponseEntity.ok("Successfully");
    }


    @Operation(
            description = "This method return all bookings of user",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"USER"})
    )
    @PreAuthorize(value = "hasAuthority('USER')")
    @GetMapping("/bookingOfUser")
    public ResponseEntity<List<BookingResponseDto>> bookingOfUser(
            Principal principal,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "5")
            int size) {
        return ResponseEntity.ok(bookingService.bookingOfUser(UUID.fromString(principal.getName()), page, size));
    }


    @Operation(
            description = "This method is used to update the status of a single book",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @PutMapping("/updateStatus/{bookingId}")
    public ResponseEntity<String> updateStatus(
            Principal principal,
            @PathVariable UUID bookingId,
            @RequestParam BookStatus bookStatus
            ) {
        return ResponseEntity.ok(bookingService.updateStatus(bookingId, bookStatus));
    }


    @Operation(
            description = "This method return all bookings",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @GetMapping("/viewBooks")
    public ResponseEntity<List<BookingResponseDto>> getAll(@RequestParam(value = "page", defaultValue = "0")
                                                           int page,
                                                           @RequestParam(value = "size", defaultValue = "5")
                                                           int size) {
        return ResponseEntity.ok(bookingService.viewBooks(page, size));
    }
}
