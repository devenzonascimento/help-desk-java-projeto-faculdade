package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Entities.Category;
import project.Repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category create(String category) {
        Category newCategory = new Category();
        newCategory.setCategory(category);

        return categoryRepository.save(newCategory);
    }
}
