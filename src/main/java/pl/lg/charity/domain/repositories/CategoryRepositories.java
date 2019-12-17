package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lg.charity.domain.entities.Category;

public interface CategoryRepositories extends JpaRepository<Category, Long> {

}
