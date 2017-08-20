package apihelper.pojo;

import com.fasterxml.jackson.annotation.JsonGetter;

public class AccountPojo {

    private long id;
    private String name;
    private String password;

    @SuppressWarnings("unused")
    public AccountPojo(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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
}
