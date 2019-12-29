package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.entities.Role;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.repositories.RoleRepository;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.services.RegistrationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DefaultRegistrationService implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DefaultRegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                                      RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(RegistrationDataDTO registrationDataDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registrationDataDTO, User.class);
        user.setActive(Boolean.TRUE);
        String encodedPassword = passwordEncoder.encode(registrationDataDTO.getPassword());
        user.setPassword(encodedPassword);
        Role roleUser = roleRepository.getByName("ROLE_USER");
        user.getRoles().add(roleUser);
        userRepository.save(user);
    }

    @Override
    public void registerAdmin(RegistrationDataDTO registrationDataDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registrationDataDTO, User.class);
        user.setActive(Boolean.TRUE);
        String encodedPassword = passwordEncoder.encode(registrationDataDTO.getPassword());
        user.setPassword(encodedPassword);
        Role roleUser = roleRepository.getByName("ROLE_ADMIN");
        user.getRoles().add(roleUser);
        userRepository.save(user);
    }

    @Override
    public List<RegistrationDataDTO> findAllAdmins() {
        ModelMapper modelMapperFindAllAdmins = new ModelMapper();
        return userRepository.findAllAdmins().stream()
                .map(m -> modelMapperFindAllAdmins.map(m, RegistrationDataDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationDataDTO> findAllUsers() {
        ModelMapper modelMapperFindAllUsers = new ModelMapper();
        return userRepository.findAllUsers().stream()
                .map(m -> modelMapperFindAllUsers.map(m, RegistrationDataDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdminOrUser(RegistrationDataDTO registrationData, Long id) {
        User user = userRepository.findById(id).get();
        log.debug("Usunięcie admina (użytkownika): {}", user);
        if (user != null) {
            userRepository.delete(user);
        }
        log.debug("Admin (Użytkownik) został usunięty: {}", user);
    }

    @Override
    public RegistrationDataDTO prepareUpdateForAdminDataAccount(Long id) {
        User admins = userRepository.findById(id).get();
        ModelMapper model = new ModelMapper();
        return model.map(admins, RegistrationDataDTO.class);
    }
}
