package project.DTOS;

import project.Entities.Category;

import java.io.Serial;
import java.io.Serializable;

public record CategoryDTO(Long id, String category) implements Serializable {

    @Serial
    public static final long serialVersionUID = -4297989842874219L;

    public static CategoryDTO valueOf(Category category) {
        if (category != null) {
            return new CategoryDTO(
                    category.getId(),
                    category.getCategory()
            );
        }
        return null;
    }

    public static Category toCategory(CategoryDTO dto) {
        if (dto != null) {
            return new Category(dto.id, dto.category);
        }
        return null;
    }
}
