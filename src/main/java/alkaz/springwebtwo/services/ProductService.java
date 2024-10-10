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

    public void addProduct(Product product){
        products.add(product);
    }

    public Product findFirstByName(String s){
        for (Product p: products)
            if(p.getName().equals(s))
                return p;
        return null;
    }

    public List<Product> filterByName(String s){
        return products.stream().filter(p->p.getName().equals(s)).toList();
    }

}
