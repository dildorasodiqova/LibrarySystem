package uz.pdp.librarysystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.librarysystem.dto.responseDto.UserResponseDto;
import uz.pdp.librarysystem.service.userService.UserService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userServiceImpl;


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/getByid")
    public UserResponseDto getById(Principal principal) {
        return userServiceImpl.getById(UUID.fromString(principal.getName()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get-all")
    public List<UserResponseDto> getAll(@RequestParam(value = "page", defaultValue = "0")
                                            int page,
                                        @RequestParam(value = "size", defaultValue = "5")
                                            int size) {
        return userServiceImpl.getAll(page, size);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable UUID userId) {
        return userServiceImpl.deleteUser(userId);
    }
}
