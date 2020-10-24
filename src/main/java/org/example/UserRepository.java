package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private Connection connection;
    private String URL = "jdbc:mysql://localhost:3307/user_database";
    private String DB_USR_NAME = "root";
    private String DB_USER_PASSWORD = "1111";


    public UserRepository() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, DB_USR_NAME, DB_USER_PASSWORD);
    }

    public List<User> getAllUser() throws Exception {

        List<User> userList = new ArrayList<>();
        String getAllUserQuery = "SELECT * from all_user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllUserQuery);
        while (resultSet.next()) {
            User user = new User();
            user.setuId(resultSet.getInt(1));
            user.setUserName(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            userList.add(user);
        }
        return userList;
    }

    public User getUser(int uId) throws Exception {
        String getUserQuery = "select * from all_user where uId=" + uId;
        User user = new User();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getUserQuery);
        if (resultSet.next()) {
            user.setuId(resultSet.getInt(1));
            user.setUserName(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
        }
        return user;
    }

    public User createUser(User user) throws Exception {
        String createUserQuery = "insert into all_user values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(createUserQuery);
        preparedStatement.setInt(1, user.getuId());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.executeUpdate();
        return user;
    }

    public User updateUser(User user) throws Exception {
        String updateUserQuery = "update all_user set uId=?,uName=?,uPassword=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateUserQuery);
        preparedStatement.setInt(1, user.getuId());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        return user;
    }

    public void deleteUser(int uId) throws Exception {
        String deleteUserQuery = "delete from all_user where uId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserQuery);
        preparedStatement.setInt(1, uId);
        preparedStatement.executeUpdate();
    }
}
