import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetLocalNumber() {
        MainClass mainClass = new MainClass();
        if (mainClass.getLocalNumber() == 14)
            System.out.println("Вернулось число 14");
        else System.out.println("Вернулось НЕ число 14");
    }
}