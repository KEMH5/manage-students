package com.training.integratePostgres;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto schooldto
    ){
        return this.schoolService.create(schooldto);
    }


    @GetMapping("/schools")
    public List<SchoolDto> findAllSchool(){
        return this.schoolService.findAllSchool();
    }

    @DeleteMapping("/schools/{school-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("school-id")
            Integer id
    )
    {
      schoolService.delete(id);
    }
}
