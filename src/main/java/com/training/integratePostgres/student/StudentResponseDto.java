package com.training.integratePostgres.student;

public record StudentResponseDto(
        String firsName,
        String lastName,
        String email
) {
}
