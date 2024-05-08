package services.impl.client;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dto.ClientDTO;
import repository.Repository;
import services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Inject
    @Named("Client")
    private Repository<ClientDTO> repo;
    @Override
    public List<ClientDTO> list() {
        return repo.list();
    }

    @Override
    public ClientDTO byId(int id) {
        return repo.byId(id);
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
