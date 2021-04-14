package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.model.SeasonCheck;
import by.bsu.tp.lab2.service.ProductService;
import by.bsu.tp.lab2.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static by.bsu.tp.lab2.util.ProductDtoConverter.convert;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;
    private final AuthenticationUtil authenticationUtil;

    @GetMapping
    public String index(@RequestParam(value = "season", required = false) String[] seasons, Model model) {
        model.addAttribute("products", convert(productService.getAllProducts(seasons)));
        authenticationUtil.injectEmployee(model);
        model.addAttribute("seasonCheck", new SeasonCheck(seasons));
        return "product/index";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product, Model model) {
        authenticationUtil.injectEmployee(model);
        return "product/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        authenticationUtil.injectEmployee(model);
        return "product/edit";
    }

    @PostMapping
    public String createProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") long id,
                                @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }
}
