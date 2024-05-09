package repository.impl.toy;

import annotations.Mysqlconn;
import connection.DataBaseConnection;
import exceptions.ServiceJdbcException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.Toy;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static connection.DataBaseConnection.getConnection;
//Aquí van los métodos en donde se usa SQL
@ApplicationScoped
public class ToyRepositoryJDBCImpl implements Repository<Toy> {
    @Inject
    @Mysqlconn
    private Connection conn;

    public ToyRepositoryJDBCImpl() {
        this.conn = conn;
    }
    private Toy createToy(ResultSet resultSet) throws SQLException {
        Toy toy = new Toy();
        toy.setToy_id(resultSet.getInt("toy_id"));
        toy.setToy_name(resultSet.getNString("toy_name"));
        toy.setToy_category(resultSet.getString("toy_category"));
        toy.setToy_price(resultSet.getDouble("toy_price"));
        toy.setToy_stock(resultSet.getInt("toy_stock"));
        return toy;
    }

    @Override
    public List<Toy> list() {
        List<Toy> toyList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     """ 
                         SELECT * FROM toy
                         """
             )) {
            while (resultSet.next()){
                Toy toy = createToy(resultSet);
                toyList.add(toy);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("unable to list info");
        }
        return toyList;
    }
    @Override
    public Toy byId(int id) {
        Toy toy = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            SELECT * FROM toy 
                            WHERE toy_id = ?
                            """
                )
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                toy = createToy(resultSet);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("unable to find info");
        }
        return toy;
    }

    @Override
    public void save(Toy toy) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            INSERT INTO toy(toy_name,toy_category,toy_price,toy_stock) 
                            VALUES (?,?,?,?)
                            """
                )
        ) {
            preparedStatement.setString(1, toy.getToy_name());
            preparedStatement.setString(2, toy.getToy_category());
            preparedStatement.setDouble(3, toy.getToy_price());
            preparedStatement.setInt(4, toy.getToy_stock());
        } catch (SQLException e) {
            throw new ServiceJdbcException("unable to save info");
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            DELETE FROM toy
                            WHERE toy_id = ?
                            """
                )) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServiceJdbcException("unable to delete info");
        }
    }
    @Override
    public void update(Toy toy) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            UPDATE toy 
                            SET toy_name = ?,toy_category = ?,toy_price = ?,toy_stock = ? 
                            WHERE toy_id = ?
                            """
                )
        ) {
            preparedStatement.setString(1, toy.getToy_name());
            preparedStatement.setString(2, toy.getToy_category());
            preparedStatement.setDouble(3, toy.getToy_price());
            preparedStatement.setInt(4, toy.getToy_stock());
            preparedStatement.setInt(5,toy.getToy_id());

        } catch (SQLException e) {
            throw new ServiceJdbcException("unable to update info");
        }
    }
    public Toy byName(String name) {
        Toy toy = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            SELECT * FROM toy 
                            WHERE toy_name = ?
                            """
                )
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                toy = createToy(resultSet);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("unable to find info");
        }
        return toy;
    }
}
