package org.dipayan.SpringStarter.services;

import java.util.Optional;

import org.dipayan.SpringStarter.models.Authority;
import org.dipayan.SpringStarter.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    
    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    public Optional<Authority> findById(Long id) {
        return authorityRepository.findById(id);
    }

}
