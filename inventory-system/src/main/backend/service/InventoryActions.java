package backend.service;

import backend.model.Product;

public interface InventoryActions {
    void addProduct(Product product);
    void updateStock(int productId, int newQuantity);
    double calculateTotalValue();
}