package pl.lg.charity.services;

import pl.lg.charity.dtos.InstitutionDataDTO;

import java.util.List;

public interface InstitutionService {

    List<InstitutionDataDTO> findAllInstitutions();
}
