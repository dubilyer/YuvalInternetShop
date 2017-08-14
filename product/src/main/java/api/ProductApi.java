package api;

import dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import logger.LoggerDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v2")
@Api(value = "ProductApi", description = "Internet shop product api")
public class ProductApi {
    @Autowired
    private ProductService productService;

    private LoggerDecorator logger = new LoggerDecorator(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Home page", notes = "Forwards to the product home page")
    public ModelAndView home() {
        logger.logRequest(RequestMethod.GET, "/");
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @RequestMapping(value = "/addproduct/{product_name}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get products", notes = "Shows all existing accounts")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        logger.logRequest(RequestMethod.GET, "/products");
        ResponseEntity<List<ProductDto>> result = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        logger.logResponse(result);
        return result;
    }

    @RequestMapping(value = "deleteproduct/{id}", method = RequestMethod.DELETE)
    @ResponseBody
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

    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Delete account", notes = "Deletes product by id")
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
