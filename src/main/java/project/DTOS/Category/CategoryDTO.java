package project.DTOS.Category;

import project.Entities.Category;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record CategoryDTO(Long id, String name) implements Serializable {

    @Serial
    public static final long serialVersionUID = -4297989842874219L;

    public static CategoryDTO fromCategory(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryDTO(category.getId(), category.getName());
    }

    public static Category toCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        return new Category(categoryDTO.id, categoryDTO.name);
    }

    public static List<CategoryDTO> fromCategories(List<Category> categories) {
        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        if (categories != null && !categories.isEmpty()) {
            categoriesDTO.addAll(categories.stream().map(CategoryDTO::fromCategory).toList());
        }

        return categoriesDTO;
    }

    public static List<Category> toCategories(List<CategoryDTO> categoriesDTO) {
        List<Category> categories = new ArrayList<>();

        if (categoriesDTO != null && !categoriesDTO.isEmpty()) {
            categories.addAll(categoriesDTO.stream().map(CategoryDTO::toCategory).toList());
        }

        return categories;
    }
}
