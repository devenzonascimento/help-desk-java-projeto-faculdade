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

    public static List<CategoryDTO> fromCategorys(List<Category> categorys) {
        List<CategoryDTO> userDTO = new ArrayList<>();

        if (categorys != null && !categorys.isEmpty()) {
            userDTO.addAll(categorys.stream().map(CategoryDTO::fromCategory).toList());
        }

        return userDTO;
    }

    public static List<Category> toCategorys(List<CategoryDTO> categorysDTO) {
        List<Category> users = new ArrayList<>();

        if (categorysDTO != null && !categorysDTO.isEmpty()) {
            users.addAll(categorysDTO.stream().map(CategoryDTO::toCategory).toList());
        }

        return users;
    }
}
