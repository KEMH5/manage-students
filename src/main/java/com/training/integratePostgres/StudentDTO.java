package com.training.integratePostgres;

public record StudentDTO(
        String firsName,
        String lastName,
        String email,
        Integer schoolId
) {

}
