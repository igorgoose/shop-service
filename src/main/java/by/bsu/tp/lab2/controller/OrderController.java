package by.bsu.tp.lab2.controller;

import by.bsu.tp.lab2.model.OrderRequest;
import by.bsu.tp.lab2.model.SeasonCheck;
import by.bsu.tp.lab2.service.OrderRequestService;
import by.bsu.tp.lab2.service.ProductService;
import by.bsu.tp.lab2.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRequestService orderRequestService;
    private final AuthenticationUtil authenticationUtil;
    private final ProductService productService;

    @GetMapping()
    public String index(@RequestParam(value = "status", required = false) String status,
                        Model model) {
        List<OrderRequest> orders;
        if (status != null) {
            orders = orderRequestService.getOrdersByStatus(status);
        } else {
            orders = orderRequestService.getOrders();
        }
        model.addAttribute("orders", orders);
        authenticationUtil.injectEmployee(model);
        return "order/index";
    }

    @GetMapping("/{id}")
    public String one(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderRequestService.getById(id));
        authenticationUtil.injectEmployee(model);
        return "order/one";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderRequestService.getById(id));
        authenticationUtil.injectEmployee(model);
        return "order/edit";
    }

    @GetMapping("/{id}/products")
    public String addProducts(@RequestParam(value = "season", required = false) String[] seasons,
            @PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderRequestService.getById(id));
        model.addAttribute("products", productService.getAllProducts(seasons));
        model.addAttribute("seasonCheck", new SeasonCheck(seasons));
        authenticationUtil.injectEmployee(model);
        return "order/addProducts";
    }

    @PatchMapping("/{id}/products/{product_id}/remove")
    public String removeProduct(@ModelAttribute("quantity") int quantity,
                                @PathVariable("product_id") long productId,
                                @PathVariable("id") long orderId,
                                Model model) {
        orderRequestService.removeProduct(orderId, productId, quantity);
        model.addAttribute("order", orderRequestService.getById(orderId));
        authenticationUtil.injectEmployee(model);
        return "redirect:/orders/" + orderId + "/edit";
    }

    @PatchMapping("/{id}/status")
    public String updateStatus(@PathVariable("id") long id,
                               @ModelAttribute("status") String status,
                               @ModelAttribute("redirect") String redirect) {
        orderRequestService.updateStatus(id, status);
        return "redirect:" + redirect;
    }

    @GetMapping(value = "/{id}/bill", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public Resource getBill(@PathVariable("id") long id, @ModelAttribute("redirect") String redirect) {
        return new ByteArrayResource(orderRequestService.getBillByOrderId(id));
    }

    @PostMapping("/{id}/bill")
    public String issueBill(@PathVariable("id") long id, @ModelAttribute("redirect") String redirect) {
        orderRequestService.issueBill(id);
        return "redirect:/orders/" + id;
    }
}
