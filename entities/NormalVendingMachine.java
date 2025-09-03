package lld.vending_machine.entities;

import lld.vending_machine.config.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NormalVendingMachine implements IVendingMachine{
    final int id;
    final int capacity;
    final Map<IProduct, Integer> availableProduct;
    final Map<IProduct, Integer> productCapacity;
    int occupied;

    public NormalVendingMachine(NormalVendingMachineBuilder builder){
        this.id = Constants.vendingMachineId++;
        this.capacity = builder.capacity;
        this.availableProduct = builder.availableProduct;
        this.productCapacity = builder.productCapacity;
        this.occupied = builder.occupied;
    }

    public boolean addNewProduct(IProduct product, int capacity){
        int currCapacity = 0;
        for(Map.Entry<IProduct, Integer> e: this.productCapacity.entrySet()){
            currCapacity += e.getValue();
        }
        if(currCapacity+capacity > this.capacity){
            return false;
        }else{
            this.productCapacity.put(product, capacity);
            return true;
        }
    }

    public int removeOldProduct(IProduct product){
        if(this.productCapacity.containsKey(product)){
            int curr = this.availableProduct.get(product);
            occupied -= curr;
            this.productCapacity.remove(product);
            this.availableProduct.remove(product);
            return curr;
        }
        return 0;
    }

    public boolean addProduct(IProduct product, int count){
        int updatedCount = count+this.availableProduct.getOrDefault(product,0);
        if(!this.productCapacity.containsKey(product)
                || this.productCapacity.get(product) < updatedCount){
            return false;
        }
        this.availableProduct.put(product, updatedCount);
        occupied += count;
        return true;
    }

    public boolean removeProduct(IProduct product, int count){
        int updatedCount = this.availableProduct.getOrDefault(product,0)-count;
        if(!this.productCapacity.containsKey(product) || updatedCount<0){
            return false;
        }
        this.availableProduct.put(product, updatedCount);
        occupied -= count;
        return true;
    }

    public boolean removeProduct(int productID, int count){
        for (Map.Entry<IProduct, Integer> entry : this.productCapacity.entrySet()) {
            if (entry.getKey().getProductId() == productID) {
                return removeProduct(entry.getKey(), count);
            }
        }
        return false;
    }

    public boolean isProductAvailable(IProduct product){
        return productCapacity.containsKey(product);
    }


    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<IProduct, Integer> getAvailableProduct() {
        return availableProduct;
    }

    public Map<IProduct, Integer> getProductCapacity() {
        return productCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalVendingMachine that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class NormalVendingMachineBuilder{
        final int capacity;
        final Map<IProduct, Integer> availableProduct;
        final Map<IProduct, Integer> productCapacity;
        int occupied;

        public NormalVendingMachineBuilder(int capacity) {
            this.capacity = capacity;
            this.productCapacity = new HashMap<>();
            this.availableProduct = new HashMap<>();
            this.occupied = 0;
        }

        public NormalVendingMachineBuilder addProductCapacity(IProduct product, int capacity){
            this.productCapacity.put(product, capacity);
            return this;
        }

        public NormalVendingMachineBuilder addProduct(IProduct product, int count){
            if(this.productCapacity.containsKey(product) && this.productCapacity.get(product) > count){
                this.availableProduct.put(product, count);
                occupied+= count;
            }
            return this;
        }

        public NormalVendingMachine build(){
            return new NormalVendingMachine(this);
        }
    }

}
