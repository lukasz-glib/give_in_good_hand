package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.entities.Role;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.repositories.RoleRepository;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.dtos.UpdateUserDataDTO;
import pl.lg.charity.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
@Slf4j
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DefaultUserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                              RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public RegistrationDataDTO prepareEditDataUser(Principal principal) {
        User showDataLoggedUser = userRepository.findUserByEmail(principal.getName());
        ModelMapper model = new ModelMapper();
        return model.map(showDataLoggedUser, RegistrationDataDTO.class);
    }

    @Override
    public void processEditDataUser(RegistrationDataDTO dataDTO, Principal principal, HttpServletRequest req)
            throws ServletException {
        User editDataLoggedUser = userRepository.findUserByEmail(principal.getName());
        editDataLoggedUser.setEmail(dataDTO.getEmail());
        editDataLoggedUser.setUsername(dataDTO.getUsername());
        editDataLoggedUser.setLastName(dataDTO.getLastName());
        userRepository.save(editDataLoggedUser);
        req.logout();
    }

    @Override
    public RegistrationDataDTO prepareEditPasswordUser(Principal principal) {
        User showPasswordLoggedUser = userRepository.findUserByEmail(principal.getName());
        ModelMapper model = new ModelMapper();
        return model.map(showPasswordLoggedUser, RegistrationDataDTO.class);
    }

    @Override
    public void processEditPasswordUser(RegistrationDataDTO dataDTO, Principal principal, HttpServletRequest req)
            throws ServletException {
        User editPasswordLoggedUser = userRepository.findUserByEmail(principal.getName());
        editPasswordLoggedUser.setPassword(passwordEncoder.encode(dataDTO.getPassword()));
        req.logout();
    }

    @Override
    public void processEditDataUsersByAdmin(UpdateUserDataDTO dataDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(dataDTO, User.class);
        user.setActive(Boolean.TRUE);
        String encodedPassword = passwordEncoder.encode(dataDTO.getPassword());
        user.setPassword(encodedPassword);
        Role roleUser = roleRepository.getByName("ROLE_USER");
        user.getRoles().add(roleUser);
        userRepository.save(user);
    }

    @Override
    public void processEditDataAdminsByAdmin(UpdateUserDataDTO dataDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(dataDTO, User.class);
        user.setActive(Boolean.TRUE);
        String encodedPassword = passwordEncoder.encode(dataDTO.getPassword());
        user.setPassword(encodedPassword);
        Role roleUser = roleRepository.getByName("ROLE_ADMIN");
        user.getRoles().add(roleUser);
        userRepository.save(user);
    }

}
