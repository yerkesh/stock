package databean;


import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class CustomerFunds {
	private int    id;
	private String customer_email;
	private String    fund_name;
	private String shares_number;
	private String total_value;
	
	public int    getId()                { return id;           }
    public String getCustomer_email()              { return customer_email;         }
    public String    getFund_name()          { return fund_name;     }
    public String getShares_number()         { return shares_number;    }
    public String getTotal_value()          { return total_value;     }

    public void   setId(int i)           { id = i;              }
	public void   setCustomer_email(String s)      { customer_email = s;            }
	public void   setFund_name(String i)     { fund_name = i;        }
	public void   setShares_number(String s) { shares_number = s;       }
	public void   setTotal_value(String s)  { total_value = s;        }
}
