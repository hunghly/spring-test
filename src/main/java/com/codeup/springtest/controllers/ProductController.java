package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Product;
import com.codeup.springtest.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    ProductRepository productDao;

    public ProductController(ProductRepository productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String viewProducts(Model view) {
        view.addAttribute("products", productDao.findAll());
        return "/products/index";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable long id, Model view) {
        Product product = productDao.getOne(id);
        view.addAttribute("product", product);
        return "/products/product-page";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productDao.delete(productDao.getOne(id));
        return "redirect:/products";
    }

    @GetMapping("/products/create")
    public String showCreateProduct(Model view) {
        view.addAttribute("product", new Product());
        return "/products/create";
    }

    @PostMapping("/products/create")
    public String doCreateProduct(@ModelAttribute Product product) {
        productDao.save(product);
        return "redirect:/products";
    }

}
