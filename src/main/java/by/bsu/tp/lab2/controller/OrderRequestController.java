package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.model.OrderRequest;
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
    public String newRequest() {
        return "redirect:/requests/" + orderRequestService.create().getId() + "/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("request", orderRequestService.getById(id));
        return "request/edit";
    }

    @GetMapping("/{id}/products")
    public String addProducts(@PathVariable("id") long id, Model model) {
        model.addAttribute("request", orderRequestService.getById(id));
        model.addAttribute("products", productService.getAllProducts());
        return "request/addProducts";
    }

    @GetMapping("/{id}/patch")
    public String patch(@PathVariable("id") long requestId, Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("requestId", requestId);
        return "request/patch";
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") long id,
                      @ModelAttribute("request") OrderRequest request,
                      Model model) {
        model.addAttribute("request", orderRequestService.update(id, request));
        model.addAttribute("products", productService.getAllProducts());
        return "redirect:" + id;
    }

    @PatchMapping("/{id}/products/{product_id}/add")
    public String addProduct(@ModelAttribute("quantity") int quantity,
                             @PathVariable("product_id") long productId,
                             @PathVariable("id") long requestId,
                             Model model) {
        orderRequestService.addProduct(requestId, productId, quantity);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("request", orderRequestService.getById(requestId));
        return "redirect:/requests/" + requestId + "/products";
    }

    @PatchMapping("/{id}/products/{product_id}/remove")
    public String removeProduct(@ModelAttribute("quantity") int quantity,
                                @PathVariable("product_id") long productId,
                                @PathVariable("id") long requestId,
                                Model model) {
        orderRequestService.removeProduct(requestId, productId, quantity);
        model.addAttribute("request", orderRequestService.getById(requestId));
        return "redirect:/requests/" + requestId + "/edit";
    }

}
