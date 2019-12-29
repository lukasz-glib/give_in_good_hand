package pl.lg.charity.services;

import pl.lg.charity.dtos.RegistrationDataDTO;

import java.util.List;

public interface RegistrationService {

    void register (RegistrationDataDTO registrationDataDTO);

    List<RegistrationDataDTO> findAllAdmins();

    void deleteAdmin(RegistrationDataDTO registrationData, Long id);
}
