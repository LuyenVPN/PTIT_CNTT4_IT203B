package MiniProjectSs9;

public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name,double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + "| Name: "+name+"| Price: " + price + "| Weight: " + weight + "kg" + "| Type: Physical Product");
    }
}
