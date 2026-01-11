package com.example.assetmanagementsystem.dao;

import com.example.assetmanagementsystem.model.Software;
import com.example.assetmanagementsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SoftwareDAO {
    public void create(Software software) {
        String sql="INSERT INTO software(name,price,validity_in_days,vendor_id) VALUES (?,?,?,?)";
        try(Connection con= DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setString(1,software.getName());
                ps.setFloat(2,software.getPrice());
                ps.setInt(3,software.getValidityInDays());
                ps.setInt(4,software.getVendorId());
                ps.executeUpdate();
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    software.setId(rs.getInt(1));
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Software getById(int id){
        String sql="SELECT * FROM software WHERE id=?";
        Software software=null;
        try(Connection con= DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                software=new Software();
                software.setId(rs.getInt(1));
                software.setName(rs.getString(2));
                software.setPrice(rs.getFloat(3));
                software.setValidityInDays(rs.getInt(4));
                software.setVendorId(rs.getInt(5));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return software;
    }

    public List<Software> getAll(){
        String sql="SELECT * FROM software";
        List<Software> softwareList=new ArrayList<>();
        try(Connection con= DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Software software=new Software();
                software.setId(rs.getInt(1));
                software.setName(rs.getString(2));
                software.setPrice(rs.getFloat(3));
                software.setValidityInDays(rs.getInt(4));
                software.setVendorId(rs.getInt(5));
                softwareList.add(software);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return softwareList;
    }

    public void delete(int id){
        String sql="DELETE FROM software WHERE id=?";
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Software software){
        String sql="UPDATE software SET  name=?,price=?,validity_in_days=? WHERE id=?";
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,software.getName());
            ps.setFloat(2,software.getPrice());
            ps.setInt(3,software.getValidityInDays());
            ps.setInt(4,software.getId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
