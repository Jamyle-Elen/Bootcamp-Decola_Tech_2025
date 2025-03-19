package br.com.dio.barber_shop_ui_api.controller;

import br.com.dio.barber_shop_ui_api.controller.request.SaveClientRequest;
import br.com.dio.barber_shop_ui_api.controller.request.UpdateClientRequest;
import br.com.dio.barber_shop_ui_api.controller.response.ClientDetailResponse;
// import br.com.dio.barber_shop_ui_api.controller.response.ListClientResponse;
import br.com.dio.barber_shop_ui_api.controller.response.SaveClientResponse;
import br.com.dio.barber_shop_ui_api.controller.response.UpdateClientResponse;
import br.com.dio.barber_shop_ui_api.entity.ClientEntity;
import br.com.dio.barber_shop_ui_api.mapper.IClientMapper;
import br.com.dio.barber_shop_ui_api.service.IClientService;
import br.com.dio.barber_shop_ui_api.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @PostMapping
    public SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request) {
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    public UpdateClientResponse update(@PathVariable final String id, @RequestBody @Valid final UpdateClientRequest request) {
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final String id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ClientDetailResponse findById(@PathVariable final String id) {
        try {
            var entity = queryService.findById(id);
            return mapper.toDetailResponse(entity);
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente por ID: " + e.getMessage());
            throw new RuntimeException("Cliente não encontrado.");
        }
    }

    @GetMapping // ("/clients")  nn vai precisar por conta do RequestMapping
    public Iterable<ClientEntity> listar() {
        try {
            return service.listar();
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            throw new RuntimeException("Erro ao listar clientes.");
        }
    }
}
