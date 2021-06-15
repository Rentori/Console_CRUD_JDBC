package repository.io;

import model.Post;
import model.Writer;
import repository.WriterRepository;
import utilities.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriterRepositoryImpl implements WriterRepository {
    private static WriterRepository instance;
    PostRepositoryImpl postRepository = PostRepositoryImpl.getInstance();
    ConnectionService connectionService = new ConnectionService();
    ResultSet resultSet = null;
    Connection connection = null;

    private WriterRepositoryImpl(){
    }

    public static WriterRepository getInstance(){
        if (instance == null) {
            instance = new WriterRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Writer save(Writer type) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Writers (FirstName, LastName) value (?, ?)");
            preparedStatement.setString(1, type.getFirstName());
            preparedStatement.setString(2, type.getLastName());
            preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public Writer update(Writer type) {
        Connection connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE Writers  \n" +
                            " SET FirstName = ?, LastName = ? WHERE ID = ?");
            statement.setString(1, type.getFirstName());
            statement.setString(2, type.getLastName());
            statement.setLong(3, type.getId());
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public Writer getById(Long aLong) {
        Writer writer = null;
        List<Post> postList = null;
        connection = connectionService.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM Writers WHERE ID = ?");
            preparedStatement.setLong(1, aLong);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Long id = resultSet.getLong("ID");
            postList = postRepository.returnPostsByWriterId(id);
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            writer = new Writer(id ,firstName, lastName, postList);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer;
    }

    @Override
    public void deleteById(Long aLong) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM Writers WHERE ID = ?");
            statement.setLong(1, aLong);
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Writer> getAll() {
        connection = connectionService.getConnection();
        List<Writer> writerList = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Writers");
            writerList = getWritersFromSql(resultSet);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writerList;
    }

    private List<Writer> getWritersFromSql(ResultSet resultSet) throws SQLException {
        List<Writer> writerList = new ArrayList<>();
        List<Post> postList = null;

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            postList = postRepository.returnPostsByWriterId(id);

            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            writerList.add(new Writer(id, firstName, lastName, postList));

        }
        return writerList;
    }
}
