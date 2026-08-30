package org.multitower.auth.server.dto;

import lombok.Data;
import org.multitower.user.domain.valueobject.AuthenticationType;

@Data
public class UserDto {
    private String email;
    private String password;
    private String nickName;
    private AuthenticationType authenticationType;
}