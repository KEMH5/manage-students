package com.training.integratePostgres.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @Valid
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ){
        var errors = new HashMap<String, String>();//<fieldTye, errorType>
        exp.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    var fieldName =((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}




