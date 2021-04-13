package project.tastyfood.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.tastyfood.model.entity.Category;
import project.tastyfood.model.entity.enums.CategoryName;
import project.tastyfood.repository.CategoryRepository;

import javax.transaction.Transactional;


import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInitMethodNotToBeInvokeAfterTheFirstInvoke(){
        CategoryService categoryService=mock(CategoryService.class);
        verify(categoryService,times(0)).initCategories();
    }

    @Test
    public void testInitMethod(){
        if(categoryRepository.count()==0){
            this.categoryService.initCategories();

        }

    }
    @Test(expected = Exception.class)
    public void testFindCategoriesWithException(){
        CategoryService categoryService=mock(CategoryService.class);
        Category category=categoryService.findByName(CategoryName.valueOf("INVALID"));

    }
    @Test
    public void testFindCategories(){
        Category category = categoryService.findByName(CategoryName.valueOf("САЛАТИ"));
        Assert.assertEquals("САЛАТИ",category.getName().toString());
    }

}
