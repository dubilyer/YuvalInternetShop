package apihelper.pojo;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.HashSet;
import java.util.Set;

public class AccountPojo {

    private long id;
    private String name;
    private String password;
    private Set<RolePojo> roles = new HashSet<>();

    @SuppressWarnings("unused")
    public AccountPojo(long id, String name, String password, Set<RolePojo> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }


    @SuppressWarnings("unused")

    public AccountPojo() {
    }

    @JsonGetter("id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter("roles")
    public Set<RolePojo> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolePojo> roles) {
        this.roles = roles;
    }
}
