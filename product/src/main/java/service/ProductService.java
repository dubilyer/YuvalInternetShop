package service;

import dao.ProductRepository;
import dto.ProductDto;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void addProduct(ProductDto productDto){
        productRepository.addProduct(new Product(productDto.getId(), productDto.getName()));
    }
}
