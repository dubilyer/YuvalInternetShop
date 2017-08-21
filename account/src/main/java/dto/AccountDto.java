package dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import model.Role;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashSet;
import java.util.Set;

@ApiModel(value = "User account")
public class AccountDto {
    @ApiModelProperty(value = "the unique id of user account", required = true)
    private long id;

    @ApiModelProperty(value = "users nickname", required = true)
    private String name;

    @ApiModelProperty(value = "password", required = true)
    private String password;

    @ApiModelProperty(value = "roles", required = true)
    private Set<Role> roles = new HashSet<>();

    public AccountDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AccountDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AccountDto(long id, String name, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }


    @JsonGetter
    public long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(long id) {
        this.id = id;
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter
    public Set<Role> getRoles() {
        return roles;
    }

    @SuppressWarnings("unused")
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
