package college.ems.model;

import java.time.LocalDate;

public class Employee {
    private int employeeId, businessId;
    private String businessName, employeeName, email, phone, designation;
    private LocalDate joiningDate;
    public int getEmployeeId(){return employeeId;} public void setEmployeeId(int v){employeeId=v;}
    public int getBusinessId(){return businessId;} public void setBusinessId(int v){businessId=v;}
    public String getBusinessName(){return businessName;} public void setBusinessName(String v){businessName=v;}
    public String getEmployeeName(){return employeeName;} public void setEmployeeName(String v){employeeName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getDesignation(){return designation;} public void setDesignation(String v){designation=v;}
    public LocalDate getJoiningDate(){return joiningDate;} public void setJoiningDate(LocalDate v){joiningDate=v;}
}
