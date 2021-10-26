package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sun.text.normalizer.ICUBinary;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;


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

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order){
//    public String orderForm(Model model){

//        model.addAttribute("order", new Order());

        if(order.getDeliveryName() == null){
            order.setDeliveryName(user.getFullname());
        }
        if(order.getDeliveryStreet() == null){
            order.setDeliveryStreet(user.getStreet());
        }
        if(order.getDeliveryCity() == null){
            order.setDeliveryCity(user.getCity());
        }
        if(order.getDeliveryState() == null){
            order.setDeliveryState(user.getState());
        }
        if(order.getDeliveryZip() == null){
            order.setDeliveryZip(user.getZip());
        }
        return  "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){

        if(errors.hasErrors()){
            return "orderForm";
        }

//        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authenticate.getPrincipal();

        order.setUser(user);
        log.info("Order submitted: " + order);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
