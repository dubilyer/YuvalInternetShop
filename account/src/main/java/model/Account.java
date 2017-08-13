package model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "GET_ALL_PRODUCTS", query = "from Account"),
        @NamedQuery(name = "GET_PRODUCT_BY_ID", query = "from Account where id= :id"),
})
@Table(name = "accounts")
public class Account {
    private long id;
    private String name;

    public Account(long id, String name) {
        this.id = id;
        this.name = name;
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

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "account_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

