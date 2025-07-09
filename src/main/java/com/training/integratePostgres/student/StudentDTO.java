package com.training.integratePostgres.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
        @NotEmpty(message = "firstName should not be empty")
        String firsName,

        @NotEmpty(message = "firstName should not be empty")
        String lastName,
        String email,
        Integer schoolId
) {

}
