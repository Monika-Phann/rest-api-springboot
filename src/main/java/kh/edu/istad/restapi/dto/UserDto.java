package kh.edu.istad.restapi.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        String fullName
) {
}
