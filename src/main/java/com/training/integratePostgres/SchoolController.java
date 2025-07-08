package com.training.integratePostgres;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private final SchoolRepository schoolrepository;

    public SchoolController(SchoolRepository schoolrepository) {
        this.schoolrepository = schoolrepository;
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto schooldto
    ){
        var school = toSchool(schooldto);
        var savedSchool = schoolrepository.save(school);
        return schooldto;
    }

    private School toSchool (
            SchoolDto dto
    ){
        return new School(
                dto.name()
        );
    }

    private SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAllSchool(){
        return
                schoolrepository.findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/schools/{school-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("school-id")
            Integer id
    )
    {
      schoolrepository.deleteById(id);
    }
}
