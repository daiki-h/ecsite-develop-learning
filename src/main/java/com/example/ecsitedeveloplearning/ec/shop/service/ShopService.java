package com.example.ecsitedeveloplearning.ec.shop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecsitedeveloplearning.ec.shop.model.Category;
import com.example.ecsitedeveloplearning.ec.shop.model.Product;
import com.example.ecsitedeveloplearning.ec.shop.repository.CategoryRepository;
import com.example.ecsitedeveloplearning.ec.shop.repository.ProductRepository;

@Service
public class ShopService {

	@Value("${upload.image.path}")
	private String uploadImagePath;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Category> findCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * 商品登録
	 * 
	 * @param product
	 * @param image
	 */
	public void register(Product product, MultipartFile image) {
		String imagePath = saveImageFile(image);
		if (imagePath.length() > 0) {
			product.setImagePath(imagePath);
			productRepository.save(product);
		}
	}
	
	public Product findProductById(long productId) {
		Product product = productRepository.findById(productId).get();
		return product;
	}

	private String saveImageFile(MultipartFile image) {
		
		String imagePath = "";
		try {
			// Image Info
			String originFilename = image.getOriginalFilename();
//			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());

			// Save Image File
			imagePath = genSaveFileName(originFilename);

			System.out.println("originFilename : " + originFilename);
			System.out.println("saveFileName : " + imagePath);

			writeFile(image, imagePath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return imagePath;
	}

	// Create Image File Name
	private String genSaveFileName(String imageName) {
		Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        
        String format = sdf.format(cl.getTime());
		String fileName = "";
		fileName += format;
		fileName += imageName;

		return fileName;
	}

	// Write Image File
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		boolean result = false;
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(uploadImagePath + "/" + saveFileName);
		fos.write(data);
		fos.close();
		return result;
	}

	// 商品修正
	public void update(Product product, MultipartFile image) {
		Product originalProduct = productRepository.findById(product.getId()).get();
		
		File originalImage = new File(originalProduct.getImagePath());
		originalImage.delete();
		
		String imagePath = saveImageFile(image);
		if (imagePath.length() > 0) {
			product.setImagePath(imagePath);
			productRepository.save(product);
		}
	}

	// 商品削除
	public void delete(long productId) {
		productRepository.deleteById(productId);
	}
	
}
