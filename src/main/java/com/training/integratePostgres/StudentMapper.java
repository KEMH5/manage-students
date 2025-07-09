package com.training.integratePostgres;

import org.springframework.stereotype.Service;

@Service
public class StudentMapper {


    public Student toStudent(
            StudentDTO dto
    ){
        var student = new Student();
        student.setFirsName(dto.firsName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);

        return student;
    }


    public StudentResponseDto tostudentResponseDto(
            Student student
    ){
        return new StudentResponseDto(
                student.getFirsName(),
                student.getLastName(),
                student.getEmail()
        );
    }

}
