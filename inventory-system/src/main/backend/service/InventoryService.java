package backend;

import backend.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class InventoryService {
    private List<Product> inventory = new ArrayList<>();

    public void addProduct(Product product) {
        inventory.add(product);
    }

    // pod 5 nalichni = avtomatichno izvestie 
    public void updateStock(int productId, int newQuantity) {
        for (Product p : inventory) {
            if (p.getId() == productId) {
                p.setQuantity(newQuantity);
                if (newQuantity < 5) {
                    System.out.println("ALERT: Ниска наличност за " + p.getName());
                }
            }
        }
    }

    // Izhislenie na obshtata stoynost 
    public double calculateTotalValue() {
        return inventory.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
    }
}