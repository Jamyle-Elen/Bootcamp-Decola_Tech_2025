package dio.dio_spring_security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration   // indica que é uma classe de configuração
@EnableWebSecurity   // Habilitando uma segurança web de forma manual
                  // OBS: Não utiliza mais o EnableGlobalMethodSecurity, pois ele foi removido, agora usamos o SecurityFilterChain
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  // Ele vai criar uma cadeia de usuarios em memoria
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user123")
                .roles("USERS")
                .and()
                .withUser("admin")
                .password("{noop}master123")
                .roles("MANAGERS");
    }
    
}
