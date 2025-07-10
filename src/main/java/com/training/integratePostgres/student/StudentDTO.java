package com.training.integratePostgres.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
        @NotEmpty(message = "firstName should not be empty")
        String firsName,

        @NotEmpty(message = "firstName should not be empty")
        String lastName,

        @Email
        String email,

        Integer schoolId
) {

}
