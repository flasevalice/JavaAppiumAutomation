import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetLocalNumber() {
        MainClass mainClass = new MainClass();
        if (mainClass.getLocalNumber() == 14)
            System.out.println("Вернулось число 14");
        else System.out.println("Вернулось НЕ число 14");
    }

    @Test
    public void testGetClassNumber() {
        MainClass mainClass = new MainClass();
        if (mainClass.getClassNumber()>45)
            System.out.println("Вернулось число больше 45");
        else System.out.println("Вернулось число меньше или равно 45");
    }
}