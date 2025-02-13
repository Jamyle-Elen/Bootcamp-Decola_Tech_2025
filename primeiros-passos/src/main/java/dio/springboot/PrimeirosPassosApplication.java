package dio.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimeirosPassosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeirosPassosApplication.class, args);

		Calculadora calculadora = new Calculadora();
		System.out.println("o resultado é " + calculadora.somar(2,7));
	}

}
