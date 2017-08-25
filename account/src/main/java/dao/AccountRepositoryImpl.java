package dao;

import model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SuppressWarnings("unchecked")
    @Override
    public List<Account> getAllAccounts() {
        return getSession()
                .getNamedQuery("GET_ALL_ACCOUNTS")
                .list();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void addAccount(Account account) throws NoSuchElementException {
        getSession().persist(account);
        getAccountById(account.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteAccount(long id) throws NoSuchElementException {
        Account account = getAccountById(id);
        getSession().delete(account);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SuppressWarnings("unchecked")
    @Override
    public Account getAccountById(long id) throws NoSuchElementException{
        List<Account> accounts =  getSession()
                .getNamedQuery("GET_ACCOUNT_BY_ID")
                .setParameter("id", id)
                .getResultList();
        if (accounts.size()==0){
            throw new NoSuchElementException("There's no account with id "+id);
        }
        return accounts.get(0);

    }

    @PreAuthorize("permitAll()")
    @SuppressWarnings("unchecked")
    @Override
    public Account getAccountByName(String name) throws NoSuchElementException {
        List<Account> accounts =  getSession()
                .getNamedQuery("GET_ACCOUNT_BY_NAME")
                .setParameter("name", name)
                .getResultList();
        if (accounts.size()==0){
            throw new NoSuchElementException("There's no account with name "+name);
        }
        return accounts.get(0);    }
}

