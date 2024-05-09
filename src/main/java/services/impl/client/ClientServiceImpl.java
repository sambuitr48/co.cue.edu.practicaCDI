package services.impl.client;

import annotations.Mysqlconn;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dto.ClientDTO;
import mapping.mappers.ClientMapper;
import models.Client;
import repository.Repository;
import services.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClientServiceImpl implements ClientService {
    @Inject
    private Repository<Client> repo;
    @Override
    public List<ClientDTO> list() {
        return repo.list().stream().map(ClientMapper::mapFromModel).collect(Collectors.toList());
    }

    @Override
    public ClientDTO byId(int id) {
        return ClientMapper.mapFromModel(repo.byId(id));
    }

    @Override
    public void update(ClientDTO t) {
        //No me sirve
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
