package com.example.assetmanagementsystem.dao;

import com.example.assetmanagementsystem.model.Device;
import com.example.assetmanagementsystem.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {
    public void create(Device device){
        String sql="INSERT INTO device(name,serial_number) VALUES (?,?)";
        try(Connection con= DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);){
            ps.setString(1, device.getName());
            ps.setString(2, device.getSerialNumber());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                int id=rs.getInt(1);
                device.setId(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateStatus(int deviceId, int status){
        String sql="UPDATE device SET status=? WHERE id=?";
        try(Connection con= DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1, String.valueOf(status));
            ps.setInt(2, deviceId);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Device getById(int id){
        String sql="SELECT * FROM device WHERE id=?";
        Device device=null;
        try(Connection con= DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                device=new Device();
                device.setId(rs.getInt(1));
                device.setName(rs.getString(2));
                device.setSerialNumber(rs.getString(3));
                device.setStatus(Device.Status.valueOf(rs.getString(4)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return device;
    }

    public List<Device> getAll(){
        String sql="SELECT * FROM device";
        List<Device> deviceList=new ArrayList<>();
        try(Connection con= DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Device device=new Device();
                device.setId(rs.getInt(1));
                device.setName(rs.getString(2));
                device.setSerialNumber(rs.getString(3));
                device.setStatus(Device.Status.valueOf(rs.getString(4)));
                deviceList.add(device);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return deviceList;
    }

}
