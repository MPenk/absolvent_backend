package com.umg.absolwentbackend.services;

import com.umg.absolwentbackend.exceptions.AuthenticationException;
import com.umg.absolwentbackend.models.Graduate;
import com.umg.absolwentbackend.repositories.GraduateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class GraduateService {

    @Autowired
    GraduateRepository graduateRepository;

    public Graduate addGraduate(String name, String lastName, String email, int graduation_year, String faculty, String field, Date date_of_birth, String title) throws AuthenticationException {
        Integer count = graduateRepository.countbyemail(email);
        if(count>0) {
            throw new AuthenticationException("Graduate already exists");
        }
        Integer userid = graduateRepository.insertGraduate(name,lastName,email,graduation_year,faculty,field,date_of_birth,title);
        return graduateRepository.findbyid(userid);
    }
}
