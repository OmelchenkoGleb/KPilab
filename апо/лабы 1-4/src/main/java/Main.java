import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Release release = new Release();
        release.AddCommand(new Controller());
        release.RunCommand();

        System.out.println("-------");

        ProxxyPattern proxxyPattern = new ProxxyPattern();
        proxxyPattern.execute(scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());
    }
    static class Release{
        List <Command> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        void AddCommand(Command newCommand) {
            list.add(newCommand);
        }
        void RunCommand(){
            System.out.println("Введите, пожалуйста, данные:");
            list.forEach(command -> {command.execute(scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());});
        }
    }
}
