package rms.manozct.resturantmanagement.model;

/**
 * Created by manozct on 4/11/2017.
 */

public class Table {
    private Integer tableId;
    private String tableName;
    private Boolean tableStatus;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(Boolean tableStatus) {
        this.tableStatus = tableStatus;
    }
}
