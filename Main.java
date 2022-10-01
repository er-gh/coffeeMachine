import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CoffeeMachine.readFile();
        CoffeeMachine cm = new CoffeeMachine();
        Coffee latte = new Coffee("Latte", 259.99, 50, 20, 1);
        Coffee cappuccino = new Coffee("Cappuccino", 359.99, 50, 20, 1);
        Coffee americano = new Coffee("Americano", 159.99, 100, 20, 1);
        cm.startWork();
        cm.saveFile();
    }
}