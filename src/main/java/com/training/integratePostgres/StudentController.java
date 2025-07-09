package com.training.integratePostgres;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @RequestBody
            StudentDTO dto
    ){
        return this.studentService.saveStudent(dto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents()
    {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findAllStudent(
            @PathVariable("student-id")
            Integer id
    )
    {
            return studentService.findAllStudent(id);
    }

    @GetMapping("/students/search/{student-firstname}")
    public List<StudentResponseDto> findAllStudentsByFirstName(
            @PathVariable("student-firstname")
            String name
    ){
        return studentService.findAllStudentsByFirstName(name);
    }

    @DeleteMapping("/student/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id")
            Integer id
    ) {
        studentService.delete(id);
    }

}




