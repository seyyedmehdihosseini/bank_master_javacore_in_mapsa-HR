import java.sql.SQLException;

public class ATM {
    Customer customer = new Customer();
    Account account = new Account();

    public void atmCustomer() throws SQLException {
        int customerID = (int) MyInput.getInput("Enter CustomerID ( 0 = Back ): ", int.class);
        if (customer.loging(customerID) == true) {
            int account1 = (int) MyInput.getInput("Enter AccountID : ", int.class);
            if (account.loginAccountID(account1) == true) {
                account.updateAccount(account1);

            }
        }
    }
}