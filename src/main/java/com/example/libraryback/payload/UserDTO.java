package com.example.libraryback.payload;

import com.example.libraryback.entity.User;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private UUID id;

    private String bio;

    private UUID avatar;

    private RoleDTO role;

    private String email;

    private boolean enabled;

    private String lastname;

    private String firstname;

    private Set<PermissionEnum> permissions;

    public static UserDTO mapUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .bio(user.getBio())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .avatar(user.getAvatar().getId())
                .role(RoleDTO.map(user.getRole()))
                .permissions(user.getRole().getPermissions())
                .build();
    }

    public static Set<UserDTO> mapUserDTO(Set<User> users) {
        return users.stream()
                .map(UserDTO::mapUserDTO)
                .collect(Collectors.toSet());
    }
}

