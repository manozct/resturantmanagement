package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/11/2017.
 */

public class Report {
    private Integer reportId;
    private Inventory inventory;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
