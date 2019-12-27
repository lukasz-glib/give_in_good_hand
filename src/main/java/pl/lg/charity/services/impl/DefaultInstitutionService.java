package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.entities.Institution;
import pl.lg.charity.domain.repositories.InstitutionRepository;
import pl.lg.charity.dtos.InstitutionDataDTO;
import pl.lg.charity.services.InstitutionService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class DefaultInstitutionService implements InstitutionService {


    private final InstitutionRepository institutionRepository;

    public DefaultInstitutionService(InstitutionRepository institutionRepositories) {
        this.institutionRepository = institutionRepositories;
    }

    @Override
    public List<InstitutionDataDTO> findAllInstitutions() {
        ModelMapper modelMapperFindAll = new ModelMapper();
        return institutionRepository.findAll().stream()
                .map(m -> modelMapperFindAll.map(m, InstitutionDataDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer numberOfAllInstitutions() {
        return institutionRepository.numberOfAllInstitutions();
    }

    @Override
    public void addInstitution(InstitutionDataDTO institutionData) {
        ModelMapper modelMapper = new ModelMapper();
        Institution institution = modelMapper.map(institutionData, Institution.class);
        institutionRepository.save(institution);
    }

    @Override
    public void deleteInstitution(InstitutionDataDTO institutionData, Long id) {
        Institution institution = institutionRepository.findById(id).get();
        log.debug("Usunięcie instytucji: {}", institution);
        if (institution != null) {
            institutionRepository.delete(institution);
        }
        log.debug("Usunięto instytucję: {}", institution);
    }
}
