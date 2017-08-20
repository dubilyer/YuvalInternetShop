package api;

import apihelper.helper.ApiHelper;
import apihelper.pojo.AccountPojo;
import dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import logger.LoggerDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v2")
@Api(value = "ProductApi", description = "Internet shop product api")
public class ProductApi {
    @Autowired
    private ProductService productService;

    private LoggerDecorator logger = new LoggerDecorator(this.getClass());

    @GetMapping(value = "/")
    @ResponseBody
    @ApiOperation(value = "Home page", notes = "Forwards to the product home page")
    public ModelAndView home() {
        logger.logRequest(RequestMethod.GET, "/");
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @PutMapping(value = "/addproduct/{product_name}")
    @ResponseBody
    @ApiOperation(value = "Add product", notes = "Adds a new product")
    public ResponseEntity<String> addProduct(@PathVariable String product_name) {
        logger.logRequest(RequestMethod.PUT, "/addproduct/" + product_name);
        ResponseEntity<String> result;
        try {
            productService.addProduct(new ProductDto(product_name));
            result = new ResponseEntity<>("success", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.logResponse(result);
        return result;
    }

    @GetMapping(value = "/products")
    @ResponseBody
    @ApiOperation(value = "Get products", notes = "Shows all existing products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        logger.logRequest(RequestMethod.GET, "/products");
        ResponseEntity<List<ProductDto>> result = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        logger.logResponse(result);
        return result;
    }

    @DeleteMapping(value = "deleteproduct/{id}")
    @ResponseBody
    @ApiOperation(value = "Delete product", notes = "Deletes product by id")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        logger.logRequest(RequestMethod.DELETE, "deleteproduct/" + id);
        ResponseEntity<String> result;
        try {
            productService.deleteProduct(id);
            result = new ResponseEntity<>("success", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.logResponse(result);
        return result;
    }

    @GetMapping(value = "products/{id}")
    @ResponseBody
    @ApiOperation(value = "Get product by id", notes = "Shows product with specified id")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id) {
        logger.logRequest(RequestMethod.GET, "products/" + id);
        ResponseEntity<ProductDto> result;
        try {
            result = new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.logResponse(result);
        return result;
    }
}
