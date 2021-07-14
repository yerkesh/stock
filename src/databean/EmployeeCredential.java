package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("employeeId")
public class EmployeeCredential {
    private String employeeId;
    private String password;

    public String getPassword()        { return password; }
    public String getEmployeeId()        { return employeeId; }

    public void setPassword(String s)  { password = s;    }
    public void setEmployeeId(String s)  { employeeId = s;    }
}

