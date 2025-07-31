package com.training.integratePostgres.student;

import com.training.integratePostgres.school.School;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public Student toStudent(
            StudentDTO dto
    ){
        if (
                dto == null
        ){
            throw new NullPointerException("The student Dto should not be null");
        }
        var student = new Student();
        student.setFirsName(dto.firsName());
        student.setLastName(dto.lastName());
        student.setEmail(encoder.encode(dto.email()));

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
