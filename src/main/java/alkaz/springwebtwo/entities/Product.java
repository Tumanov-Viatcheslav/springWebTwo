package alkaz.springwebtwo.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {

    private int id;
    private String name;
    private double cost;

    public Product(int id, String name, double cost) throws Exception {
        if(cost < 0)
            throw new Exception("отрицательная цена");
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }


}


