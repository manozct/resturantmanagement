package rms.manozct.resturantmanagement.model;

import java.util.Date;

/**
 * Created by Zamuna on 4/11/2017.
 */

public class Cashier extends Employee {
    private Double salary;

    public Cashier(Integer empId, String empName, String add, Date dob, String cNo, String ssn, Date hd, Double salary) {
        super(empId, empName, add, dob, cNo, ssn, hd);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
