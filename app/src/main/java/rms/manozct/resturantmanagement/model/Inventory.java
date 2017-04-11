package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/11/2017.
 */

public class Inventory {
    private Integer inventoryId;
    private String inventoryName;
    private Double price;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
