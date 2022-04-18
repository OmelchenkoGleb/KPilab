package com.example.save.savepo.Dao;

import com.example.save.savepo.Model.lab2;
import com.example.save.savepo.Repository.lab2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;


@Component
public class image {

    @Autowired
    lab2Repository lab2Repository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String save(String name, MultipartFile file) throws IOException {

//        byte[] i = file.getBytes();
//        BufferedImage img = ImageIO.read(new File(String.valueOf(file)));
//        OutputStream outputStream = lab2.getBlob().setBinaryStream(1)
        try {
            lab2 lab2 = new lab2();
            lab2.setName(name);
            lab2.setBytes(file.getBytes());
            lab2Repository.save(lab2);
        return "Картинка загружена !";
        }catch (IOException e) {
            e.printStackTrace();
            return "Какая-то ошибка";
        }
    }


//
//
//
//    public void writeImageToRespose(Long id, HttpServletResponse response) throws IOException, SQLException {
////        //store image in browser cache
////        response.setContentType("image/jpeg" + "image/jpg" + "image/png" + "image/gif");
////        response.setHeader("Cache-Control", "max-age=2628000");
////
//////        //obtaining bytes from DB
//////        byte[] imageData = someEntityDao.getPhotoById(id);
//////
//////        //Some conversion
//////        //Maybe to base64 string or something else
//////        //Pay attention to encoding (UTF-8, etc)
//////
//////        //write result to http response
//////        try (OutputStream out = response.getOutputStream()) {
//////            out.write(convertedStringBytes);
//////        }
//
//
//        Blob blob2 = lab2Repository.findAllById(id).getBlob();
//        BufferedImage bufferedImage = ImageIO.read(blob2.getBinaryStream());
//    }
}
