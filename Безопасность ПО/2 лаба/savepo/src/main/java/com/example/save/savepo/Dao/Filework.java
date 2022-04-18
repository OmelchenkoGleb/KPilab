package com.example.save.savepo.Dao;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class Filework {
    public String newFile(String place, String name) throws IOException, IOException {
        String fileName = place + name + ".txt";
        File file = new File(fileName);
        if (file.createNewFile()) {
            return "Файл создан";
        } else {
            return "Ошибка (файл уже существует)";
        }
    }

    public String openFile(String place, String key, String name) throws IOException {
        Vizhinera vizhinera = new Vizhinera();
        FileReader fileReader = new FileReader(place);
        Scanner scanFile = new Scanner(fileReader);
        StringBuilder result = new StringBuilder();
        if (Objects.equals(name, "Шифровка")) while (scanFile.hasNextLine()) result.append(vizhinera.encryption(scanFile.nextLine(), key).append(" "));
        if (Objects.equals(name, "Дешифровка")) while (scanFile.hasNextLine()) result.append(vizhinera.decryption(scanFile.nextLine(), key).append(" "));
        return result.toString();
    }

    public String deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            return "Файл удалён";
        } else {
            return "Ошибка удаления файла.!";
        }
    }


    public String renameFile(String place, String name, String newname) {
        File file = new File(place + name);
        File file1 = new File(place + "\\" + newname + ".txt");
        if(file.renameTo(file1)) {
            return "Файл успешно переименован";
        } else {
            return "Ошибка переименования файла";
        }
    }

    public static List<String> option(){
        return List.of("Шифровка", "Дешифровка", "Создать новый файл", "Открыть файл", "Удалить файл", "Переименовать", "Загрузить картинку");
    }
}
