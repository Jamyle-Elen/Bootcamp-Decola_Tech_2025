# Actuator

### Actuator traz recursos prontos para produção para a aplicação

#### Propriedades

management.endpoint.health.show-details=always  // Mostra todos os detalhes, sempre
management.endpoint.web.exposure.include=*

# Spring Boot Test

É uma ferramenta pra monitorar e gerenciar aplicações em tempo real (Spring Boot)

---

# HealthIndicator

1. Métricas personalizadas
`Então ele consegue definir verificações personalizadas de saúde para sua aplicação`

OBS: É possivel até mesmo ve de um site externo

## Health Check

Atraves dele é possivel monitorar o estado de uma aplicação e seus serviços
``status``, ``diskspace`` e ``ping``

### É recomendado utilizar logger ao invés de System.out.println()
Ex.:
    try {
        int resultado = 10 / 0;
    } catch (ArithmeticException e) {
        logger.severe("Erro de divisão por zero: " + e.getMessage());
    }

O "e" que utilizamos é uma variaveis, as vezes é legal utilizar nomes descritivos.


# Testes Unitários
Eles procuram verificar a exatidão do código, em sua menor fração.

## JUnit
É um framework que facilita o desenvolvimento e execução de testes unitários em código Java.

@SpringBootTest - deve ser utilizada nas classes de testes para que o Spring inicialize o server e disponibilize os Beans da aplicação.


# MockMvc

## MockMvcRequestBuilders
Construir nossa requisição, nosso corpo, o que vamos mandar no header

## MockMvcResultMatchers
Confirmar se o resultado bateu com o esperado