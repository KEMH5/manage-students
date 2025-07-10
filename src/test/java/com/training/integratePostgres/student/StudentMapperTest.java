package com.training.integratePostgres.student;


import jakarta.persistence.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    /*
      @BeforeAll
     *     static void beforeAll() {
     *         System.out.println("Inside the before all method");
     *     }
     *
     *     @AfterAll
     *     static void afterAll() {
     *         System.out.println("Inside the after all method");
     *     }
     *
     *     @BeforeEach//Will be executed before the other methods
     *     void setUp() {
     *         System.out.println("Inside the before each method");
     *     }
     *
     *     @AfterEach//If for example, you want to execute any script to reset the values of variables or classes...
     *     void tearDown() {
     *         System.out.println("Inside the after each method");
     *     }
     *
     *     @Test
     *     public void testMethod1(){
     *         System.out.println("My first test Method");
     *     }
     *
     *     @Test
     *     public void testMethod2(){
     *         System.out.println("My second test Method");
     *     }
     */

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDTO dto = new StudentDTO(
                "TRAE",
                "Young",
                "traeyoung@gmail.com",
                1
        );

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firsName(), student.getFirsName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());

    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
       var msgExp = assertThrows(NullPointerException.class, () ->  mapper.toStudent(null));
       assertEquals("The student Dto should not be null", msgExp.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        //Given
        Student student = new Student(
               "BALL",
               "Lammel",
               "balllammel@gmail.com",
                24
       );
        //When
        StudentResponseDto responseDto = mapper.tostudentResponseDto(student);
        //Then
        assertEquals(responseDto.firsName(), student.getFirsName());
        assertEquals(responseDto.lastName(), student.getLastName());
        assertEquals(responseDto.email(), student.getEmail());
        //assertEquals();

    }

}