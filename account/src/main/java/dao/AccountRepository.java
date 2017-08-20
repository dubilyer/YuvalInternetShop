package dao;

import model.Account;

import java.util.List;
import java.util.NoSuchElementException;

public interface AccountRepository {
    List<Account> getAllAccounts();
    void addAccount(Account account) throws NoSuchElementException;
    void deleteAccount(long id) throws NoSuchElementException;
    Account getAccountById(long id) throws NoSuchElementException;
    Account getAccountByName(String name) throws NoSuchElementException;
}