package dto;

public class ProductDto {
    private long id;
    private String name;

    public ProductDto(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public ProductDto(long id, String name) {

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
