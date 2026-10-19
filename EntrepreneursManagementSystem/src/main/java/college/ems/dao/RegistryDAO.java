package college.ems.dao;

import college.ems.util.DBUtil;
import java.sql.*;
import java.util.*;

/** CRUD for the three child tables. Table/column names come only from this fixed allow-list. */
public class RegistryDAO {
    public List<Map<String,Object>> list(String kind)throws SQLException{
        String sql=switch(kind){
            case "businesses" -> "SELECT b.business_id AS id,b.business_name AS name,b.business_type AS type,b.industry,b.location,b.status,e.full_name AS parent FROM businesses b JOIN entrepreneurs e ON e.entrepreneur_id=b.entrepreneur_id ORDER BY b.created_at DESC";
            case "employees" -> "SELECT x.employee_id AS id,x.employee_name AS name,x.email,x.phone,x.designation AS type,x.joining_date AS date,b.business_name AS parent FROM employees x JOIN businesses b ON b.business_id=x.business_id ORDER BY x.employee_id DESC";
            case "investments" -> "SELECT i.investment_id AS id,i.investor_name AS name,i.investment_amount AS amount,i.investment_type AS type,i.investment_date AS date,b.business_name AS parent FROM investments i JOIN businesses b ON b.business_id=i.business_id ORDER BY i.investment_id DESC";
            default -> throw new IllegalArgumentException("Unknown section");
        };
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){
            List<Map<String,Object>> rows=new ArrayList<>();ResultSetMetaData md=r.getMetaData();while(r.next()){Map<String,Object> row=new LinkedHashMap<>();for(int i=1;i<=md.getColumnCount();i++)row.put(md.getColumnLabel(i),r.getObject(i));rows.add(row);}return rows;
        }
    }
    public Map<String,Object> find(String kind,int id)throws SQLException{
        String sql=switch(kind){case "businesses"->"SELECT business_id AS id,entrepreneur_id AS parentId,business_name AS name,business_type AS type,industry,description,founded_date AS date,location,status FROM businesses WHERE business_id=?";case "employees"->"SELECT employee_id AS id,business_id AS parentId,employee_name AS name,email,phone,designation AS type,joining_date AS date FROM employees WHERE employee_id=?";case "investments"->"SELECT investment_id AS id,business_id AS parentId,investor_name AS name,investment_amount AS amount,investment_date AS date,investment_type AS type FROM investments WHERE investment_id=?";default->throw new IllegalArgumentException("Unknown section");};
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,id);try(ResultSet r=p.executeQuery()){if(!r.next())return null;Map<String,Object> row=new LinkedHashMap<>();ResultSetMetaData md=r.getMetaData();for(int i=1;i<=md.getColumnCount();i++)row.put(md.getColumnLabel(i),r.getObject(i));return row;}}
    }
    public void save(String kind,Integer id,Map<String,String> v)throws SQLException{
        String sql=switch(kind){
            case "businesses" -> id==null?"INSERT INTO businesses(entrepreneur_id,business_name,business_type,industry,description,founded_date,location,status) VALUES(?,?,?,?,?,?,?,?)":"UPDATE businesses SET entrepreneur_id=?,business_name=?,business_type=?,industry=?,description=?,founded_date=?,location=?,status=? WHERE business_id=?";
            case "employees" -> id==null?"INSERT INTO employees(business_id,employee_name,email,phone,designation,joining_date) VALUES(?,?,?,?,?,?)":"UPDATE employees SET business_id=?,employee_name=?,email=?,phone=?,designation=?,joining_date=? WHERE employee_id=?";
            case "investments" -> id==null?"INSERT INTO investments(business_id,investor_name,investment_amount,investment_date,investment_type) VALUES(?,?,?,?,?)":"UPDATE investments SET business_id=?,investor_name=?,investment_amount=?,investment_date=?,investment_type=? WHERE investment_id=?";
            default -> throw new IllegalArgumentException("Unknown section");};
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            int i=1;
            if(kind.equals("businesses")){p.setInt(i++,integer(v,"parentId"));p.setString(i++,required(v,"name"));p.setString(i++,v.get("type"));p.setString(i++,v.get("industry"));p.setString(i++,v.get("description"));date(p,i++,v.get("date"));p.setString(i++,v.get("location"));p.setString(i++,v.get("status"));}
            else if(kind.equals("employees")){p.setInt(i++,integer(v,"parentId"));p.setString(i++,required(v,"name"));p.setString(i++,v.get("email"));p.setString(i++,v.get("phone"));p.setString(i++,v.get("type"));date(p,i++,v.get("date"));}
            else {p.setInt(i++,integer(v,"parentId"));p.setString(i++,required(v,"name"));p.setBigDecimal(i++,new java.math.BigDecimal(required(v,"amount")));date(p,i++,v.get("date"));p.setString(i++,v.get("type"));}
            if(id!=null)p.setInt(i,id);if(p.executeUpdate()==0&&id!=null)throw new SQLException("Record not found.");
        }
    }
    public void delete(String kind,int id)throws SQLException{String table=switch(kind){case "businesses"->"businesses";case "employees"->"employees";case "investments"->"investments";default->throw new IllegalArgumentException("Unknown section");};String key=switch(kind){case "businesses"->"business_id";case "employees"->"employee_id";default->"investment_id";};try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM "+table+" WHERE "+key+"=?")){p.setInt(1,id);if(p.executeUpdate()==0)throw new SQLException("Record not found.");}}
    public List<Map<String,Object>> parents(String kind)throws SQLException{String sql=kind.equals("businesses")?"SELECT entrepreneur_id id,full_name name FROM entrepreneurs ORDER BY full_name":"SELECT business_id id,business_name name FROM businesses ORDER BY business_name";try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){List<Map<String,Object>> rows=new ArrayList<>();while(r.next()){Map<String,Object> row=new HashMap<>();row.put("id",r.getInt("id"));row.put("name",r.getString("name"));rows.add(row);}return rows;}}
    private static int integer(Map<String,String> v,String k){return Integer.parseInt(required(v,k));}
    private static String required(Map<String,String> v,String k){String s=v.get(k);if(s==null||s.isBlank())throw new IllegalArgumentException("Please fill in all required fields.");return s.trim();}
    private static void date(PreparedStatement p,int i,String value)throws SQLException{if(value==null||value.isBlank())p.setNull(i,Types.DATE);else p.setDate(i,java.sql.Date.valueOf(value));}
}
