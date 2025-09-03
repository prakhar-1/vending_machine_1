package lld.vending_machine.entities;

public interface IProduct {
    void printProductDesc();
    double getProductPrice();
    String getProductName();
    int getProductId();
}
