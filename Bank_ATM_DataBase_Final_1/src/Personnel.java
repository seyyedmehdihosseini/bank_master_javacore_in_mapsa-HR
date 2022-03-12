import java.sql.SQLException;

public class Personnel {
    Bank bank = new Bank();
    public int[] arrayUserName() {
        int[] arrayUser = {123456789, 12345678, 1234567};
        return arrayUser;
    }

    public int[] arrayPassword() {
        int[] arrayPass = new int[3];
        arrayPass[0] = 6789;
        arrayPass[1] = 5678;
        arrayPass[2] = 4567;
        return arrayPass;
    }


    public int number = 1;

    public boolean userName(int username) {
        boolean usernamechecking = false;
        for (int i = 0; i < arrayUserName().length; i++) {
            if (username == arrayUserName()[i]) {
                this.number = i;
                usernamechecking = true;
            }
        }
        return usernamechecking;
    }

    public int getnumber() {
        return number;
    }

    public boolean passWord(int password) {
        boolean b = false;
        if (password == arrayPassword()[number]) {
            System.out.println(" ... true user and pass .... \n \n ");
            return b = true;
        } else {
            System.out.println(" :() oooooooooooooooo :() ");
            return b = false;
        }
    }


    public void showMenuPersonal() throws SQLException {
        int username;
        int password;
        if (userName(username = (int) MyInput.getInput("enter userName : ", int.class)) &&
                passWord(password = (int) MyInput.getInput("enter password : ", int.class))) {
            while (true) {
                int number = (int) MyInput.getInput("select one option \n" +
                        " 1- add Customer \n 2- remove Customer \n 3- open IAccount  \n 4- Print All Customer   \n 5- Exit", int.class);
                Customer customer = new Customer();
                switch (number) {

                    case 1://Add Customer
                        while (true) {
                            customer.setName((String) MyInput.getInput("Enter Name : ", String.class));
                            customer.setFamily((String) MyInput.getInput("Enter Family : ", String.class));//  چرا next قبول میکند ولی nextLine قبول نمیکند???
                            customer.setAge((int) MyInput.getInput("Enter Number Age : ", int.class));
                            customer.setCodeID((int) MyInput.getInput("Enter Code ID : ", int.class));
                            try {
                                bank.insertCustomer(customer);
                                System.out.println("Customer ID is :  " + bank.getOneCustomerID(customer.getCodeID()));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            String checking = (String) MyInput.getInput("Enter New IAccount ( Y/N ) ? ", String.class);
                            if (checking.equalsIgnoreCase("N")) {
                                break;
                            }
                        }
                        continue;
                    case 2: // Remove Customer
                        int customerID = (int) MyInput.getInput("Enter CustomerID : ", int.class);
                        bank.deleteCustomer(customerID);
                        continue;

                    case 3:     //Add AccountID Customer
                        Account account = new Account();
                        int customerId = (int) MyInput.getInput("Enter CustomerID ", int.class);
                        for (int i = 0; i < bank.getAllCustomer().size(); i++) {
                            if (customerId == bank.getAllCustomer().get(i).getCustomerID()) { /// *******  ////// *****  in qesmateslah bayad check shavad k moshtari ba in kode vojod dard y na
                                account.setCustomerID(customerId);
                                while (true) {
                                    //account.setAccountID((int) MyInput.getInput("Enter AccountID : ", int.class));
                                    account.setPassword((int) MyInput.getInput("Enter New Password number : ", int.class));
                                    account.setAccountType((String) MyInput.getInput("Select IAccount Type (jari , pasandaz , seporde)", String.class));
                                    account.setBalance((int) MyInput.getInput("Enter Balance ", int.class));
                                    bank.insertAccount(account);
                                    String checking = (String) MyInput.getInput("Enter New IAccount ( Y/N ) ? ", String.class);
                                    if (checking.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }
                            }
                        }// mitoonim inja hm halqe bzanim v in amaliyat baray moshtari ha dig hm ejra beshe
                        continue;
                    case 4:
                        System.out.println();
                        System.out.println(" ---------------- All Customer ------------------- ");
                        System.out.println(bank.getAllCustomer());
                        continue;       // break => nabayad bashad bayad az content estefade shavad
                    case 5:
                        System.out.println(" .....  Best wishes  .... ");
                        System.out.println();
                        break;
                    default:
                        System.out.println("The number you entered is incorrect");
                        continue;      // break => nabayad bashad bayad az content estefade shavad

                }
                break;
            }
        }else {
            System.out.println("\t\t----  :(  ----- \t\t");
            System.out.println("user or password is not match ");
        }

    }


}
