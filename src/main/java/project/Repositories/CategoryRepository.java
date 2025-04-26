package project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
