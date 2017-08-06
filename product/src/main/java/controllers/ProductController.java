package controllers;

import config.WebAppConfig;
import dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;

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
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseBody
    public String getProduct(){
        System.out.println("----------------------");
        ProductDto product = new ProductDto("p1");
        productService.addProduct(product);
        return "fghfghdgh";
    }
}
