package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/10/2017.
 */

public class SubMenu
{
    private Integer subMenuId;
    private String subMenuName;
    private Double price;
    private  String imageUrl;
    private Integer mainMenuId;

    public SubMenu(){

    }
    public SubMenu(String imageUrl, String subMenuName, Double price) {
        this.imageUrl = imageUrl;
        this.subMenuName = subMenuName;
        this.price = price;
    }

    public Integer getMainMenuId() {
        return mainMenuId;
    }

    public void setMainMenuId(Integer mainMenuId) {
        this.mainMenuId = mainMenuId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
