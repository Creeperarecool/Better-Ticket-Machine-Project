/**
 * TicketMachine models a ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * Instances will check to ensure that a user only enters
 * sensible amounts of money, and will only print a ticket
 * if enough money has been input.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class TicketMachine
{
    // The price of a ticket from this machine.
    private int price;
    private int newPrice;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;
    
    private boolean moneyInsertCheck;
    
    private int amountLeftToPay;

    /**
     * Create a machine that issues tickets of the given price.
     */
    public TicketMachine(int cost)
    {
        price = cost;
        balance = 0;
        total = 0;
        newPrice = price;
    }

    /**
     * @Return The price of a ticket.
     */
    public int getPrice()
    {
        return price;
    }
    
    public void discount(float percentDiscounted)
    {
        percentDiscounted = percentDiscounted / 100;
        float tempPrice = price * percentDiscounted;
        newPrice = price - (int)tempPrice;
    }

    /**
     * Return The amount of money already inserted for the next ticket.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Receive an amount of money from a customer.
     * Check that the amount is sensible.
     */
    public void insertMoney(int amount)
    {
        if (amount <= 0){
            moneyInsertCheck = true;
        }
        else{
            moneyInsertCheck = false;
        }
        if(moneyInsertCheck) {
            System.out.println("Use a positive amount rather than: " + amount);
        }
        else {
            balance = balance + amount;
        }
    }
    
    public void affordable(int budget)
    {
        if (price > budget){
            System.out.println("Too expensive, your budget is:" + budget + "and the price is:" + price);
        }
        else{
            System.out.println("Just Right");
        }
    }

    /**
     * Print a ticket if enough money has been inserted, and
     * reduce the current balance by the ticket price. Print
     * an error message if more money is required.
     */
    public void printTicket()
    {   
        if(balance >= newPrice - amountLeftToPay) {
            // Simulate the printing of a ticket.
            System.out.println("##################");
            System.out.println("# The BlueJ Line");
            System.out.println("# Ticket");
            System.out.println("# " + price + " cents.");
            System.out.println("##################");
            System.out.println();

            // Update the total collected with the price.
            total = total + newPrice;
            // Reduce the balance by the price.
            balance = balance - newPrice;
            newPrice = price;
        }
        else {
            amountLeftToPay = amountLeftToPay + balance;
            System.out.printf("You must pay %d more cents.%n",
                              newPrice - amountLeftToPay);
            balance = 0;
        }
    }

    /**
     * Return the money in the balance.
     * The balance is cleared.
     */
    public int refundBalance()
    {
        int amountToRefund;
        amountToRefund = balance;
        balance = 0;
        return amountToRefund;
    }
    
    public int empty()
    {
        int emptyMachine;
        emptyMachine = total;
        total = 0;
        return emptyMachine;
    }
}
