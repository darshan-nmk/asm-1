package com.example.assetmanagementsystem.controller;
import com.example.assetmanagementsystem.dao.VendorDAO;
import com.example.assetmanagementsystem.model.Vendor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class VendorServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final VendorDAO dao = new VendorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vendor vendor = mapper.readValue(req.getReader(), Vendor.class);
        try{
            dao.create(vendor);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().println("Vendor created successfully with id: " + vendor.getId());
        }catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

}
