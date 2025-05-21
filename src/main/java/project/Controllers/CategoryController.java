package project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.Category.CategoryDTO;
import project.Entities.Category;
import project.Services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/{category}")
    public @ResponseBody ResponseEntity<CategoryDTO> create(@PathVariable String category) {
        Category createdCategory = categoryService.create(category);

        return ResponseEntity.ok(CategoryDTO.fromCategory(createdCategory));
    }
}
