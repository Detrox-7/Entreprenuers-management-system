package college.ems.model;

import java.time.LocalDate;

public class Entrepreneur {
    private int entrepreneurId;
    private String fullName, email, phone, gender, address, city, state, status;
    private LocalDate dateOfBirth, registrationDate;
    public int getEntrepreneurId(){return entrepreneurId;} public void setEntrepreneurId(int v){entrepreneurId=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getGender(){return gender;} public void setGender(String v){gender=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getCity(){return city;} public void setCity(String v){city=v;}
    public String getState(){return state;} public void setState(String v){state=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public LocalDate getDateOfBirth(){return dateOfBirth;} public void setDateOfBirth(LocalDate v){dateOfBirth=v;}
    public LocalDate getRegistrationDate(){return registrationDate;} public void setRegistrationDate(LocalDate v){registrationDate=v;}
}
