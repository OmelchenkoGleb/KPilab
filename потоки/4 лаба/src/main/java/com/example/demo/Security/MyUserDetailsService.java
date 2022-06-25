package com.example.demo.Security;

import com.example.demo.Models.admin;
import com.example.demo.repom.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        admin admin = adminRepository.findByUsername(username);
        if (admin == null){
            System.out.println(username);
            System.out.println(3);
            throw new UsernameNotFoundException("UserN=NotFound");
        }
        return new MyUserPrincipal(admin);
    }
}
