package repository.io;

import model.Label;
import model.Post;
import model.PostStatus;
import repository.PostRepository;
import utilities.ConnectionService;
import utilities.DateFormatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    private static PostRepositoryImpl instance;
    private final ConnectionService connectionService = new ConnectionService();
    private final LabelRepositoryImpl labelRepository = LabelRepositoryImpl.getInstance();

    private ResultSet resultSet = null;
    private Connection connection = null;
    private final Long id = null;
    private final String name = null;

    private PostRepositoryImpl() {
    }

    public static PostRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PostRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Post save(Post type) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO Posts (Content, Created, Updated, PostStatus, WriterId)\n" +
                            "value (?, ?, ?, ?, ?) ");
            statement.setString(1, type.getContent());
            statement.setString(2, DateFormatter.getCurrentDate());
            statement.setString(3, DateFormatter.getCurrentDate());
            statement.setString(4, PostStatus.ACTIVE.toString());
            statement.setLong(5, type.getWriterId());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public Post update(Post type) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE Posts  \n" +
                            " SET Content = ?, Updated = ?, PostStatus = ? WHERE ID = ?");
            statement.setString(1, type.getContent());
            statement.setString(2, DateFormatter.getCurrentDate());
            statement.setString(3, PostStatus.UNDER_REVIEW.toString());
            statement.setLong(4, type.getId());
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public Post getById(Long aLong) {
        Post post = null;
        List<Label> labels = null;
        connection = connectionService.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM Posts WHERE ID = ?");
            preparedStatement.setLong(1, aLong);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Long id = resultSet.getLong("ID");
            labels = labelRepository.returnLabelsByPostId(id);
            String content = resultSet.getString("Content");
            String created = resultSet.getString("Created");
            String updated = resultSet.getString("Updated");
            String postStatus = resultSet.getString("PostStatus");
            Long writerId = resultSet.getLong("WriterId");
            post = new Post(id, content, created, updated, PostStatus.valueOf(postStatus), writerId, labels);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Long aLong) {
        connection = connectionService.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE Posts SET PostStatus = ? WHERE ID = ?");
            statement.setString(1, PostStatus.DELETED.toString());
            statement.setLong(2, aLong);
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        connection = connectionService.getConnection();
        List<Post> postList = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Posts");
            postList = getPostListFromSql(resultSet);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return postList;
    }

    private List<Post> getPostListFromSql(ResultSet resultSet) throws SQLException {
        List<Post> postList = new ArrayList<>();
        List<Label> labelList = null;

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            labelList = labelRepository.returnLabelsByPostId(id);
            System.out.println(id);
            String content = resultSet.getString("Content");
            String created = resultSet.getString("Created");
            String updated = resultSet.getString("Updated");
            String postStatus = resultSet.getString("PostStatus");
            Long writerId = resultSet.getLong("WriterId");

            postList.add(new Post(id, content, created, updated, PostStatus.valueOf(postStatus), writerId, labelList));
        }

        return postList;
    }

    public List<Post> returnPostsByWriterId(Long id) throws SQLException {
        connection = connectionService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from posts\n" +
                "where WriterId = ?");
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();


        return getPostListFromSql(resultSet);
    }
}
