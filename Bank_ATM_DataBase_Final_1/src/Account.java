import java.sql.SQLException;

public class Account {
    Transaction transaction = new Transaction();
    Bank bank = new Bank();
    private int customerID;    // shomare moshtari
    private int accountID;     // shomare hesab
    private int password;
    private String accountType;     // noe hesab (jari , pasandaz , seporde )
    private long balance;

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }


    public int getCustomerID() {
        return customerID;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public long getBalance() {
        return balance;
    }


    private int number;

    public boolean loginAccountID(int accountID1) throws SQLException {
        boolean b = false;

        for (int i = 0; i < bank.getAllAccount().size(); i++) {
            if (accountID1 == bank.getAllAccount().get(i).getAccountID()) {
                int password = (int) MyInput.getInput("Enter Password : ", int.class);
                if (password == bank.getAllAccount().get(i).getPassword()) {
                    this.number = i;
                    return b = true;
                } else {
                    System.out.println("Password Or AccountID is not Match");
                    return b = false;
                }
            }
        }
        return b;
    }


    public void updateAccount(int accountID) throws SQLException {
        Account account = new Account();
        for (int i = 0; i < bank.getAccount(accountID).size(); i++) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println();
                System.out.println(" -------------- WELCOME --------------");
                System.out.println();
                int number1 = (int) MyInput.getInput("Select Option : " +
                        "\n1- withdrawals \n2- payment \n3- Balance \n4- Transactions \n5- Exit \n", int.class);
                switch (number1) {
                    case 1: // kahesh hesab
                        transaction.setTransactionType("-");
                        transaction.setAccountID(accountID);
                        int amount1 = (int) MyInput.getInput(" Enter Number Reduce ", int.class);
                        if (amount1 < bank.getAccount(accountID).get(i).getBalance()) {
                         transaction.setTransAmount(amount1);
                            account.setAccountID(accountID);
                            account.setBalance(bank.getAllAccount().get(number).getBalance() - amount1);
                            bank.updateAccountBalance(accountID, (int) account.getBalance());
                            System.out.println(" Balance :  " + bank.getAllAccount().get(number).getBalance());
                           transaction.setRemainAmount((int) bank.getAllAccount().get(number).getBalance());
                          bank.insertTransaction(transaction);
                        } else {
                            System.out.println("Amount Entered More Than Inventory");

                            break;
                        }
                        continue;

                    case 2: // afzayesh
                    transaction.setTransactionType("+");
                     transaction.setAccountID(accountID);
                        int amount2 = (int) MyInput.getInput(" Enter Number Increase ", int.class);
                        account.setAccountID(accountID);
                        transaction.setTransAmount(amount2);
                        account.setBalance(bank.getAllAccount().get(number).getBalance() + amount2);
                        bank.updateAccountBalance(accountID, (int) account.getBalance());
                        System.out.println(" Balance :  " + bank.getAllAccount().get(number).getBalance());
                        transaction.setRemainAmount((int) bank.getAllAccount().get(number).getBalance());
                        bank.insertTransaction(transaction);
                        continue;
                    case 3:
                        transaction.setTransactionType(" 0 ");
                        transaction.setAccountID(accountID);
                        transaction.setTransAmount(0);
                        transaction.setRemainAmount((int) bank.getAllAccount().get(number).getBalance());
                        bank.insertTransaction(transaction);
                        System.out.print("Balance All : \t");
                        System.out.println(bank.getAllAccount().get(number).getBalance());
                        continue;
                    case 4://tarakonesh // transaction
                        System.out.println(bank.getTransactionOneAccount(accountID));
                        continue;
                    case 5:
                        System.out.println(" .....   Best Wishes   ..... ");
                        break;
                    default:
                        System.out.println("Number Is Not Match ");
                        continue;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    @Override
    public String toString() {
        return "Accounts{ \n" + "accountID =  " + accountID + "\n" + '}';
    }
}
