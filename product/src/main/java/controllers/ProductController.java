package controllers;

import dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home(){
        return "welcome";
    }

    @RequestMapping(value = "/addproduct/{product_name}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> addProduct(@PathVariable String product_name){
        ResponseEntity<String> result;
        try {
            productService.addProduct(new ProductDto(product_name));
            result = new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="deleteproduct/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        ResponseEntity<String> result;
        try {
            productService.deleteProduct(id);
            result = new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id){
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
