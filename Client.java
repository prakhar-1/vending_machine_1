package lld.vending_machine;

import lld.vending_machine.entities.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Inventory inventory1 = new Inventory(10000);
        IProduct parleG = new Product("PARLEG", 1, "Biscuit", 10);
        IProduct pepsi = new Product("PEPSI", 2, "Cold Drink", 50);
        IVendingMachine VM1 = new NormalVendingMachine.NormalVendingMachineBuilder(1000).
                addProductCapacity(parleG, 20).
                addProduct(parleG, 10).
                addProductCapacity(pepsi, 20).
                addProduct(pepsi, 10).build();
        VendingMachineManager VMManager = new VendingMachineManager(List.of(inventory1), List.of(VM1));
        VMManager.addProductInInventory(parleG, 50, inventory1);
        VMManager.addProductInInventory(pepsi, 40, inventory1);

        User user1 = new User("Praks");
        Map<IProduct, Integer> availableProduct = VM1.getAvailableProduct();
        System.out.println("Available Products in this Vending Machine");
        for (Map.Entry<IProduct, Integer> entry : availableProduct.entrySet()) {
            System.out.println(entry.getKey());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select product ID");
        int productID = scanner.nextInt();
        boolean status = VM1.removeProduct(productID, 1);
        if (status)
            System.out.println("Your product is dispatched");
        else
            System.out.println("Sorry, some issue occurred");
    }
}
