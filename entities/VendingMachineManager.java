package lld.vending_machine.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VendingMachineManager {

    List<Inventory> inventoryList;
    List<IVendingMachine> vmList;

    public VendingMachineManager(List<Inventory> inventoryList, List<IVendingMachine> vmList){
        this.inventoryList = inventoryList;
        this.vmList = vmList;
    }

    public void addVendinMachine(IVendingMachine newVM){
        vmList.add(newVM);
    }

    public void addInventory(Inventory newInventory){
        inventoryList.add(newInventory);
    }

    public void removeInventory(Inventory inventory){
        inventoryList.remove(inventory);
    }

    public void removeVendingMachine(IVendingMachine vm){
        vmList.remove(vm);
    }

    public Map<IProduct,Integer> getInventoryStock(Inventory inventory){
        Map<IProduct, Integer> productData = new HashMap<>();
        if(inventory!=null){
            productData = inventory.getProductInventory();
        }
        return productData;
    }

    public boolean removeProductFromInventory(IProduct product, Inventory inventory){
        return inventory.removeProduct(product);
    }

    public boolean addProductInInventory(IProduct product, int count, Inventory inventory){
        return inventory.addProduct(product, count);
    }

    public boolean addProductToVendingMachine(IProduct product, Inventory inventory, IVendingMachine vm, int count){
        boolean removeFromInventory = inventory.removeProduct(product,count);
        if(removeFromInventory){
            if(vm.isProductAvailable(product) && vm.addProduct(product,count)){
                return true;
            }
            inventory.addProduct(product,count);
        }
        return false;
    }

    public boolean addNewProductToVendingMachine(IProduct product, IVendingMachine vm, int capacity){
        if(!vm.isProductAvailable(product) && vm.addNewProduct(product,capacity)){
            return true;
        }
        return false;
    }

    public boolean removeOldProductFromVendingMachine(IProduct product, IVendingMachine vm){
        int count =  vm.removeOldProduct(product);
        if(count==0) return false;
        Random rand = new Random();
        int idx = rand.nextInt(inventoryList.size());
        inventoryList.get(idx).addProduct(product, count);
        return true;
    }

    public boolean removeProductToVendingMachine(IProduct product, Inventory inventory, IVendingMachine vm, int count){
        if(vm.isProductAvailable(product) && vm.removeProduct(product,count)){
            inventory.addProduct(product,count);
            return true;
        }
        return false;
    }


}
