package com.example.Cart.Controller;

import com.example.Cart.Dto.OrderDto;
import com.example.Cart.Dto.OrderHistDto;
import com.example.Cart.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto
            , BindingResult bindingResult, Principal principal) {

        if (principal == null) {
            // 사용자가 로그인되어 있지 않은 경우 로그인 폼으로 리다이렉트
            return new ResponseEntity<String>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }

        if (bindingResult.hasErrors()) { //에러가 있는지 검사
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String memId = principal.getName();
        Long orderId;

        try {
            orderId = orderService.order(orderDto, memId);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
    // 사용자가 로그인되어 있지 않은 경우 로그인 폼으로 리다이렉트하는 메서드
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView redirectToLoginForm() {
        return new ModelAndView("redirect:/members/login");
    }


    //예약 리스트
    @GetMapping(value = {"/orders", "/orders/{page}"})
    public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<OrderHistDto> ordersHistDtoList = orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", ordersHistDtoList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);

        return "order/orderHist";
    }




}