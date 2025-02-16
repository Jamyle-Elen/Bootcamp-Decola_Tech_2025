package dio.aula;

import dio.aula.model.User;
import dio.aula.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartApp implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("ELEN");
        user.setUsername("elen");
        user.setPassword("dio123");

        repository.save(user);

        for (User u: repository.findAll()) { // findAll para buscar todos os users
            System.out.println(u);           // para printar cada um que for encontrado ATALHO: sou
        }
    }
}