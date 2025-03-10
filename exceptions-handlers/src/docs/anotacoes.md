## Exceptions Handlers

``@ControllerAdvide`` lida com exceções globalmente, ele vai pegar qualquer tipo um try catch gigante.

``@ExceptionHandler`` lida com exceções especificas, pontos especificos de cada erro

throw new ProductNullException();
Podemos criar uma class


Exemplo de erro no Controller

@ExceptionHandler(ProductNullException.class) // A class criada
public ResponseEndity<Object> capturaErroNull() {   // retorna uma mensagem personalizada
    Map<String, Object> body = new HashMap<String, Object>();   // Estrutura pra armazenar os dados da resposta
    body.put("message", "Verifique os campos do produto");      // Define a "mensagem" que vai retornar p cliente

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);   // return padrão pegando o status e retornando o corpo

    // podeemos retornar apenas o numero do erro usando o "valueof"
    return ResponseEntity.status(HttpStatus.valueOf(400)).body(body);

    ou simplificando mais ainda
    return ResponseEntity.status(400).body(body);

}