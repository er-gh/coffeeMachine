public class Coffee {
    private String name;
    private double price;
    private int waterUsage;
    private int beansUsage;
    private int cupsUsage;

    public Coffee(String name, double price, int waterUsage, int beansUsage, int cupsUsage) {
        this.name = name;
        this.price = price;
        this.waterUsage = waterUsage;
        this.beansUsage = beansUsage;
        this.cupsUsage = cupsUsage;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getWaterUsage() {
        return waterUsage;
    }
    public int getBeansUsage() {
        return beansUsage;
    }
    public int getCupsUsage() {
        return cupsUsage;
    }
}
