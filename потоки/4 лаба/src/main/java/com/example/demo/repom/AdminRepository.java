package com.example.demo.repom;

import com.example.demo.Models.admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<admin, Long> {
    admin findByUsername(String username);
}
