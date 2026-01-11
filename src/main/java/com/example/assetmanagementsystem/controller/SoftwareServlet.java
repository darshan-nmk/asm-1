package com.example.assetmanagementsystem.controller;

import com.example.assetmanagementsystem.dao.SoftwareDAO;
import com.example.assetmanagementsystem.model.Software;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SoftwareServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final SoftwareDAO dao=new SoftwareDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
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
        Software software=mapper.readValue(req.getInputStream(),Software.class);
        try{
            dao.create(software);
            resp.getWriter().println("Software created with id: "+software.getId());
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch(Exception e){
            resp.sendError(500,e.getMessage());
        }
    }
}
