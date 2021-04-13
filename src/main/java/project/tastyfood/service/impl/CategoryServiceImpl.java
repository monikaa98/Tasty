package project.tastyfood.service.impl;

import org.springframework.stereotype.Service;
import project.tastyfood.model.entity.Category;
import project.tastyfood.model.entity.enums.CategoryName;
import project.tastyfood.repository.CategoryRepository;
import project.tastyfood.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        Category category = new Category(categoryName);
                        categoryRepository.save(category);
                    });
        }

    }

    @Override
    public Category findByName(CategoryName categoryName) {
        return categoryRepository
                .findByName(categoryName)
                .orElse(null);
    }


}
