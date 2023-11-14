package com.ra.lecture.md04ss09.model.dao;

import com.ra.lecture.md04ss09.dto.ClassroomDTO;
import com.ra.lecture.md04ss09.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO implements IGenericDAO<ClassroomDTO, Integer> {
    @Override
    public List<ClassroomDTO> getAll() {
        Connection connection = ConnectionDB.openConnection();
        List<ClassroomDTO> classroomList = new ArrayList<>();
        try {
            String sql = "select class.id, class.name, count(students.id) 'quantity' from class left join students on class.id = students.class_id group by class.id order by class.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClassroomDTO classroom = new ClassroomDTO();
                classroom.setId(resultSet.getInt("id"));
                classroom.setName(resultSet.getString("name"));
                classroom.setQuantity(resultSet.getInt("quantity"));
                classroomList.add(classroom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return classroomList;
    }

    @Override
    public boolean save(ClassroomDTO classroom) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sql = "insert into class (name) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classroom.getName());
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public ClassroomDTO getById(Integer classId) {
        Connection connection = ConnectionDB.openConnection();
        ClassroomDTO classroom = new ClassroomDTO();
        try {
            String sql = "select * from class where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, classId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classroom.setId(resultSet.getInt("id"));
                classroom.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return classroom;
    }

    @Override
    public boolean update(ClassroomDTO classroomDTO, Integer classId) {
        Connection connection = ConnectionDB.openConnection();

        try {
            String sql = "update class set name = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classroomDTO.getName());
            preparedStatement.setInt(2, classId);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Integer classId) {
        Connection connection = ConnectionDB.openConnection();

        try {
            String sql = "delete from class  where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, classId);
            int check = preparedStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    public static boolean addClassTransaction() {
        Connection connection = ConnectionDB.openConnection();
        try {
            connection.setAutoCommit(false);
            String sql1 = "insert into class (name) values (?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, "Biology");
            int check1 = preparedStatement1.executeUpdate();

            String sql2 = "insert into class (name) value (?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            int check2 = preparedStatement2.executeUpdate();
            preparedStatement2.setString(1, "Philosophy");

            connection.commit();
            if (check1 > 0 && check2 > 0) {
                return true;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;

    }

    public static void main(String[] args) {
        addClassTransaction();
    }
}
