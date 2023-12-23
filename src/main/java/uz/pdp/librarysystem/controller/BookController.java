package uz.pdp.librarysystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.librarysystem.dto.createDto.BookCreateDto;
import uz.pdp.librarysystem.dto.responseDto.BookResponseDto;
import uz.pdp.librarysystem.service.bookService.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;

    @Operation(
            description = "This method is used to add a book",
            method = "POST method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody BookCreateDto bookCreateDto) {
        return ResponseEntity.ok(bookService.save(bookCreateDto));
    }


    @Operation(
            description = "This method return all books",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDto>> getAll(@RequestParam(value = "page", defaultValue = "0")
                                                        int page,
                                                        @RequestParam(value = "size", defaultValue = "5")
                                                        int size) {
        return ResponseEntity.ok(bookService.getAll(page, size));
    }


    @Operation(
            description = "This method is designed to search books by name or author",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> search(
            @RequestParam String word,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "5")
            int size) {
        return ResponseEntity.ok(bookService.search(word,page, size));
    }


    @Operation(
            description = "This API makes the book inactive",
            method = "DELETE method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"MODERATOR"})
    )
    @PreAuthorize(value = "hasAuthority('MODERATOR')")
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> delete(@PathVariable UUID bookId) {
        return ResponseEntity.ok(bookService.delete(bookId));
    }
}
