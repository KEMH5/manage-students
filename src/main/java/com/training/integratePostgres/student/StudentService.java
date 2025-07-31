package com.training.integratePostgres.student;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);

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
