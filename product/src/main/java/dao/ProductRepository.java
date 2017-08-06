package dao;

import model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    void addProduct(Product product);
    void deleteProduct(Product product);
}
