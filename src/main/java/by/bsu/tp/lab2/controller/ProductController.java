package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.service.ProductService;
import by.bsu.tp.lab2.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;
    private final AuthenticationUtil authenticationUtil;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        authenticationUtil.injectEmployee(model);
        return "products/products";
    }
}
