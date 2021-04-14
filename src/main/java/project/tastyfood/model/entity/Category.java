package project.tastyfood.model.entity;

import project.tastyfood.model.entity.enums.CategoryName;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private CategoryName name;

    public Category() {
    }

    public Category(CategoryName name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

}
