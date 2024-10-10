package alkaz.springwebtwo.services;

import alkaz.springwebtwo.entities.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service    //аннотация, которая говорит Спрингу, что этот класс является "сервисом" - частным случаем "компонента"
            //благодаря этому аннотация @ComponentScan на уровне конфигурации приложения автоматически
            //создает бин, помещает его в контекст и выполняет инициализацию
public class ProductService {
    List<Product> products = new ArrayList<>();

    public List<Product> getAllList() {
        return products;
    }
    @PostConstruct  //аннотация, которая говорит Спрингу, что данный метод нужно вызвать для иницализации
                    //после создания бина
    public void fill(){
        try{
            products.add(new Product(1,"Макарошки", 49.99));
            products.add(new Product(2,"Тушенка", 250));
            products.add(new Product(3,"Лук", 35));
        } catch (Exception e) {
            System.out.println("Что-то не так с заполнением списка товаров "+e.getMessage());
        }

    }

    public List<Product> getFilteredProductList(String productName, String minCost, String maxCost) throws Exception {
        List<Product> productsFiltered = new ArrayList<>();
        double min, max;
        if (minCost.isEmpty())
            min = Double.MIN_VALUE;
        else min = Double.parseDouble(minCost);
        if (maxCost.isEmpty())
            max = Double.MAX_VALUE;
        else max = Double.parseDouble(maxCost);
        System.out.println(productName + ";" + min + ";" + max);
        for (Product product : products)
            if (product.getName().toLowerCase().contains(productName.toLowerCase()) && product.getCost() >= min && product.getCost() <= max)
                productsFiltered.add(product);
        return productsFiltered;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public Product findFirstByName(String s){
        for (Product p: products)
            if(p.getName().equals(s))
                return p;
        return null;
    }

    public int maxId() {
        int max = 0;
        for (Product product : products)
            if (max < product.getId())
                max = product.getId();
        return max;
    }

    public List<Product> filterByName(String s){
        return products.stream().filter(p->p.getName().equals(s)).toList();
    }

}
