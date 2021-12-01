package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.model.Product;
import by.bsu.tp.lab2.model.ProductSeason;
import by.bsu.tp.lab2.model.SeasonCheck;
import by.bsu.tp.lab2.model.dto.ProductDto;
import by.bsu.tp.lab2.repository.ProductSeasonRepository;
import by.bsu.tp.lab2.service.ProductService;
import by.bsu.tp.lab2.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final AuthenticationUtil authenticationUtil;
    private final ProductSeasonRepository productSeasonRepository;

    @GetMapping
    public String index(@RequestParam(value = "season", required = false) String[] seasons, Model model) {
        model.addAttribute("products", productService.getAllProducts(seasons));
        authenticationUtil.injectUser(model);
        model.addAttribute("seasonCheck", new SeasonCheck(seasons));
        return "product/index";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product, Model model) {
        authenticationUtil.injectUser(model);
        model.addAttribute("seasons", productSeasonRepository.findAll());
        return "product/new";
    }

    @GetMapping("/{id}")
    public String one(@PathVariable Long id, Model model) {
        authenticationUtil.injectUser(model);
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("seasons", productSeasonRepository.findAll());
        model.addAttribute("productSeasonIds", product.getProductSeasons().stream().map(ProductSeason::getId).collect(Collectors.toList()));
        model.addAttribute("seasonCheck", new SeasonCheck(product.getProductSeasons()));
        return "product/one";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("seasons", productSeasonRepository.findAll());
        model.addAttribute("productSeasonIds", product.getProductSeasons().stream().map(ProductSeason::getId).collect(Collectors.toList()));
        model.addAttribute("seasonCheck", new SeasonCheck(product.getProductSeasons()));
        authenticationUtil.injectUser(model);
        return "product/edit";
    }

    @PostMapping
    public String createProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") long id, @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }
}
