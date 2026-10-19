package college.ems.model;

import java.time.LocalDate;

public class Business {
    private int businessId, entrepreneurId;
    private String entrepreneurName, businessName, businessType, industry, description, location, status;
    private LocalDate foundedDate;
    public int getBusinessId(){return businessId;} public void setBusinessId(int v){businessId=v;}
    public int getEntrepreneurId(){return entrepreneurId;} public void setEntrepreneurId(int v){entrepreneurId=v;}
    public String getEntrepreneurName(){return entrepreneurName;} public void setEntrepreneurName(String v){entrepreneurName=v;}
    public String getBusinessName(){return businessName;} public void setBusinessName(String v){businessName=v;}
    public String getBusinessType(){return businessType;} public void setBusinessType(String v){businessType=v;}
    public String getIndustry(){return industry;} public void setIndustry(String v){industry=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public LocalDate getFoundedDate(){return foundedDate;} public void setFoundedDate(LocalDate v){foundedDate=v;}
}
