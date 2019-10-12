package com.example.repository;

import com.example.domain.Authority;
import com.example.domain.mapper.AuthorityDomainMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorityRepository {

    AuthorityDomainMapper mapper;


    public AuthorityRepository(AuthorityDomainMapper mapper) {
        this.mapper = mapper;
    }

    public Optional<Authority> findById(String name) {
        return this.mapper.findById(name);
    }

    public List<Authority> findAll() {
        return this.mapper.findAll();
    }
}
