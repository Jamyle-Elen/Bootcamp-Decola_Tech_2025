package dio.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApp implements CommandLineRunner {
    @Autowired
    // O Container quando ele for inicializado vai perceber que ja tem um component sem precisar usar o "new"
    private Calculadora calculadora;
    // OBS: nesse caso, não usamos o "new" para Components, agora para objetos ele se mantém
    @Override
    public void run(String... args) throws Exception {
        System.out.println("o resultado é " + calculadora.somar(2,7));
    }
}
