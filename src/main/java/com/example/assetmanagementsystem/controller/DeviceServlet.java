package com.example.assetmanagementsystem.controller;

import com.example.assetmanagementsystem.dao.DeviceDAO;
import com.example.assetmanagementsystem.model.Device;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeviceServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final DeviceDAO dao = new DeviceDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if (req.getParameter("id") != null) {
                mapper.writeValue(resp.getOutputStream(), dao.getById(Integer.parseInt(req.getParameter("id"))));
            } else {
                mapper.writeValue(resp.getOutputStream(), dao.getAll());
            }
        }catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Device device = mapper.readValue(req.getReader(), Device.class);
            dao.create(device);
            resp.getWriter().write("Device Created with id: " + device.getId());
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Device device = mapper.readValue(req.getReader(), Device.class);
            dao.update(device);
            resp.getWriter().write("Device Updated with id: " + device.getId());
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int id=Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.getWriter().write("Device Deleted with id: " + id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }
}
