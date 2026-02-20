package backend;

import backend.model.Product;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private List<Product> inventory = new ArrayList<>();

    // Добавяне на продукт 
    public void addProduct(Product product) {
        inventory.add(product);
    }

}