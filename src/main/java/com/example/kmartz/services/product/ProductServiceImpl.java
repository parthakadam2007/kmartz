package com.example.kmartz.services.product;

import com.example.kmartz.dto.ProductDTO;
import com.example.kmartz.models.Product;
import com.example.kmartz.models.ShopKeeper;
import com.example.kmartz.repository.ProductRepository;
import com.example.kmartz.repository.ShopKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopKeeperRepository shopKeeperRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        // ✅ Validate Shopkeeper
        // ShopKeeper shopkeeper = shopKeeperRepository.findById(productDTO.getShopkeeperId())
        //         .orElseThrow(() -> new RuntimeException("Shopkeeper not found with ID: " + productDTO.getShopkeeperId()));

        // ✅ Convert DTO → Entity
        Product product = new Product();
        product.setShopkeeperId(productDTO.getShopkeeperId()); // <-- fixed method name
        product.setImageUrl(productDTO.getImageUrl());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setRating(productDTO.getRating());
        product.setPrice(productDTO.getPrice());

        // ✅ Save product
        Product savedProduct = productRepository.save(product);

        // ✅ Return DTO
        return convertToDTO(savedProduct);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        return convertToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByShopkeeperId(Long shopkeeperId) {
        // ✅ Use custom native query instead of filtering in memory
        return productRepository.findByShopkeeperId(shopkeeperId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔁 Helper: Entity → DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setShopkeeperId(product.getShopkeeperId());
        dto.setImageUrl(product.getImageUrl());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setRating(product.getRating());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
