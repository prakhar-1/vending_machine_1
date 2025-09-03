package lld.vending_machine.entities;

import lld.vending_machine.config.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inventory {
    final int inventoryId;
    Map<IProduct, Integer> productInventory;
    final int capacity;
    int occupied;

    public Inventory(int limit){
        this.capacity = limit;
        this.occupied = 0;
        inventoryId = Constants.inventoryId++;
        productInventory = new HashMap<>();
    }

    public boolean addProduct(IProduct product, int count){
        if(occupied+count <= capacity){
            productInventory.put(product, productInventory.getOrDefault(product,0) + count);
            this.occupied+= count;
            return true;
        }
        return false;
    }

    public boolean removeProduct(IProduct product, int count){
        int prodCount = productInventory.get(product);
        if(prodCount==count){
            productInventory.remove(product);
        }else if(prodCount > count){
            productInventory.put(product, prodCount - count);
        }else{
            return false;
        }
        this.occupied -= count;
        return true;
    }

    public boolean removeProduct(IProduct product){
        int prodCount = productInventory.get(product);
        return removeProduct(product, prodCount);
    }

    public int getCapacity(){
        return this.capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory inventory)) return false;
        return inventoryId == inventory.inventoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(inventoryId);
    }


    public int getOccupied() {
        return occupied;
    }

    public Map<IProduct, Integer> getProductInventory() {
        return productInventory;
    }

    public int getInventoryId() {
        return inventoryId;
    }
}
