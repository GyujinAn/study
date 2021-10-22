package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.data.OrderRepository;
import tacos.jpa.entity.OrderEntity;
import tacos.jpa.repo.OrderRepo;

import javax.validation.Valid;

/**
 * @author agj017@gmail.com
 * @since 2021/10/12
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepo orderRepository;

    public OrderController(OrderRepo orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(){
//    public String orderForm(Model model){

//        model.addAttribute("order", new Order());
        return  "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid OrderEntity order, Errors errors, SessionStatus sessionStatus){

        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted: " + order);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
