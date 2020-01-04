package pl.lg.charity.services;

import pl.lg.charity.dtos.RegistrationDataDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface UserService {

    RegistrationDataDTO prepareEditDataUser (Principal principal);

    void processEditDataUser(RegistrationDataDTO dataDTO, Principal principal, HttpServletRequest req)
            throws ServletException;

    void processEditPasswordUser(String password, Principal principal, HttpServletRequest req)
            throws ServletException;
}
