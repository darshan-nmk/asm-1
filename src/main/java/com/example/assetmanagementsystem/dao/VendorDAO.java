package com.example.assetmanagementsystem.dao;
import com.example.assetmanagementsystem.model.Vendor;
import com.example.assetmanagementsystem.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {
    public void create(Vendor vendor) {
        String sql="INSERT INTO vendor(name) VALUES (?)";
        try(Connection con=DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1,vendor.getName());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                vendor.setId(rs.getInt(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Vendor getById(int id) {
        String sql="SELECT * FROM vendor WHERE id=?";
        Vendor vendor=null;
        try(Connection con=DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                vendor=new Vendor(rs.getInt(1),rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vendor;
    }

    public List<Vendor> getAll() {
        String sql="SELECT * FROM vendor";
        List<Vendor> vendorsList= new ArrayList<>();
        try(Connection con=DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Vendor employee=new Vendor(rs.getInt(1),rs.getString(2));
                vendorsList.add(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vendorsList;
    }
}
