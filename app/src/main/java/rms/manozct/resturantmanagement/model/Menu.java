package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/10/2017.
 */

public class Menu {
    private Integer menuId;
    private String menuName;
    public static Integer id=100;
    Menu(Integer menuId,String name){
        this.menuId=menuId;
        this.menuName=name;
    }
    Menu(String menuName){
        this.menuName=menuName;
    }
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
