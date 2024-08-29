package org.ite.restclients.user;

public record UserResponse(
        Integer id,
        String username,
        String email
) {
}
