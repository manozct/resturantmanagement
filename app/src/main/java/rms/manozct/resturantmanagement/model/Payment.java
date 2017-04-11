package rms.manozct.resturantmanagement.model;

/**
 * Created by Zamuna on 4/11/2017.
 */

public class Payment {
    private Double paymentId;
    private Boolean paymentStatus;
    private Bill bill;

    public Payment(Double paymentId, Boolean paymentStatus) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
    }

    public Double getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Double paymentId) {
        this.paymentId = paymentId;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
