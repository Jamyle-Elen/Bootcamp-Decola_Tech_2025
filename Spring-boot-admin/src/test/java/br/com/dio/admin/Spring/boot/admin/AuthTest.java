package br.com.dio.admin.Spring.boot.admin;

import java.net.URI;

import javax.print.attribute.standard.Media;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc // Pra ele saber que la detro vai ter que injetar mais dependencias para funcionar
public class AuthTest {
    
    @Autowired  //Injetar dependência
    private MockMvc mockMvc;

    @Test
    public void deveRetornarErroComCredenciaisValidas() throws Exception {
        URI uri = new URI("http://localhost:8080");

        String content = "{\"username\": \"karantes\", \"senha\": \"123456\"}";

        mockMvc.perform(MockMvcRequestBuilder
        .post(uri)
        .content(content))   // executar a requisição
        .contentType(MediaType.APPLICATION_JSON) // definir o tipo de conteudo
        .andExpect(MockMvcResultMatchers
        .status()
        .is(200));
    }

    @Test
    public void deveRetornarErroComCredenciaisInvalidas() throws Exception {
        URI uri = new URI("http://localhost:8080");

        String content = "{\"username\": \"karantes\", \"senha\": \"123456\"}";

        mockMvc.perform(MockMvcRequestBuilder
        .post(uri)
        .content(content))   // executar a requisição
        .contentType(MediaType.APPLICATION_JSON) // definir o tipo de conteudo
        .andExpect(MockMvcResultMatchers
        .status()
        .is(401));
    }

}
