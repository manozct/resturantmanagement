package rms.manozct.resturantmanagement.model;

import java.util.Date;

/**
 * Created by manozct on 4/11/2017.
 */

public class Bill
{
    private Integer billNo;
    private Order order;
    private double tax;
    private double serviceCharge;
    private double discount;
    private double totalAmount;
    private Date billingDate;
    private int cashierId;
    private Payment payment;

    Bill()
    {
    this.payment=new Payment( 100.0+this.getBillNo(),false);
        this.payment.setBill(this);

    }
    public Integer getBillNo() {
        return billNo;
    }

    public void setBillNo(Integer billNo) {
        this.billNo = billNo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }
    public void setPayment(Payment payment)
    {
        this.payment=payment;
    }
public Payment getPayment()
{
    return this.payment;
}


}
