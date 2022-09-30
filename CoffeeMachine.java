public class CoffeeMachine {
    private int water = 4000;
    private int beans = 1000;
    private int cups = 100;
    private final int maxWater = 4000;
    private final int maxBeans = 1000;
    private final int maxCups = 100;
    private double bank = 0.0;

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
            buyCoffeeAlter(coffee, amount);
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
        System.out.println("Bank - " + getBank());
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
    private void buyCoffeeAlter(Coffee coffee, int amount) {
        System.out.println("Available coffee:");
        System.out.println("\t-" + coffee.getName());
        System.out.println("\t-" + checkBuyAmount(coffee, amount) + " cups");
    }
}
