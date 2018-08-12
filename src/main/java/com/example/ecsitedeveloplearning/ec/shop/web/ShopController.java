package com.example.ecsitedeveloplearning.ec.shop.web;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C:/Dev/workspace/sts/ecsite-develop-learning/products";
	
	// TOP 画面
	@GetMapping("/top")
	public ModelAndView viewIndex() {
		ModelAndView mv = new ModelAndView("shop/top");
		List<Product> products = shopService.findAll();
		mv.addObject("products", products);
		return mv;
	}
	// 商品画面
	@GetMapping("/product")
	public ModelAndView viewRegisterProduct() {
		ModelAndView mv = new ModelAndView("shop/registerProduct");
		List<Category> categories = shopService.findCategories();
//		mv.addObject("data", new Product());
		mv.addObject("categories", categories);
		
		logger.info("categories : " + categories);
		return mv;
	}
	
	// 商品登録
	@PostMapping("/product")
	public void insertProduct(
							@RequestParam("name") String name,
							@RequestParam("category") int category,
							@RequestParam("price") int price,
							@RequestParam("description") String description,
		      				@RequestParam("image") MultipartFile image) {
		
//		ModelAndView mv = new ModelAndView("shop/top");
//		return mv;
	}
	
}
