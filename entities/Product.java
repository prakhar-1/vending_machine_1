package lld.vending_machine.entities;

import java.util.Objects;

public class Product implements IProduct{
    private final String name;
    private final int id;
    private final String desc;
    private final double price;

    public Product(String name, int id, String desc, double price) {
        this.name = name;
        this.id = id;
        this.desc = desc;
        this.price = price;
    }

    @Override
    public void printProductDesc() {
        System.out.println(this);
    }

    @Override
    public double getProductPrice() {
        return this.price;
    }

    @Override
    public String getProductName() {
        return this.name;
    }

    @Override
    public int getProductId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
