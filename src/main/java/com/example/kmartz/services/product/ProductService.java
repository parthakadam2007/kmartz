package com.example.kmartz.services.product;
import com.example.kmartz.dto.ProductDTO;
import java.util.List;
public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long productId);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByShopkeeperId(Long shopkeeperId);
    // ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    // void deleteProduct(Long productId);
}
