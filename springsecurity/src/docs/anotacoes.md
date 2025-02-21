## Spring Security

É uma lib que fornece proteção, autenticação, autorização e armazenamento de senhas

``Autenticação`` OAuth2, SAML2

``Autorização`` o Spring Security se baseia nas Autorities do usuário que se autentica na aplicação

### Basic Authentication

O cliente vai enviar a resquiisção http e vai por um header de autenticação (codificado em base 64)

### JWT (JSON Web Token)

Método para realizar autenticação entre duas partes por meio de um token

### OAuth 2.0

Protocolo que permite aos usuários ter acesso limitado a recursos de um website sem precisas expor suas credenciais
Ex.: Entrar com o google, facebook, etc.


## Config

@EnableWebSecurity       // Anotação para habilitar a segurança
weSecurityConfigurerAdapter  // É um adaptador de segurança para a aplicação


csrf (Cross-Site Request Forgery) é um tipo de ataque que quando o usuário mantem a sessão ativa

http.csrf().disable()   // Desativa o csrf
.authorizeRequests().anyRequest().authenticated()   // QUalquer requisição tem que ter autenticação


{noop}  // É pra dizer que não quer criptografar a senha
.password("{noop}123456")

---

# Actuator

### Actuator traz recursos prontos para produção para a aplicação

#### Propriedades

management.endpoint.health.show-details=always  // Mostra todos os detalhes, sempre
management.endpoint.web.exposure.include=*
