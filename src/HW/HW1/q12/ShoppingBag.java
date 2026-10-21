package HW.HW1.q12;

public class ShoppingBag
{
    private int numItems;
    private double totalCost;
    private double taxRate;

//constructor
    public ShoppingBag(double taxRate)
    {
        this.taxRate = taxRate;
        this.numItems = 0;
        this.totalCost = 0.0;
    }
//Place transformer
    public void place(int quantity, double unitPrice)
    {
        numItems += quantity;
        totalCost += quantity * unitPrice;
    }

    // Getters
    public int getNumItems()
    {
        return numItems;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public double getTaxRate()
    {
        return taxRate;
    }

    public double totalCost()
    {
        return totalCost + (totalCost * taxRate);
    }

    public String toString()
    {
        return String.format(
                "Shopping Bag Summary%n" +
                        "---------------------%n" +
                        "Number of items : %d%n" +
                        "Subtotal        : $%.2f%n" +
                        "Tax rate        : %.2f%%%n" +
                        "Tax amount      : $%.2f%n" +
                        "Total with tax  : $%.2f", numItems,
                        totalCost, taxRate * 100, (totalCost * taxRate), totalCost()
        );
    }
}
