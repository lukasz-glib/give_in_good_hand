package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.repositories.InstitutionRepositories;
import pl.lg.charity.dtos.InstitutionDataDTO;
import pl.lg.charity.services.InstitutionService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class DefaultInstitutionService implements InstitutionService {


    private final InstitutionRepositories institutionRepositories;

    public DefaultInstitutionService(InstitutionRepositories institutionRepositories) {
        this.institutionRepositories = institutionRepositories;
    }

    @Override
    public List<InstitutionDataDTO> findAllInstitutions() {
        ModelMapper modelMapperFindAll = new ModelMapper();
        return institutionRepositories.findAll().stream()
                .map(m -> modelMapperFindAll.map(m, InstitutionDataDTO.class))
                .collect(Collectors.toList());
    }
}
