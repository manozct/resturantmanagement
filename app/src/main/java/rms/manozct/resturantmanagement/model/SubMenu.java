package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/10/2017.
 */

public class SubMenu extends Menu {
    private Integer subMenuId;
    private Integer subMenuName;
    private Double price;
    
    public Integer getSubMenuId() {
        return subMenuId;
    }

    public void setSubMenuId(Integer subMenuId) {
        this.subMenuId = subMenuId;
    }

    public Integer getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(Integer subMenuName) {
        this.subMenuName = subMenuName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
