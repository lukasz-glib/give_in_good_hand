package pl.lg.charity.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.repositories.CategoryRepository;
import pl.lg.charity.dtos.CategoryDataDTO;
import pl.lg.charity.services.CategoryService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDataDTO> findAllCategories() {
        ModelMapper modelMapperFindAllCat = new ModelMapper();
        return categoryRepository.findAll().stream()
                .map(m -> modelMapperFindAllCat.map(m, CategoryDataDTO.class))
                .collect(Collectors.toList());
    }
}
