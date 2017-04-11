package rms.manozct.resturantmanagement.model;

import java.util.Date;

/**
 * Created by Zamuna on 4/11/2017.
 */

public class Manager extends Employee {
    private Double salary;
    private Double bonus;
    public Manager(Integer empId, String empName, String add, Date dob, String cNo, String ssn, Double salary, Date hd) {
        super(empId, empName, add, dob, cNo, ssn, hd);
        this.salary=salary;
        this.bonus=bonus;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
