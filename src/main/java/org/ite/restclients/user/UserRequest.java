package org.ite.restclients.user;

public record UserRequest(
        String username,

        String email
) {
}
