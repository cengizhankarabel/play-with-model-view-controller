package com.example.repository;

import com.example.db.DatabaseConnectionFactory;
import com.example.model.Todo;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcTodoRepository implements TodoRepository{
    @Override
    public void save(Todo todo) {
        Connection connection = null;
        try{
            connection= DatabaseConnectionFactory.getConnection();
            String sql = "insert into todos(Title, Completed, user_id values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,todo.getTitle());
            ps.setBoolean(2,todo.isCompleted());
            ps.setInt(3,todo.getAccount().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(int id, String title) {

    }

    @Override
    public void update(int id, boolean completed) {

    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        try{
            connection=DatabaseConnectionFactory.getConnection();
            String sql ="delete from todos where id=?";
            PreparedStatement ps =connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Todo> findAll(int userId) {
        return null;
    }
}
