package kh.edu.istad.restapi.dto;

public record UserAddDto(
        String username,
        String password,
        String fullname
) {
}
