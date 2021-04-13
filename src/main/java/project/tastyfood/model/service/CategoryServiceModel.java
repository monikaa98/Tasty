package project.tastyfood.model.service;

import project.tastyfood.model.entity.enums.CategoryName;

public class CategoryServiceModel {
    private CategoryName categoryName;

    public CategoryServiceModel() {
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }
}
