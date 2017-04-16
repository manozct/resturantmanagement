package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/10/2017.
 */

public class SubMenu extends Menu {
    private Integer subMenuId;
    private String subMenuName;
    private Double price;
    SubMenu(Integer menuId,String menuName,Integer subMenuId,String subMenuName,Double price){
        super(menuName);
        this.subMenuId=subMenuId;
        this.subMenuName=subMenuName;
        this.price=price;
    }
    public Integer getSubMenuId() {
        return subMenuId;
    }

    public void setSubMenuId(Integer subMenuId) {
        this.subMenuId = subMenuId;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String toString(){
        return super.toString()+"Sub Menu: "+this.subMenuName;
    }
}
