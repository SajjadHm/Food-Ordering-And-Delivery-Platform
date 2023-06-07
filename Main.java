import org.w3c.dom.html.HTMLIsIndexElement;
import view.MainMenu;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();
        /*
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy").appendLiteral("/")
                .appendPattern("MM").appendLiteral("/")
                .appendPattern("dd").appendLiteral("  ")
                .appendPattern("HH").appendLiteral(":")
                .appendPattern("mm").appendLiteral(":")
                .appendPattern("ss")
                .toFormatter();
        System.out.println(LocalDateTime.now().format(formatter));
        LocalDateTime time = LocalDateTime.of(2023, 6, 5, 16, 6, 7);
        System.out.println(time.format(formatter));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int a = LocalDateTime.now().compareTo(time);
        System.out.println(a);

         */
    }
}
