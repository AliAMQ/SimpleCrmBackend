package com.practice1.repository;

import com.practice1.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alireza on 2/4/2018.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
