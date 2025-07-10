package com.training.integratePostgres.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    //Which service we want to test
    @InjectMocks
    private StudentService studentService;

    //Declare the dependencies
    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student(){
        //Given
        StudentDTO dto = new StudentDTO(
                "TRAE",
                "Young",
                "traeyoung@gmail.com",
                1
        );
        Student savedStudent = new Student(
                "BALL",
                "Lammel",
                "balllammel@gmail.com",
                24
        );
        savedStudent.setId(1);

        Student student = new Student(
                "BALL",
                "Lammel",
                "balllammel@gmail.com",
                24
        );

        //Mock the calls
        when(mapper.toStudent(dto))
                .thenReturn(student);
        when(repository.save(student))
                .thenReturn(savedStudent);
        when(mapper.tostudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "TRAE",
                        "Young",
                        "traeyoung@gmail.com"
                ));


        //When
        StudentResponseDto responseDto = studentService.saveStudent(dto);
        //Then

        assertEquals(dto.firsName(), responseDto.firsName());
        assertEquals(dto.lastName(), responseDto.lastName());
        assertEquals(dto.email(), responseDto.email());

        //Verify that we save the student one times
        verify(mapper, times(1)).
                toStudent(dto);
        verify(repository, times(1)).
                save(student);
        verify(mapper, times(1)).
                tostudentResponseDto(savedStudent);

    }

    @Test
    public void should_successfully_find_all_students(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
           "REAVES",
           "Austin",
           "reavesaustin@gmail.com",
            30
        ));

        //Mock the calls
        when(repository.findAll())
                .thenReturn(students);
        when(mapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "REAVES",
                        "Austin",
                        "reavesaustin@gmail.com"
                ));

        //When
        List<StudentResponseDto> responseDtos = studentService.findAllStudents();
        //Then
        assertEquals(students.size(), responseDtos.size());
        verify(repository, times(1))
                .findAll();
    }

    @Test
    public void should_successfully_find_a_student_by_id(){
        //Given
        Integer studentId = 1;
        Student student = new Student(
                "DILLINGHAM",
                "Rob",
                "dillinghamrob@gmail.com",
                20
        );


        //Mock the calls
        when(repository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(mapper.tostudentResponseDto(student))
                .thenReturn(new StudentResponseDto(
                        "DILLINGHAM",
                        "Rob",
                        "dillinghamrob@gmail.com"
                ));
        //When
        StudentResponseDto dto = studentService.findAllStudent(studentId);
        //Then
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.firsName(), student.getFirsName());
        assertEquals(dto.email(), student.getEmail());

        verify(repository, times(1)).findById(studentId);//repository will be call one time for the method

    }

    @Test
    public void should_successfully_find_a_student_by_name(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "HALIBURTON",
                "Tyrese",
                "tyresehaliburton@gmail.com",
                23
        ));

        //Mock the calls
        when(repository.findAllByFirsNameContaining("HALIBURTON"))
                .thenReturn(students);
        when(mapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "HALIBURTON",
                        "Tyrese",
                        "tyresehaliburton@gmail.com"
                ));
        //when
        var responseDto = studentService.findAllStudentsByFirstName("HALIBURTON");
        //Then
        assertEquals(students.size(), responseDto.size());
        verify(repository, times(1)).findAllByFirsNameContaining("HALIBURTON");
    }

    @Test
    public void should_successfully_delete_a_student() {
        //Given
        Integer studentId = 1;
        Student student = new Student(
                "HALIBURTON",
                "Tyrese",
                "tyresehaliburton@gmail.com",
                23
        );
        //Mock the calls
        doNothing().when(repository).deleteById(studentId);
        //When
        studentService.delete(studentId);
        //Then
        verify(repository, times(1)).deleteById(studentId);

    }


}