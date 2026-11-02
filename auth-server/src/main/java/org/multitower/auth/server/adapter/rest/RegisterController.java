package org.multitower.auth.server.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.multitower.auth.server.dto.UserDto;
import org.multitower.user.domain.application.service.UserIdGenerator;
import org.multitower.user.domain.application.service.UserRepository;
import org.multitower.user.domain.entity.User;
import org.multitower.user.domain.valueobject.AuthenticationType;
import org.multitower.user.domain.valueobject.UserValidationError;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserRepository userRepository;
    private final UserIdGenerator userIdGenerator;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register"; // Loads register.html
    }

    @PostMapping(value = "/register")
    public String registerUser(@Validated UserDto requestBody, Model model) {
        User user = mapToUser(requestBody);

        List<UserValidationError> validationErrors = user.validate();
        if (!validationErrors.isEmpty()) {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setNickName(user.getNickName());
            userDto.setPassword(user.getPassword());
            model.addAttribute("user", userDto);

            model.addAttribute("errorMessage", buildErrorMessage(validationErrors));
            return "register";
        }

        try {
            user.setId(userIdGenerator.generateUserId());
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("user", requestBody);
            model.addAttribute("errorMessage",
                "The registration could not be completed. Please try again later or use a different email.");
            return "register";
        }

        return "redirect:/login"; // Redirects to login page after successful registration
    }

    private String buildErrorMessage(List<UserValidationError> validationErrors) {
        return "The registration could not be completed: "
            + validationErrors.stream()
            .map(UserValidationError::getDescription)
            .collect(Collectors.joining(" "));
    }

    private User mapToUser(UserDto userDto) {
        User user = User.builder()
            .type(AuthenticationType.OWNED)
            .email(userDto.getEmail())
            .nickName(userDto.getNickName())
            .password(encodePassword(userDto))
            .createdAt(Instant.now())
            .build();

        return user;
    }

    private String encodePassword(UserDto userDto) {
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            return null;
        }

        return passwordEncoder.encode(userDto.getPassword());
    }
}