package pl.lg.charity.services;

import pl.lg.charity.dtos.RegistrationDataDTO;
import java.util.List;

public interface RegistrationService {

    void register (RegistrationDataDTO registrationDataDTO);

    void registerAdmin(RegistrationDataDTO registrationDataDTO);

    List<RegistrationDataDTO> findAllAdmins();

    List<RegistrationDataDTO> findAllUsers();

    void deleteAdminOrUser(RegistrationDataDTO registrationData, Long id);

    RegistrationDataDTO prepareUpdateForAdminDataAccount(Long id);
}
