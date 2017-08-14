package apihelper.pojo;

import com.fasterxml.jackson.annotation.JsonGetter;

public class AccountPojo {

    private long id;
    private String name;

    public AccountPojo(long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
