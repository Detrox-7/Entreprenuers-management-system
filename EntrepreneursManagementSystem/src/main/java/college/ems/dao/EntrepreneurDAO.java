package college.ems.dao;

import college.ems.model.Entrepreneur;
import college.ems.util.DBUtil;
import java.sql.*;
import java.util.*;

public class EntrepreneurDAO {
    private static final String COLUMNS="entrepreneur_id,full_name,email,phone,gender,date_of_birth,address,city,state,registration_date,status";
    public List<Entrepreneur> findAll(String search,String status) throws SQLException {
        String sql="SELECT "+COLUMNS+" FROM entrepreneurs WHERE (?='' OR full_name LIKE ? OR email LIKE ? OR city LIKE ?) AND (?='' OR status=?) ORDER BY created_at DESC";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            String q=search==null?"":search.trim();p.setString(1,q);p.setString(2,"%"+q+"%");p.setString(3,"%"+q+"%");p.setString(4,"%"+q+"%");p.setString(5,status==null?"":status);p.setString(6,status==null?"":status);
            try(ResultSet r=p.executeQuery()){List<Entrepreneur> rows=new ArrayList<>();while(r.next())rows.add(map(r));return rows;}
        }
    }
    public Entrepreneur findById(int id) throws SQLException {try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT "+COLUMNS+" FROM entrepreneurs WHERE entrepreneur_id=?")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}}}
    public void insert(Entrepreneur e) throws SQLException {String sql="INSERT INTO entrepreneurs(full_name,email,phone,gender,date_of_birth,address,city,state,registration_date,status) VALUES(?,?,?,?,?,?,?,?,?,?)";try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){bind(p,e);p.executeUpdate();}}
    public void update(Entrepreneur e) throws SQLException {String sql="UPDATE entrepreneurs SET full_name=?,email=?,phone=?,gender=?,date_of_birth=?,address=?,city=?,state=?,registration_date=?,status=? WHERE entrepreneur_id=?";try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){bind(p,e);p.setInt(11,e.getEntrepreneurId());if(p.executeUpdate()==0)throw new SQLException("Entrepreneur not found.");}}
    public void delete(int id) throws SQLException {try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM entrepreneurs WHERE entrepreneur_id=?")){p.setInt(1,id);if(p.executeUpdate()==0)throw new SQLException("Entrepreneur not found.");}}
    private void bind(PreparedStatement p,Entrepreneur e)throws SQLException{p.setString(1,e.getFullName());p.setString(2,e.getEmail());p.setString(3,e.getPhone());p.setString(4,e.getGender());p.setDate(5,e.getDateOfBirth()==null?null:java.sql.Date.valueOf(e.getDateOfBirth()));p.setString(6,e.getAddress());p.setString(7,e.getCity());p.setString(8,e.getState());p.setDate(9,java.sql.Date.valueOf(e.getRegistrationDate()));p.setString(10,e.getStatus());}
    private Entrepreneur map(ResultSet r)throws SQLException{Entrepreneur e=new Entrepreneur();e.setEntrepreneurId(r.getInt("entrepreneur_id"));e.setFullName(r.getString("full_name"));e.setEmail(r.getString("email"));e.setPhone(r.getString("phone"));e.setGender(r.getString("gender"));if(r.getDate("date_of_birth")!=null)e.setDateOfBirth(r.getDate("date_of_birth").toLocalDate());e.setAddress(r.getString("address"));e.setCity(r.getString("city"));e.setState(r.getString("state"));if(r.getDate("registration_date")!=null)e.setRegistrationDate(r.getDate("registration_date").toLocalDate());e.setStatus(r.getString("status"));return e;}
}
