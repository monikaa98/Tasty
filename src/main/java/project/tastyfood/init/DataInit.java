package project.tastyfood.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.tastyfood.service.CategoryService;
import project.tastyfood.service.UserService;


@Component
public class DataInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public DataInit(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
        this.userService.createAdmin();
    }
}
