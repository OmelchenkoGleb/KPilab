import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Filework {
    public void newFile() throws IOException, IOException {
        System.out.println("Создаём новый файл");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла без формата.:");
        String txt = scanner.nextLine();
        String fileName = "C:\\Users\\Gleb\\IdeaProjects\\untitled\\src\\main\\resources\\" + txt + ".txt";
        File file = new File(fileName);
        if (file.createNewFile()) {
            System.out.println("Файл создан");
        } else {
            System.out.println("Ошибка (файл уже существует)");
        }
    }

    public void openFile() throws IOException {
        System.out.println("Открытие файла");
        System.out.println("Введите название файла:");
        Scanner scanner = new Scanner(System.in);
        String txt = scanner.nextLine();
        String fileName = "C:\\Users\\Gleb\\IdeaProjects\\untitled\\src\\main\\resources\\" + txt;
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Ошибка такого файла нет");
            return;
        }
        FileReader fileReader = new FileReader(fileName);
        Scanner scanFile = new Scanner(fileReader);
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Шифровка.");
        System.out.println("2. Дешифровка");
        System.out.println("Ведите выбор: ");
        int choose = sc.nextInt();
        sc = new Scanner(System.in);
        System.out.println("Ведите ключ");
        String key = sc.nextLine();
        if (choose == 1) {
            while (scanFile.hasNextLine()) {
                Vizhinera vizhinera = new Vizhinera();
                StringBuilder sb = vizhinera.encryption(scanFile.nextLine(), key);
                System.out.println(sb.toString());
            }
        } else {
            while (scanFile.hasNextLine()) {
                Vizhinera vizhinera = new Vizhinera();
                StringBuilder sb = vizhinera.decryption(scanFile.nextLine(), key);
                System.out.println(sb.toString());
            }
        }
        fileReader.close();
    }

    public void deleteFile(String fileName) {
        System.out.println("Удаление фалйа: "+fileName);
        File file = new File("C:\\Users\\Gleb\\IdeaProjects\\untitled\\src\\main\\resources\\" + fileName);
        if (file.delete()) {
            System.out.println("Файл удалён");
        } else {
            System.out.println("Ошибка удаления файла.!");
        }
    }


    public void renameFile(String fileName) {
        System.out.println("Переименовать файл: "+fileName);
        System.out.println("Введите новое имя файла");
        Scanner scanner = new Scanner(System.in);
        String txt = scanner.nextLine();
        File file = new File("C:\\Users\\Gleb\\IdeaProjects\\untitled\\src\\main\\resources\\" + fileName);
        File file1 = new File("C:\\Users\\Gleb\\IdeaProjects\\untitled\\src\\main\\resources\\" + txt + ".txt");
        if(file.renameTo(file1)) {
            System.out.println("Файл успешно переименован");
        } else {
            System.out.println("Ошибка переименования файла");
        }
    }
}
