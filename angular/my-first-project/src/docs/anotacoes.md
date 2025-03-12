## Angular

@Component -> Serve pra indicar que ali vai ser um componente.
standalone -> Um componente que pode ou não funcionar dentro de um módulo.
template -> É a parte do corpo onde vai ter a estrutura (html)


## commands

`aliases` - apelidos
- `g` subistitui o global
- `n`     ''     o new

## Criação de um novo projeto Angular
`ng new my-first-project`


### Config Files

`tsconfig` - typescript configuration, ele define como o ts tem que se comportar no projeto
`tsconfig app` - Configuração na hora da publicação da aplicação
`tsconfig spec` - Quando for referente a testes
OBS.: Jasmine é o framework mais conhecido de testes do Angular

`extends` - Ele ta pegando os arquivos do tsconfig e adicionando nos outros dois

`editorconfig` - Configurar comportamento dos editores de código, mantendo a consistência dos estilos. Então ele padroniza os estilos.


### Files legacy

Arquivos de modulos: Os modulos nao sao mais obrigatórios, eles podem teer components chamados por  `standalone`


## Rodar um projeto angular
`ng serve` or `ng s`

`ng s -o` - Ele vai iniciar o server e assim que ele compilar vai abrir o projeto (É isso que quer dizer o '-o')

`npm run start`

## Build
`ng build` or `ng b`

### Minificação
É o nome que damos para quando complila todo o conteudo retirando os espaços, comentarios, linhas, etc.
