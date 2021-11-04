import java.util.ArrayList;
import java.util.Scanner;

public class ProxxyPattern implements Command {
    Command command = new Controller();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute(Double x, Double y, Double height, Double width) {

        System.out.println("Происходит проверка так, чтобы каждое число было не меньше 5 иначе оно будет автоматически 1:");
        if (x<5) x = 1.;
        if (x<5) y = 1.;
        if (x<5) height = 1.;
        if (x<5) width = 1.;

        command.execute(x, y, height, width);
    }
}
