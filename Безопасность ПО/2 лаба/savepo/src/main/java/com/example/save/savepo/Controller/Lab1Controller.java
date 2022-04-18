package com.example.save.savepo.Controller;

import com.example.save.savepo.Dao.Filework;
import com.example.save.savepo.Dao.Vizhinera;
import com.example.save.savepo.Dao.image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class Lab1Controller {

    @Autowired
    Filework filework;

    @Autowired
    image image;

    @GetMapping("/choose")
    public String a(Model model){
        model.addAttribute("option", Filework.option());
        return "/lab1/start";
    }

    @PostMapping("/lab1")
    public String choose(Model model, @RequestParam String name){
        AtomicReference<String> res = new AtomicReference<>("");
        Filework.option().forEach(x->{
            if (Objects.equals(name, x)) {
                res.set("/lab1/" + name);
            }
        });
        if (res.get().equals("")) return "/lab1/start";
        else return res.get();
    }

    @PostMapping("/lab1/encryption")
    public String encryptionlab1(Model model, @RequestParam String text, @RequestParam String key){
        Vizhinera vizhinera = new Vizhinera();
        model.addAttribute("result", vizhinera.encryption(text, key).toString());
        return "/lab1/Шифровка";
    }

    @PostMapping("/lab1/decryption")
    public String decryptionlab1(Model model, @RequestParam String text, @RequestParam String key){
        Vizhinera vizhinera = new Vizhinera();
        model.addAttribute("result", vizhinera.decryption(text, key).toString());
        return "/lab1/Дешифровка";
    }

    @PostMapping("/lab1/createfile")
    public String createfile(Model model, @RequestParam String place, @RequestParam String name) throws IOException {
        model.addAttribute("result", filework.newFile(place, name));
        return "/lab1/Создать новый файл";
    }

    @PostMapping("/lab1/openfile")
    public String openfile(Model model, @RequestParam String place, @RequestParam String name, @RequestParam String key) throws IOException {
        model.addAttribute("result", filework.openFile(place,key,name));
        return "/lab1/Открыть файл";
    }

    @PostMapping("/lab1/renamefile")
    public String renamefile(Model model, @RequestParam String place, @RequestParam String name, @RequestParam String newname) throws IOException {
        model.addAttribute("result", filework.renameFile(place, name, newname));
        return "/lab1/Переименовать";
    }


}
