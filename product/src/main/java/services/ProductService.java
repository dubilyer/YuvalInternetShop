package services;

import dao.ProductRepository;
import dto.ProductDto;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("productService")
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void addProduct(ProductDto productDto){
        productRepository.addProduct(convertDtoToProduct(productDto));
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products =  productRepository.getAllProducts();
        List <ProductDto> result = new LinkedList<>();
        products.forEach(p -> result.add(new ProductDto(p.getId(), p.getName())) );
        return result;
    }

    public void deleteProduct(long id){
        productRepository.deleteProduct(id);
    }

    public ProductDto getProductById(long id) throws NoSuchElementException{
        Product product = productRepository.getProductById(id);
        return convertProductToDto(product);
    }

    private ProductDto convertProductToDto(Product product) {
        return new ProductDto(product.getId(), product.getName());
    }

    private Product convertDtoToProduct(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getName());
    }
}
