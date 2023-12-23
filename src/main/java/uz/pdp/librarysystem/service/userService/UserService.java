package uz.pdp.librarysystem.service.userService;

import uz.pdp.librarysystem.dto.createDto.*;
import uz.pdp.librarysystem.dto.responseDto.JwtResponse;
import uz.pdp.librarysystem.dto.responseDto.UserResponseDto;
import uz.pdp.librarysystem.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto signUp(UserCreateDto dto);
    void emailSend(UserEntity userEntity);
    String deleteUser(UUID userId);
    UserEntity findById(UUID userId);
    UserResponseDto getById(UUID userId);
    List<UserResponseDto> getAll(Integer page, Integer size);
    String getAccessToken(String refreshToken, UUID userId);
    JwtResponse signIn(VerifyDtoP verifyDtoP);
    String getVerificationCode(String email);
    UserResponseDto verify(VerifyDto verifyDto);
    SubjectDto verifyToken(String token);
    String forgetPassword(ForgetDto forgetDto);

}
