import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CoffeeMachine {
    private static int water = 4000;
    private static int beans = 1000;
    private static int cups = 100;
    private static final int maxWater = 4000;
    private static final int maxBeans = 1000;
    private static final int maxCups = 100;
    private static double bank = 0.0;

    public int getWater() {
        return water;
    }
    public void setWater(int water) {
        if (water > 0 && maxWater - this.water >= water)
            this.water = water;
    }
    public int getBeans() {
        return beans;
    }
    public void setBeans(int beans) {
        if (beans > 0 && maxBeans - this.beans >= beans)
            this.beans = beans;
    }
    public int getCups() {
        return cups;
    }
    public void setCups(int cups) {
        if (cups > 0 && maxCups - this.cups >= cups)
            this.cups = cups;
    }
    public double getBank() {
        return bank;
    }
    public void setBank(double bank) {
        if (bank > 0)
            this.bank = bank;
    }
    public void buyCoffee(Coffee coffee, int amount) {
        if (getBuyStatus(checkResultToBuy(coffee, amount))) {
            water -= coffee.getWaterUsage() * amount;
            beans -= coffee.getBeansUsage() * amount;
            cups -= coffee.getCupsUsage() * amount;
            bank += coffee.getPrice() * amount;
            getBuyInfo(coffee, amount);
        } else {
            buyCoffeeAlt(coffee, amount);
        }
    }
    private byte checkCoffee(Coffee coffee, int amount) {
        byte result = 0;
        if (coffee.getWaterUsage() * amount > water) {
            result = 1;
            return result;
        }
        if (coffee.getBeansUsage() * amount > beans) {
            result = 2;
            return result;
        }
        if (coffee.getCupsUsage() * amount > cups) {
            result = 3;
            return result;
        }
        if (amount < 1)
            result = -1;
        return result;
    }
    private boolean checkResultToBuy(Coffee coffee, int amount) {
        switch (checkCoffee(coffee, amount)) {
            case -1:
                return false;
            case 1:
                System.out.println("Low water level");
                return false;
            case 2:
                System.out.println("Low beans level");
                return false;
            case 3:
                System.out.println("No cups");
                return false;
            default:
                return true;
        }
    }
    public void getFullInfo() {
        System.out.println("-----------------------");
        System.out.println("Water - " + getWater());
        System.out.println("Beans - " + getBeans());
        System.out.println("Cups - " + getCups());
        System.out.println("Bank - " + String.format("%.2f", getBank()));
    }
    private void getBuyInfo(Coffee coffee, int amount) {
        System.out.println("You bought:");
        System.out.println("\tCoffee - " + coffee.getName());
        System.out.println("\tAmount - " + amount);
        System.out.println("\tPrice - " + coffee.getPrice() * amount);
    }
    private boolean getBuyStatus(boolean status) {
        if (status) {
            System.out.println("Status: Success");
            return true;
        } else {
            System.out.println("Status: Failed");
            return false;
        }
    }
    private int checkBuyAmount(Coffee coffee, int amount) {
        while (checkCoffee(coffee, amount) != 0) {
            if (amount < 1)
                break;
            amount--;
        }
        return amount;
    }
    private void buyCoffeeAlt(Coffee coffee, int amount) {
        System.out.println("Available coffee:");
        System.out.println("\t-" + coffee.getName());
        System.out.println("\t-" + checkBuyAmount(coffee, amount) + " cups");
    }
    public void withdrawMoney() {
        setBank(0);
    }
    public void refill() {
        setBeans(maxBeans);
        setWater(maxWater);
        setCups(maxCups);
    }
    public void refill(int water, int beans, int cups) {
        setBeans(beans);
        setWater(water);
        setCups(cups);
    }
    public void saveFile() {
        try (FileWriter writer = new FileWriter("Info.txt", false)) {
            String text = String.valueOf(water) + " " +
                    String.valueOf(beans) + " " +
                    String.valueOf(cups) + " " +
                    String.valueOf(String.format("%.2f", getBank()));
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void readFile() {
        try (FileReader reader = new FileReader("Info.txt")) {
            int c;
            int j = 0;
            String[] savedNums = {"", "", "", ""};
            while ((c = reader.read()) != -1) {
                if (c == 32) {
                    j++;
                    continue;
                }
                if ((char)c == ',')
                    c = '.';
                savedNums[j] += (char)c;

            }
            water = Integer.parseInt(savedNums[0]);
            beans = Integer.parseInt(savedNums[1]);
            cups = Integer.parseInt(savedNums[2]);
            bank = Double.parseDouble(savedNums[3]);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void startWork() throws IOException {
        boolean checkLoop = true;
        while (checkLoop) {
            System.out.println("\t\tCoffee available:");
            for (int i = 0; i < Coffee.coffeeList.size(); i++) {
                System.out.println("- " + String.format("%-15s %.2f$", Coffee.coffeeList.get(i).getName(), Coffee.coffeeList.get(i).getPrice()));
            }
            System.out.println("Buy - 1 | Refill - 2 | Info - 3 | Close - q");
            switch (System.in.read()) {
                case '1':
                    System.out.println("-------------------------");
                    for (int i = 0; i < Coffee.coffeeList.size(); i++) {
                        System.out.println(i + " - " + Coffee.coffeeList.get(i).getName());
                    }
                    System.in.read();
                    int coffeeBuyRes = System.in.read();
                    System.out.print("How many cups: ");
                    System.in.read();
                    int cupsAmount = System.in.read();
                    buyCoffee(Coffee.coffeeList.get(coffeeBuyRes - 48), cupsAmount - 48);
                    System.out.println("-------------------------");
                    break;
                case '2':
                    refill();
                    break;
                case '3':
                    getFullInfo();
                    break;
                case 'q':
                    checkLoop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("Exit");
    }
}
