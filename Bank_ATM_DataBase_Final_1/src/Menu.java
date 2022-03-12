import java.sql.SQLException;

public class Menu {
    Personnel personnel = new Personnel();
   ATM atm = new ATM();



   public void menuPersonal(){
        try {
            personnel.showMenuPersonal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void menuATMcustomer(){
        try {
            atm.atmCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}