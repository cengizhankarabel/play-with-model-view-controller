package com.example.repository;

import com.example.db.DatabaseConnectionFactory;
import com.example.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcAccountRepository implements AccountRepository{
    @Override
    public void saveAccount(Account account) {
        Connection connection= null;

        try{
            connection = DatabaseConnectionFactory.getConnection();
            String sql = "insert into accounts (Account_Id, First_Name, Last_Name, Email_Address) values (id=?, firstName=?, lastName=?, email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,account.getId());
            ps.setString(2, account.getFirstName());
            ps.setString(3, account.getLastName());
            ps.setString(4,account.getEmail());
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
            String sql = "update accounts set firstName=?, lastName=?, email=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getFirstName());
            ps.setString(2, account.getLastName());
            ps.setString(3, account.getEmail());
            ps.setInt(4, account.getId());
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

        List<Account> accList =null;
        Connection connection =null;
        try{
            connection =DatabaseConnectionFactory.getConnection();
            String sql = "select * from accounts";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Account account =new Account(
                        rs.getInt("Account_Id"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Email_Address") );
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
