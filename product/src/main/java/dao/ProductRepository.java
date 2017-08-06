package dao;

import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public interface ProductRepository {
    List<Product> getAllProducts();
    void addProduct(Product product) throws NoSuchElementException;
    void deleteProduct(long id) throws NoSuchElementException;
    Product getProductById(long id) throws NoSuchElementException;
}
