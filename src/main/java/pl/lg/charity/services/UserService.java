package pl.lg.charity.services;

import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.dtos.UpdateUserDataByAdminDataDTO;
import pl.lg.charity.dtos.UpdateUserDataDTO;
import pl.lg.charity.dtos.UpdateUserPasswordDataDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface UserService {

    UpdateUserDataDTO prepareEditDataUser (Principal principal);

    void processEditDataUser(UpdateUserDataDTO dataDTO, Principal principal, HttpServletRequest req)
            throws ServletException;

    UpdateUserPasswordDataDTO prepareEditPasswordUser(Principal principal);

    void processEditPasswordUser(UpdateUserPasswordDataDTO dataDTO, Principal principal, HttpServletRequest req)
            throws ServletException;

    void processEditDataUsersByAdmin(UpdateUserDataByAdminDataDTO dataDTO);

    void processEditDataAdminsByAdmin(UpdateUserDataByAdminDataDTO dataDTO);
}
