public class Main {
    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine();
        Coffee latte = new Coffee("Latte", 259.99, 50, 20, 1);
        Coffee cappuccino = new Coffee("Cappuccino", 359.99, 50, 20, 1);
        Coffee americano = new Coffee("Americano", 159.99, 100, 20, 1);
        cm.buyCoffee(latte, 4);
        cm.buyCoffee(cappuccino, 36);
        cm.buyCoffee(americano, 10);
        cm.buyCoffee(americano, 1);
        cm.getFullInfo();
    }
}