package services;

import apihelper.helper.ApiHelper;
import apihelper.pojo.AccountPojo;
import logger.LoggerDecorator;
import org.apache.logging.log4j.Level;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

@Service
public class AccountCredentials implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AccountPojo account = (new ApiHelper()).getAccountByName(username);
            return new User(username, account.getPassword(), getGrantedAuthorities(account));
        } catch (IOException e) {
            LoggerDecorator.getLogger().log(Level.ERROR, "User " + username +" not found");
            throw  new UsernameNotFoundException("User " + username +" not found");
        }
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(AccountPojo account) {
        Collection<GrantedAuthority> authorities = new LinkedList<>();
        account.getRoles().forEach(rolePojo -> authorities.add((GrantedAuthority) rolePojo::getRole));
        return authorities;
    }
}
