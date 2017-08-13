package services;

import dao.AccountRepository;
import dto.AccountDto;
import model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("accountService")
@Transactional
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public void addAccount(AccountDto accountDto){
        accountRepository.addAccount(convertDtoToAccount(accountDto));
    }

    public List<AccountDto> getAllAccounts(){
        List<Account> accounts =  accountRepository.getAllAccounts();
        List <AccountDto> result = new LinkedList<>();
        accounts.forEach(p -> result.add(new AccountDto(p.getId(), p.getName())) );
        return result;
    }

    public void deleteAccount(long id){
        accountRepository.deleteAccount(id);
    }

    public AccountDto getAccountById(long id) throws NoSuchElementException{
        Account account = accountRepository.getAccountById(id);
        return convertAccountToDto(account);
    }

    private AccountDto convertAccountToDto(Account account) {
        return new AccountDto(account.getId(), account.getName());
    }

    private Account convertDtoToAccount(AccountDto accountDto){
        return new Account(accountDto.getId(), accountDto.getName());
    }
}

