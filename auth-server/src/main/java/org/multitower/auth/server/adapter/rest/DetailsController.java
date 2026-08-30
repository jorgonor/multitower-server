package org.multitower.auth.server.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.multitower.auth.server.adapter.security.CustomUserDetails;
import org.multitower.auth.server.dto.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DetailsController {
    @GetMapping("/details")
    public String showUserDetails(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        model.addAttribute("user", mapToUserDTO(userDetails));

        return "details"; // Loads register.html
    }

    private UserDto mapToUserDTO(UserDetails userDetails) {
        UserDto userDto = new UserDto();

        if (userDetails instanceof CustomUserDetails customUserDetails) {
            userDto.setEmail(customUserDetails.getUsername());
            userDto.setNickName(customUserDetails.getNickname());
            userDto.setAuthenticationType(customUserDetails.getType());
        }

        return userDto;
    }
}
