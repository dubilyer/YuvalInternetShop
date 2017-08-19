package api;
import dto.AccountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import logger.LoggerDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.AccountService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController(value = "account")
@ComponentScan("services")
@RequestMapping(value = "/api/v2")
@Api(value = "AccountApi", description = "Internet shop accounts api")
public class AccountApi {
    @Autowired
    private AccountService accountService;

    private LoggerDecorator logger = new LoggerDecorator(this.getClass());

    @GetMapping(value = "/")
    @ResponseBody
    @ApiOperation(value = "Home page", notes = "Forwards to the account home page")
    public ModelAndView home() {
        logger.logRequest(RequestMethod.GET, "/");
        return new ModelAndView("redirect:/swagger-ui.html");
    }


    @PutMapping(value = "/addaccount/{account_name}")
    @ResponseBody
    @ApiOperation(value = "Add account", notes = "Adds a new account")
    public ResponseEntity<String> addAccount(@PathVariable String account_name) {
        logger.logRequest(RequestMethod.PUT, "/addaccount/" + account_name);
        ResponseEntity<String> result;
        try {
            accountService.addAccount(new AccountDto(account_name));
            result = new ResponseEntity<>("success", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.logResponse(result);
        return result;
    }

    @GetMapping(value = "/accounts")
    @ResponseBody
    @ApiOperation(value = "Get accounts", notes = "Shows all existing accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        logger.logRequest(RequestMethod.GET, "/accounts");
        ResponseEntity<List<AccountDto>> result = new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
        logger.logResponse(result);
        return result;
    }

    @DeleteMapping(value = "deleteaccount/{id}")
    @ResponseBody
    @ApiOperation(value = "Delete account", notes = "Deletes account by id")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {
        logger.logRequest(RequestMethod.DELETE, "deleteaccount/" + id);
        ResponseEntity<String> result;
        try {
            accountService.deleteAccount(id);
            result = new ResponseEntity<>("success", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            result = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.logResponse(result);
        return result;
    }

    @GetMapping(value = "accounts/{id}")
    @ResponseBody
    @ApiOperation(value = "Get Account", notes = "Shows account with specified id")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable long id) {
        logger.logRequest(RequestMethod.GET, "accounts/" + id);
        ResponseEntity<AccountDto> result;
        try {
            result = new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.logResponse(result);
        return result;
    }
}
