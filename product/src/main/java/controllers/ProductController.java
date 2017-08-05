package controllers;

import dto.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ProductController {
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(){
        Product product = new Product(111l, "p1");
        return product;
    }
}
