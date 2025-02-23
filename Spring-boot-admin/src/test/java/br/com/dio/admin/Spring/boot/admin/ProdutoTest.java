package br.com.dio.admin.Spring.boot.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

// @SpringBootTest
public class ProdutoTest {

    @Autowired
    private ProdutoService produtoService;
    
    @Test
    public void verificarValorNegativoNoProduto() {
        Produto produto = new Produto();
        produto.setNome("Teste");
        produto.setPreco(-10.0);

        produtoService.save(produto);

        assertNull(produto.getId());     // Verifica se é null
        assertEquals(produto.getId(), 1); // Verifica se é 1
    }

    // @Test
    // public void testeFalso() {
    //     assertTrue(true);
    // }
}
