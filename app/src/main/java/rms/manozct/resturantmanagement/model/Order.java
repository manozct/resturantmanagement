package rms.manozct.resturantmanagement.model;

import java.sql.Date;

/**
 * Created by manozct on 4/11/2017.
 */

public class Order {
    private Integer orderId;
    private Integer tableId;
    private Integer waiterId;
    private Integer subMenuId;
    private Integer quantity;
    private Double price;

    public Order(){

    }
    public Order(Integer tableId, Integer waiterId, Integer subMenuId, Integer quantity, Double price, String orderDate) {
        this.tableId = tableId;
        this.waiterId = waiterId;
        this.subMenuId = subMenuId;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    private String orderDate;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public Integer getSubMenuId() {
        return subMenuId;
    }

    public void setSubMenuId(Integer subMenuId) {
        this.subMenuId = subMenuId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
