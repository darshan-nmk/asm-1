package com.example.assetmanagementsystem.dao;

import com.example.assetmanagementsystem.model.License;
import com.example.assetmanagementsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LicenseDAO {
    public void create(License license) throws Exception{
        String sql="INSERT INTO license(software_id,device_id,license_key,price,validity_in_days,activated_on) VALUES(?,?,?,?,?,?)";
        Connection con= DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        try{
            con.setAutoCommit(false);
            PreparedStatement ps2=con.prepareStatement("SELECT price,validity_in_days FROM software WHERE id=?");
            ps2.setInt(1,license.getSoftwareId());
            ResultSet sw=ps2.executeQuery();

            ps.setInt(1,license.getSoftwareId());
            ps.setInt(2,license.getDeviceId());
            ps.setString(3, license.getLicenseKey());
            ps.setFloat(4,sw.getFloat(1));
            ps.setInt(5,sw.getInt(2));
            ps.setDate(6, java.sql.Date.valueOf(license.getActivatedOn()));
            ps.executeUpdate();
            con.commit();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                int id=rs.getInt(1);
                license.setId(id);
            }
        }catch (Exception e){
            con.rollback();
            e.printStackTrace();
        }finally {
            con.close();
        }
    }
    public License getById(int id){
        String sql="SELECT * FROM license WHERE id=?";
        License license=null;
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                license=new License();
                license.setId(rs.getInt(1));
                license.setSoftwareId(rs.getInt(2));
                license.setDeviceId(rs.getInt(3));
                license.setLicenseKey(rs.getString(4));
                license.setPrice(rs.getFloat(5));
                license.setValidityInDays(rs.getInt(6));
                license.setActivatedOn(rs.getDate(7).toLocalDate());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return license;
    }

    public List<License> getAll(){
        String sql="SELECT * FROM license";
        List<License> licenseList=new ArrayList<>();
        try(Connection con=DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                License license=new License();
                license.setId(rs.getInt(1));
                license.setSoftwareId(rs.getInt(2));
                license.setDeviceId(rs.getInt(3));
                license.setLicenseKey(rs.getString(4));
                license.setPrice(rs.getFloat(5));
                license.setValidityInDays(rs.getInt(6));
                license.setActivatedOn(rs.getDate(7).toLocalDate());
                licenseList.add(license);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return licenseList;
    }
}