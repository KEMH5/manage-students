package com.training.integratePostgres;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto create(
            SchoolDto schooldto
    ){
        var school = schoolMapper.toSchool(schooldto);
        schoolRepository.save(school);
        return schoolMapper.toSchoolDto(school);
    }

    public List<SchoolDto> findAllSchool(){
        return
                schoolRepository.findAll()
                        .stream()
                        .map(schoolMapper::toSchoolDto)
                        .collect(Collectors.toList());
    }

    public void delete(
            Integer id
    )
    {
        schoolRepository.deleteById(id);
    }
}

