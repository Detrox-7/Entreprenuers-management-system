package college.ems.dao;

import college.ems.util.DBUtil;
import java.sql.*;
import java.util.*;

public class DashboardDAO {
    public Map<String,Integer> summary()throws SQLException{
        Map<String,Integer> result=new LinkedHashMap<>();
        try(Connection c=DBUtil.getConnection();Statement s=c.createStatement()){
            result.put("entrepreneurs",count(s,"SELECT COUNT(*) FROM entrepreneurs"));
            result.put("businesses",count(s,"SELECT COUNT(*) FROM businesses WHERE status='Active'"));
            result.put("startups",count(s,"SELECT COUNT(*) FROM businesses WHERE LOWER(business_type)='startup'"));
            result.put("pending",count(s,"SELECT COUNT(*) FROM entrepreneurs WHERE status='Pending'"));
            result.put("employees",count(s,"SELECT COUNT(*) FROM employees"));result.put("investments",count(s,"SELECT COUNT(*) FROM investments"));
        }return result;
    }
    private int count(Statement s,String sql)throws SQLException{try(ResultSet r=s.executeQuery(sql)){r.next();return r.getInt(1);}}
}
