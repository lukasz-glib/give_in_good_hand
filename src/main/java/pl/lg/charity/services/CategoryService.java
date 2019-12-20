package pl.lg.charity.services;

import pl.lg.charity.domain.entities.Category;
import pl.lg.charity.dtos.CategoryDataDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDataDTO> findAllCategories();

}
