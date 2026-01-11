package com.example.assetmanagementsystem.dao;
import com.example.assetmanagementsystem.model.Employee;
import com.example.assetmanagementsystem.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public void create(Employee employee) {
        String sql="INSERT INTO employee(name) VALUES (?)";
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1,employee.getName());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                employee.setId(rs.getInt(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Employee getById(int id) {
        String sql="SELECT * FROM employee WHERE id=?";
        Employee employee=null;
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                employee=new Employee(rs.getInt(1),rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> getAll() {
        String sql="SELECT * FROM employee";
        List<Employee> employeeList= new ArrayList<>();
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Employee employee=new Employee(rs.getInt(1),rs.getString(2));
                employeeList.add(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employeeList;
    }

    public void delete(int id) {
        String sql="DELETE FROM employee WHERE id=?";
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Employee employee) {
        String sql="UPDATE employee SET name=? WHERE id=?";
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,employee.getName());
            ps.setInt(2,employee.getId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
