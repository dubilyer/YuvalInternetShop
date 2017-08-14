package dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Shop item")
public class ProductDto {
    @ApiModelProperty(value = "the unique id of shop item", required = true)
    private long id;
    @ApiModelProperty(value = "Items name", required = true)
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
}
