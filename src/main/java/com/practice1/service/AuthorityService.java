package com.practice1.service;

import com.practice1.model.Authority;
import com.practice1.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * Created by Alireza on 2/6/2018.
 */

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public ResponseEntity<?> addAuthority(@RequestBody @Validated Authority authority){
        Authority authority1 = new Authority();
        authority1.setTitle(authority.getTitle());
        authorityRepository.save(authority1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateAuthority(@RequestBody @Validated Authority authority){
        Authority authority1 = authorityRepository.findOne(authority.getId());
        authority1.setTitle(authority.getTitle());
        authorityRepository.save(authority1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteAuthority(Long id){
        authorityRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public Authority getOneAuthority(Long id){
        return authorityRepository.findOne(id);
    }

    public Collection<Authority> getAllAuthorities(){
        return authorityRepository.findAll();
    }

}
