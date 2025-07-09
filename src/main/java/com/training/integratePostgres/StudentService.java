package com.training.integratePostgres;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(
            StudentDTO dto
    ){
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.tostudentResponseDto(savedStudent);
    }


    public List<StudentResponseDto> findAllStudents()
    {
        return repository.findAll()
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findAllStudent(
            Integer id
    )
    {
        return repository.findById(id)
                .map(studentMapper::tostudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findAllStudentsByFirstName(
            String name
    ){
        return repository.findAllByFirsNameContaining(name)
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(
            Integer id
    ) {
        repository.deleteById(id);
    }

}
