package college.ems.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Investment {
    private int investmentId, businessId;
    private String businessName, investorName, investmentType;
    private BigDecimal investmentAmount;
    private LocalDate investmentDate;
    public int getInvestmentId(){return investmentId;} public void setInvestmentId(int v){investmentId=v;}
    public int getBusinessId(){return businessId;} public void setBusinessId(int v){businessId=v;}
    public String getBusinessName(){return businessName;} public void setBusinessName(String v){businessName=v;}
    public String getInvestorName(){return investorName;} public void setInvestorName(String v){investorName=v;}
    public String getInvestmentType(){return investmentType;} public void setInvestmentType(String v){investmentType=v;}
    public BigDecimal getInvestmentAmount(){return investmentAmount;} public void setInvestmentAmount(BigDecimal v){investmentAmount=v;}
    public LocalDate getInvestmentDate(){return investmentDate;} public void setInvestmentDate(LocalDate v){investmentDate=v;}
}
