package pl.lg.charity.heroku;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.lg.charity.domain.entities.Role;
import pl.lg.charity.domain.repositories.RoleRepository;
import pl.lg.charity.domain.repositories.UserRepository;

@Component
@Profile("heroku") @Slf4j
@RequiredArgsConstructor
public class StarterDataCreator implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override @Transactional
    public void run(ApplicationArguments args) throws Exception {
        createRoles();
    }

    private void createRoles(){
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        userRepository.createUser();
        userRepository.makeUserUser();
        userRepository.createAdmin();
        userRepository.makeAdminAdmin();
    }
}
