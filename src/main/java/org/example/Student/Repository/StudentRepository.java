package org.example.Student.Repository;

import org.example.Student.Model.Student;
import org.example.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private Connection connection;
    private String URL = "jdbc:mysql://localhost:3307/db_student";
    private String DB_USR_NAME = "root";
    private String DB_USER_PASSWORD = "1111";

    public StudentRepository() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, DB_USR_NAME, DB_USER_PASSWORD);
    }

    public List<Student> getAllStudent() throws Exception {

        List<Student> studentList = new ArrayList<>();
        String getAllUserQuery = "SELECT * from student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllUserQuery);
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt(1));
            student.setEmail(resultSet.getString(2));
            student.setPassword(resultSet.getString(3));
            student.setName(resultSet.getString(4));
            student.setUniversity(resultSet.getString(5));
            studentList.add(student);
        }
        return studentList;
    }

    public Student createStudent(Student student) throws Exception {
        String createStudentQuery = "insert into student(email,password,name,university) values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(createStudentQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, student.getEmail());
        preparedStatement.setString(2, student.getPassword());
        preparedStatement.setString(3, student.getName());
        preparedStatement.setString(4, student.getUniversity());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
        return student;
    }
}
