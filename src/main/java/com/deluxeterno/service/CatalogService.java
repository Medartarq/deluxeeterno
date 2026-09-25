package com.deluxeterno.service;

import com.deluxeterno.domain.Category;
import com.deluxeterno.domain.Product;
import com.deluxeterno.dto.CategoryForm;
import com.deluxeterno.dto.ProductForm;
import com.deluxeterno.repository.CategoryRepository;
import com.deluxeterno.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CatalogService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CatalogService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> findCategories() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public List<Product> findActiveProducts(Long categoryId) {
        return categoryId == null
                ? productRepository.findAllByActiveTrueOrderByNameAsc()
                : productRepository.findByActiveTrueAndCategory_IdOrderByNameAsc(categoryId);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAllByOrderByNameAsc();
    }

    public Product findProduct(Long id) {
        return productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    @Transactional
    public Product createProduct(ProductForm form) {
        Product product = new Product(
                form.getName(),
                form.getDescription(),
                form.getImagePath(),
                findCategory(form.getCategoryId()));
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductForm form) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setImagePath(form.getImagePath());
        product.setCategory(findCategory(form.getCategoryId()));
        return productRepository.save(product);
    }

    @Transactional
    public void deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        product.setActive(false);
    }

    @Transactional
    public Category createCategory(CategoryForm form) {
        return categoryRepository.save(new Category(form.getName(), form.getDescription()));
    }

    @Transactional
    public Category updateCategory(Long id, CategoryForm form) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
        category.setName(form.getName());
        category.setDescription(form.getDescription());
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (productRepository.existsByCategory_Id(id)) {
            throw new IllegalStateException("No se puede eliminar una categoría con productos asociados.");
        }
        categoryRepository.deleteById(id);
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
    }
}
