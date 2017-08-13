package api;
import dto.AccountDto;
import logger.LoggerDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AccountService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ComponentScan("services")
@RequestMapping(value = "/api/v2")
public class AccountApi {
    @Autowired
    private AccountService accountService;

    private LoggerDecorator logger = new LoggerDecorator(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> home() {
        logger.logRequest(RequestMethod.GET, "/");
        ResponseEntity<String> result = new ResponseEntity<>("redirect:/api/swagger-ui", HttpStatus.OK);
        logger.logResponse(result);
        return result;
    }

    @RequestMapping(value = "/addaccount/{account_name}", method = RequestMethod.PUT)
    @ResponseBody
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

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        logger.logRequest(RequestMethod.GET, "/accounts");
        ResponseEntity<List<AccountDto>> result = new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
        logger.logResponse(result);
        return result;
    }

    @RequestMapping(value = "deleteaccount/{id}", method = RequestMethod.DELETE)
    @ResponseBody
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

    @RequestMapping(value = "accounts/{id}", method = RequestMethod.GET)
    @ResponseBody
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
