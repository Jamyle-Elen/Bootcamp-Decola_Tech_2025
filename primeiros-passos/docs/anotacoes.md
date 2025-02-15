## Aplication.properties
- Ao invés de utilizar declarações eu atribuo a @Value
``OBS: O @Value da pra fazer tipo um mapeamento e deixar um valor como default``

_@Value("{name:nome-padrao}")_
O spring por padrão vai tentar procurar "name" se não encontrar vai usar o valor definido
por padrão


### Conceito de Beans
- Beans é um componente que pode ser injetado em outras partes da aplicação


### ORM e JPA

Object-Relational Mapping, (Sequelize,por exemplo)

JPA é o próprio Hibernate, JPA é uma especificação baseada em iterfaces


### Mapeamento
- Identificação
- Definições = DDL como as tabelas serão criadas, constituidas dentro do banco (campos obrigatorios, limitar numeros de caracteres, etc)
- Relacionamento = Relacionamento entre tabelas
- Herança = O paradigma da OO com ORM consegue prover mecanismos de herança dentro da estrutura
- Persistência = Alguns atributos/campos naturalmente serão atribuidos esses valores ao banco de dados

Na hora de gerar o valor aleatorio na PK usamos o ``GeneratedValue`` (ele é parecido com o AUTO_INCREMENT do mysql)

@GeneratedValue(strategy=GenerationType=AUTO)       // O Hibernate escolhe qual
@GeneratedValue(strategy=GenerationType=IDENTiFY)   // Bom pra usar com o MySQL, SQL Server

``OBS: Eles têm a opção SEQUENCE (Oracle, PostgreSQL) e TABLE também (vi que era bom evitar esse por ter uma baixa performace)``

### Anotação Column

- Quando usar?
    Quando eu quiser mudar um nome da tabela ou por restrições nos valores dos atributos (limitar o tamanho, se é único, etc)


## JPA Data

- Interfaces
    CrudRepository                  // Pesquisar sobre esses três
    JPARepository
    PagingAndSortingRepository
