package com.example.assetmanagementsystem.controller;

import com.example.assetmanagementsystem.dao.DeviceAssignmentDAO;
import com.example.assetmanagementsystem.model.DeviceAssignment;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeviceAssignmentServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    private final DeviceAssignmentDAO dao = new DeviceAssignmentDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try{
            if(req.getParameter("deviceId")!=null){
                int deviceId=Integer.parseInt(req.getParameter("deviceId"));
                mapper.writeValue(resp.getOutputStream(),dao.getByID(deviceId));
            }
            else {
                mapper.writeValue(resp.getOutputStream(),dao.getAll());
            }
            resp.setStatus(200);
        }catch(Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            DeviceAssignment deviceAssignment=mapper.readValue(req.getReader(), DeviceAssignment.class);
            dao.create(deviceAssignment);
            resp.setStatus(201);
            resp.getWriter().write("Device Assignment created successfully");
        }catch(Exception e){
            resp.sendError(500,e.getMessage());
        }
    }
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            DeviceAssignment deviceAssignment=mapper.readValue(req.getReader(), DeviceAssignment.class);
            dao.update(deviceAssignment.getDeviceId(),deviceAssignment.getAssignedTill());
            resp.setStatus(204);
            resp.getWriter().write("Device Assignment Updated");
        }catch(Exception e){
            resp.sendError(500,e.getMessage());
        }
    }
}
