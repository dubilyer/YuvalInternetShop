package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "GET_ALL_ACCOUNTS", query = "from Account"),
        @NamedQuery(name = "GET_ACCOUNT_BY_ID", query = "from Account where id= :id"),
        @NamedQuery(name = "GET_ACCOUNT_BY_NAME", query = "from Account where name= :name")
})
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = {
        "account_id",
        "account_name"
}))
public class Account {
    private long id;
    private String name;
    private String password;

    private Set<Role> roles = new HashSet<>();

    public Account(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Account() {

    }

    public Account(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_account_id_seq")
    @SequenceGenerator(name = "accounts_account_id_seq", sequenceName = "accounts_account_id_seq", allocationSize = 1)
    @Column(name = "account_id")
    public long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "account_name", unique = true)
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )    public Set<Role> getRoles() {
        return roles;
    }

    @SuppressWarnings("unused")
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @SuppressWarnings("unused")
    public void addRole (Role role){
        this.roles.add(role);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

