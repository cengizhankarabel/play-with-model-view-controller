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


    static Logger logger= Logger.getLogger("JdbcAccountRepository");


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

        logger.info("invoked findMyAccountByEmail methods");
        Connection connection =null;

        Account account = new Account();
        logger.info("temp account created");

        try{
            connection = DatabaseConnectionFactory.getConnection();
            String sql= "select * from accounts where Email_Address=?";
            PreparedStatement ps =connection.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                account.setId(rs.getInt("id"));
                account.setFirstName(rs.getString("First_Name"));
                account.setLastName(rs.getString("Last_Name"));
                account.setEmail(rs.getString("Email_Address"));
                account.setPassword(rs.getString("Password"));
                logger.info("temp account initialized"+account.getEmail());
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
        logger.info("account returning "+account.getFirstName());
        return account;
    }

    @Override
    public Account findMyAccount(int accountId) {

        Account account = new Account();
        Connection connection=null;
        try{
            connection=DatabaseConnectionFactory.getConnection();
            String sql= "select * from accounts where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                logger.info("findMyAccount methods invoked-> ");
                account.setId(rs.getInt("id"));
                account.setFirstName(rs.getString("First_Name"));
                account.setLastName(rs.getString("Last_Name"));
                account.setEmail(rs.getString("Email_Address"));
                account.setPassword(rs.getString("Password"));
                logger.info("findMyAccount methods initialized-> "+account.getFirstName());
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
        logger.info("returning account "+account.getFirstName());
        return account;

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
