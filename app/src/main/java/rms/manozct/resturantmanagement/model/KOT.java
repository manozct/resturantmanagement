package rms.manozct.resturantmanagement.model;

/**
 * Created by Zamuna on 4/11/2017.
 */

public class KOT {
    private Integer kotId;
    private Integer quantity;
    private Integer waiterId;

    public KOT(Integer kotId, Integer quantity, Integer waiterId) {
        this.kotId = kotId;
        this.quantity = quantity;
        this.waiterId = waiterId;
    }

    public Integer getKotId() {
        return kotId;
    }

    public void setKotId(Integer kotId) {
        this.kotId = kotId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }
}
