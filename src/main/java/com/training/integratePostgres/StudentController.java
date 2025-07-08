package com.training.integratePostgres;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }


    @PostMapping("/students")
    public StudentResponseDto post(
            @RequestBody
            StudentDTO dto
    ){
        var student = toStudent(dto);
        var savedStudent = repository.save(student);
        return tostudentResponseDto(savedStudent);
    }

    private Student toStudent(
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

    private StudentResponseDto tostudentResponseDto(
            Student student
    ){
        return new StudentResponseDto(
                student.getFirsName(),
                student.getLastName(),
                student.getEmail()
        );
    }



    @GetMapping("/students")
    public List<Student> findAllStudents(){
        return repository.findAll();
    }

    @GetMapping("/students/{student-id}")
    public Student findAllStudent(
            @PathVariable("student-id")
            Integer id
    )
    {
            return repository.findById(id)
                    .orElse(new Student());
    }

    @GetMapping("/students/search/{student-firstname}")
    public List<Student> findAllStudentsByFirstName(
            @PathVariable("student-firstname")
            String name
    ){
        return repository.findAllByFirsNameContaining(name);
    }

    @DeleteMapping("/student/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id")
            Integer id
    ) {
        repository.deleteById(id);
    }

}




