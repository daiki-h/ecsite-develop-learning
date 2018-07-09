package com.example.ecsitedeveloplearning.ec.shop.web;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.ecsitedeveloplearning.ec.shop.model.Category;
import com.example.ecsitedeveloplearning.ec.shop.model.Product;
import com.example.ecsitedeveloplearning.ec.shop.service.ShopService;

@Controller
@RequestMapping(path="/shop")
public class ShopController {
	
	private static Logger logger = Logger.getLogger(ShopController.class);
	
	@Autowired
	private ShopService shopService;
	
	// top画面
	@GetMapping("/top")
	public ModelAndView viewIndex() {
		ModelAndView mv = new ModelAndView("shop/top");
		List<Product> products = shopService.findAll();
		mv.addObject("products", products);
		
		logger.info("products : " + products);
		return mv;
	}
	
	// 商品登録画面
	@GetMapping("/product")
	public ModelAndView viewRegisterProduct() {
		ModelAndView mv = new ModelAndView("shop/registerProduct");
		List<Category> categories = shopService.findCategories();
		mv.addObject("data", new Product());
		mv.addObject("categories", categories);
		
		logger.info("categories : " + categories);
		return mv;
	}
	
	// 商品登録
	@PostMapping("/product")
	public ModelAndView insertProduct(@ModelAttribute Product product) {
		logger.info("product : " + product);
		ModelAndView mv = new ModelAndView("shop/top");
		return mv;
	}
	
}
