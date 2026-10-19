package college.ems.dao;

import college.ems.model.User;
import college.ems.util.DBUtil;
import java.sql.*;

public class UserDAO {
    public User findByUsername(String username)throws SQLException{
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT user_id,username,password_hash,role FROM users WHERE username=?")){
            p.setString(1,username);try(ResultSet r=p.executeQuery()){if(!r.next())return null;User u=new User();u.setUserId(r.getInt("user_id"));u.setUsername(r.getString("username"));u.setPasswordHash(r.getString("password_hash"));u.setRole(r.getString("role"));return u;}
        }
    }
}
