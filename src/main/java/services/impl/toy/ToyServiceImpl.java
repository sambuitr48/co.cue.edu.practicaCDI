package services.impl.toy;


import annotations.Mysqlconn;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapping.dto.ClientDTO;
import mapping.dto.ToyDTO;
import mapping.mappers.ClientMapper;
import mapping.mappers.ToyMapper;
import models.*;
import repository.Repository;
import repository.impl.client.ClientRepositoryJDBCImpl;
import repository.impl.toy.ToyRepositoryJDBCImpl;
import services.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@ApplicationScoped
public class ToyServiceImpl implements Service {
    @Inject
    private Repository<Toy> toyRepository;
    private Repository<Client> clientRepository;
    public ToyServiceImpl() {
        this.toyRepository = new ToyRepositoryJDBCImpl();
        this.clientRepository = new ClientRepositoryJDBCImpl();
    }


    @Override
    public void addToy(ToyDTO toyDTO) {
        toyRepository.save(ToyMapper.mapFromDto(toyDTO));
    }

    @Override
    public ToyDTO search(Integer id) throws SQLException {
        return ToyMapper.mapFromModel(toyRepository.byId(id));
    }


    @Override
    public void addClient(ClientDTO clientDTO) {
        clientRepository.save(ClientMapper.mapFromDTO(clientDTO));
    }

    @Override
    public List<ClientDTO> listClients() {
        return clientRepository.list()
                .stream()
                .map(ClientMapper::mapFromModel)
                .toList();
    }
    @Override
    public List<ToyDTO> listToys() {
        return toyRepository.list()
                .stream()
                .map(ToyMapper::mapFromModel)
                .toList();
    }

    @Override
    public ToyDTO searchByName(String name) throws SQLException {
        return ToyMapper.mapFromModel(toyRepository.byName(name));
    }
}