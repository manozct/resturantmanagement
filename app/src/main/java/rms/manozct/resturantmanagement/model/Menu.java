package rms.manozct.resturantmanagement.model;

import java.io.Serializable;

/**
 * Created by manozct on 4/10/2017.
 */

public class Menu implements Serializable {
    private Integer menuId;
    private String menuName;
    public static Integer id=100;

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
