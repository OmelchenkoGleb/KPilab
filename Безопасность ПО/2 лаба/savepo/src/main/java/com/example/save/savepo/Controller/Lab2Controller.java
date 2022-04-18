package com.example.save.savepo.Controller;

import com.example.save.savepo.Dao.Filework;
import com.example.save.savepo.Dao.image;
import com.example.save.savepo.Model.lab2;
import com.example.save.savepo.Repository.lab2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class Lab2Controller {
    @Autowired
    image image;

    @Autowired
    lab2Repository lab2Repository;

    @Autowired
    Filework filework;

    @PostMapping("/lab2/shareimg")
    public String shareimg(Model model, @RequestParam String name, @RequestParam("file") MultipartFile file) throws IOException {
        model.addAttribute("result", image.save(name, file));
        return "/lab1/Загрузить картинку";
    }

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        lab2 img = lab2Repository.findAllById(id);
        return ResponseEntity.ok().body(new InputStreamResource(new ByteArrayInputStream(img.getBytes())));
    }

//    @PostMapping("/lab1/deletefile")
//    public String deletefile(Model model, @RequestParam String place) throws IOException {
//        model.addAttribute("result", filework.deleteFile(place));
//        return "/lab1/Удалить файл";
//    }

    @PostMapping("/lab1/deletefile")
    public String deletefile(Model model, @RequestParam String place) throws IOException {
        List<lab2> listimages = lab2Repository.findAllBy();

        List<Long> allid = lab2Repository.findAllId();
        int id = (int) (Math.random()*allid.size());
        model.addAttribute("place", place);
        model.addAttribute("listimages", lab2Repository.findAllById(allid.get(id)));
        return "/lab2/lab2";
    }

    @PostMapping("/lab2/delete/{id}")
    public String delete(Model model, @PathVariable Long id, @RequestParam String name,@RequestParam String place) throws IOException {
        if (name.equals(lab2Repository.findAllById(id).getName())) {
            System.out.println(place);
            model.addAttribute("result", filework.deleteFile(place));
        } else {
            model.addAttribute("result", "Не прошли проверку");
        }
        return "/lab1/Удалить файл";
    }
}
