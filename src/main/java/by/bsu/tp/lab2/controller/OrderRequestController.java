package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.service.OrderRequestService;
import by.bsu.tp.lab2.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/requests")
public class OrderRequestController {

    private final ProductService productService;
    private final OrderRequestService orderRequestService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("requests", orderRequestService.getAll());
        return "request/index";
    }

    @GetMapping("/{id}")
    public String one(@PathVariable("id") long id, Model model) {
        model.addAttribute("request", orderRequestService.getById(id));
        return "request/one";
    }

    @GetMapping("/new")
    public String newRequest(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("request", orderRequestService.create());
        return "request/edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("request", orderRequestService.getById(id));
        model.addAttribute("products", productService.getAllProducts());
        return "request/edit";
    }

    @GetMapping("/{id}/patch")
    public String patch(@PathVariable("id") long requestId, Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("requestId", requestId);
        return "request/patch";
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") long id, Model model) {
        model.addAttribute("request", orderRequestService.getById(id));
        model.addAttribute("products", productService.getAllProducts());
        return "redirect:" + id;
    }

    @PatchMapping("/{id}")
    public String addProduct(@ModelAttribute("product_id") long productId,
                              @ModelAttribute("quantity") int quantity,
                              @PathVariable("id") long requestId,
                              Model model) {
        orderRequestService.addProduct(requestId, productId, quantity);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("requestId", requestId);
        return "redirect:" + requestId + "/edit";
    }

}
