package thegreymanshow.vscanner;

/**
 * Created by akshay dangare on 4/6/2017.
 */

public class Order {

    private String name;
    private int quant;
    private double cost;

    public Order(String a,int b,double c){
        this.setName(a);
        this.setQuant(b);
        this.setCost(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
