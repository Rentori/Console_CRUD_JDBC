package repository.io;

import model.Label;
import repository.LabelRepository;
import utilities.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabelRepositoryImpl implements LabelRepository {
    private static LabelRepositoryImpl instance;
    private ResultSet resultSet = null;
    private final ConnectionService connectionService = new ConnectionService();
    private Connection connection = null;
    private Long id = null;
    private Long postId = null;
    private String name = null;


    private LabelRepositoryImpl() {
    }

    public static LabelRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new LabelRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Label save(Label type) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO Labels (Name, PostId) value (?, ?)");
            statement.setString(1, type.getName());
            statement.setLong(2, type.getPostId());
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public Label update(Label type) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE Labels SET Name = ? WHERE ID = ?");
            statement.setString(1, type.getName());
            statement.setLong(2, type.getId());
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public Label getById(Long aLong) {
        Label label = null;
        connection = connectionService.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM Labels WHERE ID = ?");
            preparedStatement.setLong(1, aLong);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong("ID");
            name = resultSet.getString("Name");
            postId = resultSet.getLong("PostId");
            label = new Label(id, name, postId);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    @Override
    public void deleteById(Long aLong) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM Labels WHERE ID = ?");
            statement.setLong(1, aLong);
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Label> getAll() {
        List<Label> labelList = null;
        connection = connectionService.getConnection();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Labels");
            labelList = getLabelListFromSQL(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return labelList;
    }

    private List<Label> getLabelListFromSQL(ResultSet resultSet) throws SQLException {
        List<Label> labelList = new ArrayList<>();

        while (resultSet.next()) {
            id = resultSet.getLong("ID");
            name = resultSet.getString("Name");
            postId = resultSet.getLong("PostId");
            labelList.add(new Label(id, name, postId));
        }
        return labelList;
    }

    public List<Label> returnLabelsByPostId(Long id) throws SQLException {
        connection = connectionService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from labels\n" +
                "where PostId = ?");
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();


        return getLabelListFromSQL(resultSet);
    }
}
