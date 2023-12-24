package uz.pdp.librarysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.librarysystem.dto.createDto.ClosetCreateDto;
import uz.pdp.librarysystem.dto.createDto.ShelfCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.dto.responseDto.ShelfResponseDto;
import uz.pdp.librarysystem.service.shelfService.ShelfService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/shelf")
public class ShelfController {
    private final ShelfService shelfService;

    @Operation(
            description = "This method is used to add a shelf",
            method = "POST method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody ShelfCreateDto dto) {
        return ResponseEntity.ok(shelfService.save(dto));
    }


    @Operation(
            description = "This API makes the shelf inactive",
            method = "DELETE method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{shelfId}")
    public ResponseEntity<String> delete(@PathVariable UUID shelfId) {
        return ResponseEntity.ok(shelfService.remove(shelfId));
    }


    @Operation(
            description = "This method return all shelf",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN", "MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/getAll")
    public ResponseEntity<List<ShelfResponseDto>> getAll(@RequestParam(value = "page", defaultValue = "0")
                                                          int page,
                                                         @RequestParam(value = "size", defaultValue = "5")
                                                          int size) {
        return ResponseEntity.ok(shelfService.getAll(page, size));
    }
}
