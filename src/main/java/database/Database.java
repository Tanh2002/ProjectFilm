package database;

import entities.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.ERoleUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.IRoleUserRepository;

@Getter
@Setter
@AllArgsConstructor
@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(IRoleUserRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Khởi tạo các giá trị ở đây
                RoleUser role1 = new RoleUser();
                role1.setId(1);
                role1.setName(ERoleUser.ROLE_ADMIN);

                RoleUser role2 = new RoleUser();
                role2.setId(2);
                role2.setName(ERoleUser.ROLE_MANAGER);

                RoleUser role3 = new RoleUser();
                role3.setId(3);
                role3.setName(ERoleUser.ROLE_RECEPTIONIST);

                RoleUser role4 = new RoleUser();
                role4.setId(4);
                role4.setName(ERoleUser.ROLE_SALE);

                RoleUser role5 = new RoleUser();
                role5.setId(5);
                role5.setName(ERoleUser.ROLE_CLIENT);

                if (repository.findAll().size() == 0) {
                    repository.save(role1);
                    repository.save(role2);
                    repository.save(role3);
                    repository.save(role4);
                    repository.save(role5);
                }
            }
        };
    }
}
