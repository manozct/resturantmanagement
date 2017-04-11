package rms.manozct.resturantmanagement.model;
import java.util.Date;



public abstract class Employee {
    private Integer empId;
    private String empName;
    private String address;
    private Date dob;
    private String cNo;
    private String ssn;
//    private Double salary;
    private Date hireDay;

    Employee(Integer empId,String empName,String add,Date dob,
             String cNo,String ssn,Date hd){
        this.empId=empId;
        this.empName=empName;
        this.address=add;
        this.dob=dob;
        this.cNo=cNo;
        this.ssn=ssn;
//        this.salary=salary;
        this.hireDay=hd;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getcNo() {
        return cNo;
    }

    public void setcNo(String cNo) {
        this.cNo = cNo;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }

    public String getEmpName() {

        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return empName;
    }

    public void setName(String name) {
        this.empName = name;
    }
}
