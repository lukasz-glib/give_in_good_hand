package pl.lg.charity.services;

import pl.lg.charity.dtos.DeleteAdminValidationDataDTO;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.dtos.UpdateUserDataByAdminDataDTO;

import java.util.List;

public interface RegistrationService {

    void register (RegistrationDataDTO registrationDataDTO);

    void registerAdmin(RegistrationDataDTO registrationDataDTO);

    List<RegistrationDataDTO> findAllAdmins();

    List<RegistrationDataDTO> findAllUsers();

    DeleteAdminValidationDataDTO findAdminToDeleteById(Long id);

    void deleteAdminOrUser(RegistrationDataDTO registrationData, Long id);

    UpdateUserDataByAdminDataDTO prepareUpdateForAdminDataAccount(Long id);

    UpdateUserDataByAdminDataDTO prepareUpdateForUserDataAccount(Long id);

    void lockUser(Long id);

    void unlockUser(Long id);

    void makeUsersStatusActive(Long id);
}
