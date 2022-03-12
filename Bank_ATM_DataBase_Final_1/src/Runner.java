public class Runner {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while (true) {
            System.out.println("----------- WELCOME --------------");
            int number = (int) MyInput.getInput("Enter your corresponding number" + "\n 1 _ users \n 2 _ Customer(ATM) ", int.class);
            switch (number) {
                case 1:
                    menu.menuPersonal();
                    continue;
                case 2:
                    menu.menuATMcustomer();
                    continue;
                case 3:
                    System.out.println(" ------  Good night ------ ");
                    break;
            }
         break;
        }




    }
}