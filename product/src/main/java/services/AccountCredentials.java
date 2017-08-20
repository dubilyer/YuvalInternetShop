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

import static java.util.Arrays.asList;

@Service
public class AccountCredentials implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AccountPojo accountPojo = (new ApiHelper()).getAccountByName(username);
            return new User(username, accountPojo.getPassword(), getGrantedAuthorities(username));
        } catch (IOException e) {
            LoggerDecorator.getLogger().log(Level.ERROR, "User " + username +" not found");
            throw  new UsernameNotFoundException("User " + username +" not found");
        }
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<? extends GrantedAuthority> authorities;
        authorities = asList(() -> "ROLE_ADMIN", () -> "ROLE_BASIC");
        return authorities;
    }
}
