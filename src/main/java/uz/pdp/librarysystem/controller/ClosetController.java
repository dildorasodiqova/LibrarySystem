package uz.pdp.librarysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.librarysystem.dto.createDto.BookingCreateDto;
import uz.pdp.librarysystem.dto.createDto.ClosetCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookingResponseDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.service.closetService.ClosetService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/closet")
public class ClosetController {
    private final ClosetService closetService;


    @Operation(
            description = "This method is used to add a closet",
            method = "POST method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody ClosetCreateDto dto) {
        return ResponseEntity.ok(closetService.save(dto));
    }


    @Operation(
            description = "This API makes the closet inactive",
            method = "DELETE method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{closetId}")
    public ResponseEntity<String> delete(@PathVariable UUID closetId) {
        return ResponseEntity.ok(closetService.remove(closetId));
    }



    @Operation(
            description = "This method return all closets",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN", "MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN')  or hasAuthority('MODERATOR')")
    @GetMapping("/getAll")
    public ResponseEntity<List<ClosetResponseDto>> getAll(@RequestParam(value = "page", defaultValue = "0")
                                                           int page,
                                                          @RequestParam(value = "size", defaultValue = "5")
                                                           int size) {
        return ResponseEntity.ok(closetService.getAll(page, size));
    }
}
