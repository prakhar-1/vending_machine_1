package lld.vending_machine.entities;

import java.util.Map;

public interface IVendingMachine {
    boolean addNewProduct(IProduct product, int capacity);
    int removeOldProduct(IProduct product);
    boolean addProduct(IProduct product, int count);
    boolean removeProduct(IProduct product, int count);
    boolean removeProduct(int productID, int count);
    boolean isProductAvailable(IProduct product);
    Map<IProduct, Integer> getAvailableProduct();
}
