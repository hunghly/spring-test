package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Product;
import com.codeup.springtest.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    ProductRepository productDao;

    public ProductController(ProductRepository productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String viewProducts(Model view, @PageableDefault(value=10) Pageable pageable) {
        view.addAttribute("page", productDao.findAll(pageable));
//        view.addAttribute("products", productDao.findAll());
        return "products/index";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable long id, Model view) {
        Product product = productDao.getOne(id);
        view.addAttribute("product", product);
        return "products/product-page";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productDao.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/products/create")
    public String showCreateProduct(Model view) {
        view.addAttribute("product", new Product());
        return "products/create";
    }

    @PostMapping("/products/create")
    public String doCreateProduct(@ModelAttribute Product product) {
        productDao.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String viewEditProduct(@PathVariable long id, Model view) {
        Product product = productDao.getOne(id);
        view.addAttribute("product", product);
        return "/products/edit";
    }

    @PostMapping("/products/edit/{id}")
    public String doEditProduct(@PathVariable long id, @ModelAttribute Product product, Model view) {
        Product foundProduct = productDao.getOne(id);
        foundProduct.setName(product.getName());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setPriceInCents(product.getPriceInCents());
        foundProduct.setInStock(product.isInStock());
        productDao.save(foundProduct);
        return "redirect:/products";
    }

    @GetMapping("/products/search")
    public String searchProduct(@RequestParam String query, Model view, @PageableDefault(value=10) Pageable pageable) {
//        System.out.println(query);
        Page<Product> productList = productDao.searchByNameOrDescription(query, pageable);
//        for (Product product : productList) {
//            System.out.println(product.getName());
//        }
        view.addAttribute("query", query);
        view.addAttribute("page", productList);
        return "products/index";
    }

}
