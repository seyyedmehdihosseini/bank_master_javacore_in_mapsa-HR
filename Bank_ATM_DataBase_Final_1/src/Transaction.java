
public class Transaction {
    private int transactionID;
    private int accountID;
    private String transactionType;
    private int transAmount;
    private int remainAmount;// meqdar baqi mandeh



    public void setTransactionType(String transactionType) {this.transactionType = transactionType;}
    public void setAccountID(int accountID) {this.accountID = accountID;}
    public void setTransAmount(int transAmount) {
        this.transAmount = transAmount;
    }
    public void setRemainAmount(int remainAmount) {
        this.remainAmount = remainAmount;
    }
    public void setTransactionID(int transactionID) {this.transactionID = transactionID;}



    public int getTransactionID() {return transactionID;}
    public String getTransactionType() {return transactionType;}
    public int getAccountID() {return accountID;}
    public int getTransAmount() {
        return transAmount;
    }
    public int getRemainAmount() {
        return remainAmount;
    }

    @Override
    public String toString() {
        return "\n \t\t Transaction " +
                "\n" +"accountID : "+ accountID +
                "\t\t" + transAmount +
                "\t\t" + transactionType+
                "\t\t" + remainAmount ;

    }


    //ArrayList<String> transactions = new ArrayList<>(); //  '*' stack '*'
  /*  public void addTransactions(int customerId, int amount, int balanc) {
        String t = "customerID  " + customerId + "    " + " amount  " + amount + "     " + "balance  " + balanc;
        transactions.add(t);}
    public ArrayList<String> getTransactions() {return transactions;}
   public void printTransaction() {
        if (getTransactions().size() >= 11) {
            for (int i = getTransactions().size() - 1 ; i >= getTransactions().size() - 11; i--) {
                System.out.println(getTransactions().get(i));
            }
        }
        else {for (String t : getTransactions()) {System.out.println(t);}
        }
    }*/

}
