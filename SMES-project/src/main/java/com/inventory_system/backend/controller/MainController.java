package com.inventory_system.backend.controller;
import com.inventory_system.backend.dto.ProductDTO;
import com.inventory_system.backend.dto.UserDTO;
import com.inventory_system.backend.entity.Product;
import com.inventory_system.backend.entity.User;
import com.inventory_system.backend.security.UserData;
import com.inventory_system.backend.service.ProductService;
import com.inventory_system.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/")
public class MainController{

    private final UserService userService;
    private final ProductService productService;

    public MainController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String getIndexPage(){
        return "index";
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @GetMapping("/all")
    public String getAllProducts(@AuthenticationPrincipal UserData userData, Model model){
        User user = userService.getById(userData.getId());
        model.addAttribute("user", user);
        List<Product> products = productService.getByUnordered();
        Map m = new HashMap<String, List<Product>>();
        m.put("products", products);
        model.addAllAttributes(m);
        return "all";
    }


    @GetMapping("/product/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getCreateProductPage(Model model){
        model.addAttribute("product", new ProductDTO());
        return "create-product";
    }

    @PostMapping("/product/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createProduct(@ModelAttribute("product") ProductDTO productDTO)
    {
        productService.createProduct(productDTO);
        log.info("Product was created!");
        return "redirect:/home";
    }


    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("user") UserDTO userDTO){
        userService.signup(userDTO, "USER");
        log.info("You signed up");
        return "login";
    }


    @GetMapping("/home")
    public String getHomePage(@AuthenticationPrincipal UserData userData, Model model){
        User user = userService.getById(userData.getId());

        Map<String, List<Product>> m = new HashMap<>();

        m.put("products", user.getAddedProducts());
        model.addAllAttributes(m);
        return "home-page";
    }

    @GetMapping("/logout")
    public String getLogoutPage(){
        log.info("You are logging out");
        return "logout";
    }
}
