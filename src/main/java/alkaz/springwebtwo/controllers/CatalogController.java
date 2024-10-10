package alkaz.springwebtwo.controllers;

import alkaz.springwebtwo.entities.Product;
import alkaz.springwebtwo.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller //аннотация указывает Спрингу, что данный класс является контроллером, то есть его единственный
            //экземпляр обрабатывает какие-то http-запросы, то есть в нем будут методы с @GetMapping или @PostMapping
public class CatalogController {
    @Autowired              // автосвязывание текущего бина (productController) с бином productsService,
                            // который должен быть в контексте спрингового приложения
    ProductService productService;

    @GetMapping("/catalog")
    public String showCatalog(
            Model model,
            @RequestParam(name = "productName", defaultValue = "") String productName,
            @RequestParam(name = "minCost", defaultValue = "") String minCost,
            @RequestParam(name = "maxCost", defaultValue = "") String maxCost
    ){
        List<Product> spisok;
        model.addAttribute("productName", productName);
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
        if (productName.isEmpty() && minCost.isEmpty() && maxCost.isEmpty())
            spisok = productService.getAllList();
        else try {
            spisok = productService.getFilteredProductList(productName, minCost, maxCost);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            spisok = productService.getAllList();
        }

        model.addAttribute("spisok_produktov", spisok);
        return "catalog";        //передаем шаблон, который уже будет работать с model
    }

    @GetMapping("/add_product")
    public String addProduct(
            Model model,
            @RequestParam(name = "productName", defaultValue = "") String productName,
            @RequestParam(name = "productCost", defaultValue = "") String productCost,
            HttpServletRequest request
    ) {
        if (productName.isEmpty() || productCost.isEmpty()) {
            model.addAttribute("report", "Введите параметры продукта");
            return "add_product";
        }
        try {
            if (Double.parseDouble(productCost) < 0)
                throw new Exception("Цена не может быть отрицательной");
            Product product = new Product(productService.maxId() + 1, productName, Double.parseDouble(productCost));
            productService.addProduct(product);
        } catch (Exception e) {
            model.addAttribute("report", "Ошибка " +
                    e.getClass().getName() + ": " + e.getMessage());
            return "add_product";
        }
        return "redirect:catalog";
    }

    @GetMapping("/product")
    public String showProduct(
            Model model,
            @RequestParam(name = "product") Product product
    ){
        model.addAttribute("product", product);
        return "product";
    }


}
