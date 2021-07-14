package databean;


import org.genericdao.PrimaryKey;

@PrimaryKey("customer_email")
public class CustomerInfo {
	private String customer_email;
    private String balance;
    private String name;
    private String address;

    public String getBalance()        { return balance; }
    public String getCustomer_email()        { return customer_email; }
    public String getName()        { return name; }
    public String getAddress()        { return address; }

    public void setBalance(String s)  { balance = s;    }
    public void setCustomer_email(String s)  { customer_email = s;    }
    public void setName(String s) {	name = s; }
    public void setAddress(String s) {	address = s; }
}
