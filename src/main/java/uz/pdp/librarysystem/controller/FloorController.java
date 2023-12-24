package uz.pdp.librarysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.librarysystem.dto.createDto.ClosetCreateDto;
import uz.pdp.librarysystem.dto.createDto.FloorCreateDto;
import uz.pdp.librarysystem.dto.responseDto.ClosetResponseDto;
import uz.pdp.librarysystem.dto.responseDto.FloorResponseDto;
import uz.pdp.librarysystem.service.floorService.FloorService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/floor")
public class FloorController {
    private  final FloorService floorService;

    @Operation(
            description = "This method is used to add a floor",
            method = "POST method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody FloorCreateDto dto) {
        return ResponseEntity.ok(floorService.save(dto));
    }

    @Operation(
            description = "This API makes the floor inactive",
            method = "DELETE method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{floorId}")
    public ResponseEntity<String> delete(@PathVariable UUID floorId) {
        return ResponseEntity.ok(floorService.remove(floorId));
    }


    @Operation(
            description = "This method return all floors",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN", "MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/getAll")
    public ResponseEntity<List<FloorResponseDto>> getAll(@RequestParam(value = "page", defaultValue = "0")
                                                          int page,
                                                         @RequestParam(value = "size", defaultValue = "5")
                                                          int size) {
        return ResponseEntity.ok(floorService.getAll(page, size));
    }
}
