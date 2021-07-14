package databean;

import org.genericdao.PrimaryKey;



@PrimaryKey("fund_name")
public class Funds {
    private String fund_name;
    private String price;

    public String getFund_name()        { return fund_name; }
    public String getPrice()        { return price; }

    public void setFund_name(String s)  { fund_name = s;    }
    public void setPrice(String s)  { price = s;    }
}