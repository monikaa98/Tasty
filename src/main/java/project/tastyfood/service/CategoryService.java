package project.tastyfood.service;

import project.tastyfood.model.entity.Category;
import project.tastyfood.model.entity.enums.CategoryName;

public interface  CategoryService {
    void initCategories();
    Category findByName(CategoryName categoryName);
}
