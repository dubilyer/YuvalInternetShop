package dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonIgnore;

@ApiModel(value = "User account")
public class AccountDto {
    @ApiModelProperty(value = "the unique id of user account", required = true)
    private long id;

    @ApiModelProperty(value = "users nickname", required = true)
    private String name;

    @ApiModelProperty(value = "password", required = true)
    private String password;


    public AccountDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AccountDto(long id, String name) {
        this.id = id;
        this.name = name;
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


    public AccountDto(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(long id) {
        this.id = id;
    }

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
}
