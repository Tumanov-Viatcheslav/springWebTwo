package alkaz.springwebtwo.controllers;

import alkaz.springwebtwo.entities.Product;
import alkaz.springwebtwo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller //аннотация указывает Спрингу, что данный класс является контроллером, то есть его единственный
            //экземпляр обрабатывает какие-то http-запросы, то есть в нем будут методы с @GetMapping или @PostMapping
public class CatalogController {
    @Autowired              // автосвязывание текущего бина (productController) с бином productsService,
                            // который должен быть в контексте спрингового приложения
    ProductService productService;

    @GetMapping("/catalog")
    public String showCatalog(Model model){
        List<Product> spisok = productService.getAllList();

        model.addAttribute("spisok_produktov", spisok);
        return "catalog";        //передаем шаблон, который уже будет работать с model
    }


}
