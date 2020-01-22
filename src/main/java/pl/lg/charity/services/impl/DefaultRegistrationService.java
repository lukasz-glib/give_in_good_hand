package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.entities.Donation;
import pl.lg.charity.domain.entities.Role;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.entities.VerificationToken;
import pl.lg.charity.domain.repositories.DonationRepository;
import pl.lg.charity.domain.repositories.RoleRepository;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.domain.repositories.VerificationTokenRepository;
import pl.lg.charity.dtos.DeleteAdminValidationDataDTO;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.services.EmailService;
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
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final DonationRepository donationRepository;

    public DefaultRegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                                      RoleRepository roleRepository, EmailService emailService,
                                      VerificationTokenRepository verificationTokenRepository,
                                      DonationRepository donationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.donationRepository = donationRepository;
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
        VerificationToken verificationToken = new VerificationToken(user);
        emailService.sendSimpleMessage(user.getEmail(), "give_in_good_hand app: Please complete your registration!",
                "To activate your account, please click link: " +
                        "http://localhost:8080/register/confirmation?token="+verificationToken.getToken());
        log.debug("Rejestracja nowego użytkownika: {}", user);
        userRepository.save(user);
        log.debug("Nowy użytkownik został zarejestrowany: {}", user);
        verificationTokenRepository.save(verificationToken);
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
    public DeleteAdminValidationDataDTO findAdminToDeleteById(Long id) {
        ModelMapper modelMapperFindAdminToDelete = new ModelMapper();
        return modelMapperFindAdminToDelete.map(userRepository.getOne(id), DeleteAdminValidationDataDTO.class);
    }

    @Override
    public void deleteAdminOrUser(RegistrationDataDTO registrationData, Long id) {
        User user = userRepository.findById(id).get();
        VerificationToken verificationToken = verificationTokenRepository.findByUserId(id);
        List<Donation> userDonations = donationRepository.findAllByUserEmail(user.getEmail());
        log.debug("Usunięcie tokenu (użytkownika): {}", verificationToken);
        if (verificationToken != null) {
            verificationTokenRepository.delete(verificationToken);
        }
        log.debug("Token użytkownika został usunięty: {}", verificationToken);
        log.debug("Usunięcie przekazanych darów użytkownika: {}", userDonations);
        if (userDonations != null) {
            donationRepository.deleteAll(userDonations);
        }
        log.debug("Dary użytkownika zostały usunięte: {}", userDonations);
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

    @Override
    public RegistrationDataDTO prepareUpdateForUserDataAccount(Long id) {
        User users = userRepository.findById(id).get();
        ModelMapper model = new ModelMapper();
        return model.map(users, RegistrationDataDTO.class);
    }

    @Override
    public void unlockUser(Long id) {
        userRepository.statusActiveUser(id);
    }

    @Override
    public void lockUser(Long id) {
        userRepository.statusBlockedUser(id);
    }

    @Override
    public void makeUsersStatusActive(Long id) {
        User user = userRepository.getOne(id);
        user.setActive(Boolean.TRUE);
        userRepository.save(user);
    }
}
