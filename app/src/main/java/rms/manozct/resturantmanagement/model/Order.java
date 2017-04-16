package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/11/2017.
 */

public class Order {
    private Integer orderId;
    private Table table;
    private Waiter waiter;
    private SubMenu subMenu;
    private Integer menuId;
    private Integer waiterId;
    Order(Integer menuId,Integer waiterId){

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }
}
