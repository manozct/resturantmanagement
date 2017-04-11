package rms.manozct.resturantmanagement.model;

import java.util.Date;

/**
 * Created by manozct on 4/11/2017.
 */

public class Waiter extends Employee {
    private Double salary;
    private Double tips;

    public Waiter(Integer empId, String empName, String add, Date dob, String cNo, String ssn, Date hd, Double salary, Double tips) {
        super(empId, empName, add, dob, cNo, ssn, hd);
        this.salary = salary;
        this.tips = tips;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getTips() {
        return tips;
    }

    public void setTips(Double tips) {
        this.tips = tips;
    }
}
