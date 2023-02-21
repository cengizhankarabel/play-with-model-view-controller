package com.example.repository;

import com.example.db.DatabaseConnectionFactory;
import com.example.model.Todo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTodoRepository implements TodoRepository{
    @Override
    public void save(Todo todo) {
        Connection connection = null;
        try{
            connection= DatabaseConnectionFactory.getConnection();
            String sql = "insert into todos(Title, Completed, account_id values (?,?,?)";
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
    public List<Todo> findAll(int accountId) {
        Connection connection =null;

        List<Todo> todos =new ArrayList<>();

        try{
            connection=DatabaseConnectionFactory.getConnection();
            String sql="select * from todos where account_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,accountId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Todo todo = new Todo();
                todo.setId(rs.getInt("Id"));
                todo.setTitle(rs.getString("Title"));
                todo.setCompleted(rs.getBoolean("Completed"));
                todos.add(todo);
            }

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
        return todos;
    }
}
