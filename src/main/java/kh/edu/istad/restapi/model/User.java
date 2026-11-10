package kh.edu.istad.restapi.model;

import java.util.UUID;

public record User(
        UUID id,
        String username,
        String password,
        String fullName
) {
}
