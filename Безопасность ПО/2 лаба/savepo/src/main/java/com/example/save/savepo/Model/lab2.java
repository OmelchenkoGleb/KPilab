package com.example.save.savepo.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;


@Getter
@Setter
@Entity
@Table(name = "lab2")
public class lab2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "img")
    @Lob()
    private byte[] bytes;

    @Override
    public String toString() {
        return "lab2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }

    public lab2(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public lab2(Long id, String name, byte[] bytes) {
        this.id = id;
        this.name = name;
        this.bytes = bytes;
    }

    public lab2() {
    }
}
