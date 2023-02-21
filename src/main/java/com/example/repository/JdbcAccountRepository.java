package com.example.repository;

import com.example.db.DatabaseConnectionFactory;
import com.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class JdbcAccountRepository implements AccountRepository{

    @Autowired
    TodoRepository todoRepository;


    static Logger logger= Logger.getLogger("TodoController");


    @Override
    public void saveAccount(Account account) {
        Connection connection= null;

        try{
            connection = DatabaseConnectionFactory.getConnection();
            String sql = "insert into accounts (First_Name, Last_Name, Email_Address, Password) values (?,?,?,?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,account.getFirstName());
            ps.setString(2, account.getLastName());
            ps.setString(3, account.getEmail());
            ps.setString(4,account.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Account findMyAccountByEmail(String email) {
        Connection connection =null;

        Account account = new Account();

        try{
            connection = DatabaseConnectionFactory.getConnection();
            String sql= "select * from accounts where Email_Address=?";
            PreparedStatement ps =connection.prepareStatement(sql);
            ps.setString(1,account.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                account.setId(rs.getInt("Account_Id"));
                account.setFirstName(rs.getString("First_Name"));
                account.setLastName(rs.getString("Last_Name"));
                account.setEmail(rs.getString("Email_Address"));
                account.setPassword(rs.getString("Password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(connection!=null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return account;
    }

    @Override
    public Account findMyAccount(int accountId) {
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        Connection connection = null;
        try {
            connection = DatabaseConnectionFactory.getConnection();
            String sql = "update accounts set First_Name=?, Last_Name=?, Email_Address=?, Password=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getFirstName());
            ps.setString(2, account.getLastName());
            ps.setString(3, account.getEmail());
            ps.setString(4,account.getPassword());
            ps.setInt(5, account.getId());
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
    public void deleteAccount(int accountId) {
        Connection connection =null;

        try{
            connection = DatabaseConnectionFactory.getConnection();
            String sql = "delete from accounts where Account_Id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.executeQuery();
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
    public List<Account> getAllAccount() {



        logger.info("invoke get all account");

        List<Account> accList = new ArrayList<>();
        Connection connection =null;
        try{
            connection =DatabaseConnectionFactory.getConnection();
            String sql = "select * from accounts";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Account account =new Account();
                account.setId(rs.getInt("id"));
                account.setFirstName(rs.getString("First_Name"));
                account.setLastName(rs.getString("Last_Name"));
                account.setEmail(rs.getString("Email_Address"));
                account.setPassword(rs.getString("Password"));

                account.setTodoList(todoRepository.findAll(account.getId()));

                accList.add(account);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accList;
    }
}
