package com.example.save.savepo.Repository;


import com.example.save.savepo.Model.lab2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface lab2Repository extends CrudRepository<lab2, Long> {
    lab2 findAllById(Long id);
    List<lab2> findAllBy();

    @Query("select id from lab2")
    List<Long> findAllId();
}
