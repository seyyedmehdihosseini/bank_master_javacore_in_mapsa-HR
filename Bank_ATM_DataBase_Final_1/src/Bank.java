import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {
  /*  private final String url = "jdbc:postgresql://localhost:5432/Bank";
    private final String user = "postgres";
    private final String password = "1376";
    Connection connect = null;

    public Bank() {
        connect = connect();
    }

    private Connection connect() {
        try {
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connect;
    }*/

    private static Connection connect = null;

    static {
        final String url = "jdbc:postgresql://localhost:5432/Bank";
        final String user = "postgres";
        final String password = "1376";
        try {
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("az man copy shod ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() {
        return connect;
    }


    public long insertCustomer(Customer customer) throws SQLException {
        String SQL = "INSERT INTO customer(name,family,age,code_id) "
                + "VALUES(?,?,?,?)";
        PreparedStatement per = null;
        try {
            per = connect.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        per.setObject(1, customer.getName());
        per.setObject(2, customer.getFamily());
        per.setObject(3, customer.getAge());
        per.setObject(4, customer.getCodeID());
        per.executeUpdate();
        return 0;
    }


    public void deleteCustomer(int customerID){
        String SQLDeleteOneCustomer="delete from customer where customer_id=?";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(SQLDeleteOneCustomer);
            preparedStatement.setObject(1,customerID);
            preparedStatement.executeUpdate();
            System.out.println(" ----------------- DELETE CUSTOMER "+ customerID + " -----------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public long insertAccount(Account account) {
        String SQL = "insert into account(customer_id,account_type,balance,password)"
                + "values (?,?,?,?)";
        try {
            PreparedStatement preAccount = connect.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preAccount.setObject(1, account.getCustomerID());
            preAccount.setObject(2, account.getAccountType());
            preAccount.setObject(3, account.getBalance());
            preAccount.setObject(4, account.getPassword());
            preAccount.executeUpdate();
// account id ro da nazar nmigirim
            // va acont id b sorat utomatic sakhte mishavad
            // faqat meqad ono bayad daryaft konim v nshon bdim

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }



    public List<Account> printAllAccountOrCustomer(int customerID) throws SQLException { // kamel shavad bayad
        List<Account> accounts = new ArrayList<>();
        String SQL = "SELECT * FROM account WHERE customer_id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(SQL);
            preparedStatement.setObject(1, customerID);
        } catch (Exception e) {
            System.out.println(" OO OOO " + e.getMessage());
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int customer = resultSet.getInt("customer_id");
            int accountid = resultSet.getInt("account_id");
            int password = resultSet.getInt("password");
            String accountType = resultSet.getString("account_type");
            int balance = resultSet.getInt("balance");
            Account account = new Account();
            account.setCustomerID(customer);
            account.setAccountID(accountid);
            account.setPassword(password);
            account.setAccountType(accountType);
            account.setBalance(balance);
            accounts.add(account);
        }
        return accounts;
    }


    public List<Customer> getAllCustomer() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        String SQL = "SELECT * from customer";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) { // customer,name,family,age,codeId
            Customer customer = new Customer();
            customer.setCustomerID(resultSet.getInt("customer_id"));
            customer.setName(resultSet.getString("name"));
            customer.setFamily(resultSet.getString("family"));
            customer.setAge(resultSet.getInt("age"));
            customer.setCodeID(resultSet.getInt("code_id"));
            customers.add(customer);
        }
        return customers;
    }


    public List<Account> getAllAccount() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String SQL = "SELECT * from account";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connect.prepareStatement(SQL);
        } catch (SQLException e) { //customerID,accountID,password,accountType,balance
            e.printStackTrace();// customer_id,account_id,password,account_type,balance
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int customer = resultSet.getInt("customer_id");
            int accountid = resultSet.getInt("account_id");
            int password = resultSet.getInt("password");
            String accountType = resultSet.getString("account_type");
            int balance = resultSet.getInt("balance");
            Account account = new Account();
            account.setCustomerID(customer);
            account.setAccountID(accountid);
            account.setPassword(password);
            account.setAccountType(accountType);
            account.setBalance(balance);
            accounts.add(account);
        }
        return accounts;
    }


    public int updateAccountBalance(int account, int balance) throws SQLException {
        String SQL = "UPDATE account "
                + "SET balance=?"
                + "WHERE account_id=?";
        int affectedrows = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.setObject(1, balance);
        preparedStatement.setObject(2, account);
        affectedrows = preparedStatement.executeUpdate();

        return affectedrows;
    }


    public long insertTransaction(Transaction transaction) throws SQLException {
        String SQL = "INSERT INTO transactions (account_id,amount,transaction_type,balance)" +
                "VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(SQL);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        preparedStatement.setObject(1, transaction.getAccountID());
        preparedStatement.setObject(2, transaction.getTransAmount());
        preparedStatement.setObject(3, transaction.getTransactionType());
        preparedStatement.setObject(4, transaction.getRemainAmount());
        preparedStatement.executeUpdate();

        return 0;
    }


    public List<Transaction> getTransactionOneAccount(int accountID){
        List<Transaction> listOneAccount = new ArrayList<>();

        String SQLTransaction = "select * from transactions where account_id=? ";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(SQLTransaction);
            preparedStatement.setObject(1,accountID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Transaction transaction = new Transaction();
                transaction.setTransactionID(resultSet.getInt("transaction_id"));
                transaction.setAccountID(resultSet.getInt("account_id"));
                transaction.setTransAmount(resultSet.getInt("amount"));
                transaction.setTransactionType(resultSet.getString("transaction_type"));
                transaction.setRemainAmount(resultSet.getInt("balance"));
                listOneAccount.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOneAccount;
    }



    public int getOneCustomerID(int codeID){
        String SQLCustomer = "select * from customer where code_id=?";
        int numberCustomerID = 0;
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(SQLCustomer);
            preparedStatement.setObject(1,codeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                numberCustomerID=resultSet.getInt("customer_id");
              //  System.out.println(numberCustomerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberCustomerID;
    }


    public List<Account> getAccount(int accountID) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String SQL = "SELECT * from account WHERE account_id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(SQL);
        } catch (SQLException e) { //customerID,accountID,password,accountType,balance
            e.printStackTrace();// customer_id,account_id,password,account_type,balance
        }
        preparedStatement.setObject(1, accountID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int customer = resultSet.getInt("customer_id");
            int accountid = resultSet.getInt("account_id");
            int password = resultSet.getInt("password");
            String accountType = resultSet.getString("account_type");
            int balance = resultSet.getInt("balance");
            Account account = new Account();
            account.setCustomerID(customer);
            account.setAccountID(accountid);
            account.setPassword(password);
            account.setAccountType(accountType);
            account.setBalance(balance);
            accounts.add(account);
        }
        return accounts;
    }


    public void deletedAccount(int accountID) throws SQLException {
        String SQL = "DELETE FROM account WHERE account_id =?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.setObject(1, accountID);
        int number = preparedStatement.executeUpdate();
        System.out.println(number + " ---- Delete Account ---- ");

    }


    //update password
    public int updatePassword(Account account) throws SQLException {
        String SQL = "UPDATE account "
                + "SET password=?"
                + "WHERE account_id=?";
        int affectedrows = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.setObject(1, account.getPassword());
        preparedStatement.setObject(2, account.getAccountID());
        affectedrows = preparedStatement.executeUpdate();

        return affectedrows;
    }


}

