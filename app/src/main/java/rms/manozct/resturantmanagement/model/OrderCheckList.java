package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/11/2017.
 */

public class OrderCheckList {
    private Integer orderCheckListId;
    private Boolean prepared;
    private Boolean served;

    public Integer getOrderCheckListId() {
        return orderCheckListId;
    }

    public void setOrderCheckListId(Integer orderCheckListId) {
        this.orderCheckListId = orderCheckListId;
    }

    public Boolean getPrepared() {
        return prepared;
    }

    public void setPrepared(Boolean prepared) {
        this.prepared = prepared;
    }

    public Boolean getServed() {
        return served;
    }

    public void setServed(Boolean served) {
        this.served = served;
    }
}
