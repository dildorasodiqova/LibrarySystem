package uz.pdp.librarysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.librarysystem.dto.createDto.BookShelfCreateDto;
import uz.pdp.librarysystem.dto.createDto.BookingCreateDto;
import uz.pdp.librarysystem.service.bookShelfService.BookShelfService;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/bookShelf")
public class BookShelfController {
    private final BookShelfService bookShelfService;

    @Operation(
            description = "This method is used to place the book on the shelf",
            method = "POST method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @PostMapping()
    public ResponseEntity<String> bookPlacement(@RequestBody BookShelfCreateDto dto) {
        return ResponseEntity.ok(bookShelfService.bookPlacement(dto));
    }

}
