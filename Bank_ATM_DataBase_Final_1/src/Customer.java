import java.sql.SQLException;

public class Customer {
    Bank bank = new Bank();

    private int customerID ;
    private String name;
    private String family;
    private int age;
    private int codeID;

    public void setName(String name) {this.name = name;}
    public void setFamily(String family) {this.family = family;}
    public void setAge(int age) {if (age < 18) {System.err.println("less than 18 years old  :( ");} else {this.age = age;}}
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setCodeID(int codeID) {this.codeID = codeID;}



    public int getCodeID() {return codeID;}
    public int getCustomerID() {return customerID;}
    public String getName() {
        return name;
    }
    public String getFamily() {
        return family;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ",codeID="+codeID+
                '}' + "\n";
    }

    public boolean loging(int customerID) throws SQLException {
        boolean b = false;
        if (customerID == 0) {
            System.out.println(" CustomerID Is Not Match");
            return b=false;
        }
        for (int i = 0; i <bank.getAllAccount().size() ; i++) {
            if (customerID == bank.getAllCustomer().get(i).getCustomerID()) {
                System.out.println(bank.printAllAccountOrCustomer(customerID));
                System.out.println();
                return b=true;
            }
        }

        return b;
    }



}