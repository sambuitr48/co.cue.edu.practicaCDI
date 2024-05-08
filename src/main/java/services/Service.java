package services;

import mapping.dto.ClientDTO;
import mapping.dto.ToyDTO;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    void addToy(ToyDTO toyDTO);
    ToyDTO search(Integer id) throws SQLException;
    void addClient(ClientDTO clientDTO);
    List<ClientDTO> listClients();
    List<ToyDTO> listToys();
    ToyDTO searchByName(String name) throws SQLException;
}
