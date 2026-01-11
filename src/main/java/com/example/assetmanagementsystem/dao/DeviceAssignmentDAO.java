package com.example.assetmanagementsystem.dao;

import com.example.assetmanagementsystem.model.Device;
import com.example.assetmanagementsystem.model.DeviceAssignment;
import com.example.assetmanagementsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeviceAssignmentDAO {
    public void create(DeviceAssignment deviceAssignment) throws Exception {
        Connection con= DBUtil.getConnection();
        try{
            con.setAutoCommit(false);
            PreparedStatement ps= con.prepareStatement("INSERT INTO deviceAssignment(device_id,employee_id,assigned_from) VALUES (?,?,?)");
            ps.setInt(1, deviceAssignment.getDeviceId());
            ps.setInt(2, deviceAssignment.getEmployeeId());
            ps.setDate(3, java.sql.Date.valueOf(deviceAssignment.getAssignedFrom()));
            ps.executeUpdate();
            PreparedStatement ps2=con.prepareStatement("UPDATE device SET status=? WHERE id=?");
            ps2.setString(1, Device.Status.ASSIGNED.toString());
            ps2.setInt(2, deviceAssignment.getDeviceId());
            ps2.executeUpdate();
            con.commit();
        }catch (Exception e){
            con.rollback();
            e.printStackTrace();
        }finally {
            con.close();
        }
    }
    public void update(int deviceId, LocalDate assignedTill) throws Exception {
        Connection con = DBUtil.getConnection();
        try{
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE deviceAssignment SET assigned_till=? WHERE device_id=?");
            ps.setDate(1, java.sql.Date.valueOf(assignedTill));
            ps.setInt(2, deviceId);
            ps.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement("UPDATE device SET status=? WHERE id=?");
            ps2.setString(1, Device.Status.AVAILABLE.toString());
            ps2.setInt(2, deviceId);
            ps2.executeUpdate();
            con.commit();
        }catch (Exception e){
            con.rollback();
        }finally {
            con.close();
        }

    }
    public List<DeviceAssignment> getByID(int deviceId){
        String sql="SELECT * FROM deviceAssignment WHERE device_id=?";
        List<DeviceAssignment> deviceAssignmentList=new ArrayList<>();
        try(Connection con=DBUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, deviceId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DeviceAssignment deviceAssignment=new DeviceAssignment();
                deviceAssignment.setDeviceId(rs.getInt(1));
                deviceAssignment.setEmployeeId(rs.getInt(2));
                deviceAssignment.setAssignedFrom(rs.getDate(3).toLocalDate());
                deviceAssignment.setAssignedTill(rs.getDate(4).toLocalDate());
                deviceAssignmentList.add(deviceAssignment);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return deviceAssignmentList;
    }

    public List<DeviceAssignment> getAll(){
        String sql="SELECT * FROM deviceAssignment";
        List<DeviceAssignment> deviceAssignmentList=new ArrayList<>();
        try(Connection con=DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                DeviceAssignment deviceAssignment=new DeviceAssignment();
                deviceAssignment.setDeviceId(rs.getInt(1));
                deviceAssignment.setEmployeeId(rs.getInt(2));
                deviceAssignment.setAssignedFrom(rs.getDate(3).toLocalDate());
                if(rs.getDate(4)!=null){
                    deviceAssignment.setAssignedTill(rs.getDate(4).toLocalDate());
                }else{
                    deviceAssignment.setAssignedTill(null);
                }
                deviceAssignmentList.add(deviceAssignment);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return deviceAssignmentList;
    }
}
