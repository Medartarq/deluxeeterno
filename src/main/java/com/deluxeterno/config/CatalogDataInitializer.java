package com.deluxeterno.config;

import com.deluxeterno.domain.Category;
import com.deluxeterno.domain.Product;
import com.deluxeterno.repository.CategoryRepository;
import com.deluxeterno.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CatalogDataInitializer implements CommandLineRunner {

    private static final List<String> DEMO_IMAGE_PATHS = List.of(
            "/assets/product-flower.svg",
            "/assets/product-breakfast.svg",
            "/assets/product-box.svg");

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CatalogDataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            Category flowers = categoryRepository.save(new Category("Flores", "Flores naturales, eternas y arreglos especiales."));
            Category breakfast = categoryRepository.save(new Category("Desayunos", "Desayunos sorpresa y picnics para ocasiones especiales."));
            Category gifts = categoryRepository.save(new Category("Regalos", "Boxes, regalos y detalles personalizados para distintas celebraciones."));
            productRepository.saveAll(List.of(
                    inactiveProduct("Arreglo floral personalizado", "Flores, tarjeta y presentación adaptable a la ocasión.", "/assets/product-flower.svg", flowers),
                    inactiveProduct("Desayuno sorpresa", "Propuesta visual para pedidos especiales y celebraciones.", "/assets/product-breakfast.svg", breakfast),
                    inactiveProduct("Box personalizado", "Caja temática con diferentes elementos y dedicatoria.", "/assets/product-box.svg", gifts)));
        }

        productRepository.findAll().stream()
                .filter(product -> DEMO_IMAGE_PATHS.contains(product.getImagePath()))
                .filter(Product::isActive)
                .forEach(product -> product.setActive(false));
    }

    private Product inactiveProduct(String name, String description, String imagePath, Category category) {
        Product product = new Product(name, description, imagePath, category);
        product.setActive(false);
        return product;
    }
}
