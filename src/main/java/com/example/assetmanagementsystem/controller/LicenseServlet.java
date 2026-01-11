package com.example.assetmanagementsystem.controller;

import com.example.assetmanagementsystem.dao.LicenseDAO;
import com.example.assetmanagementsystem.dao.SoftwareDAO;
import com.example.assetmanagementsystem.model.License;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LicenseServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    private final LicenseDAO doa = new LicenseDAO();
    private final SoftwareDAO sdoa = new SoftwareDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            if (req.getParameter("id") != null) {
                mapper.writeValue(resp.getOutputStream(), doa.getById(Integer.parseInt(req.getParameter("id"))));
            } else {
                mapper.writeValue(resp.getOutputStream(), doa.getAll());
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        }catch (Exception e){
            resp.reset();
            resp.sendError(500, e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        License license=mapper.readValue(req.getInputStream(),License.class);
        try{
            doa.create(license);
            resp.setStatus(201);
            resp.getWriter().write("license created successfully with id:"+license.getId());
        }catch(Exception e){
            resp.sendError(500,e.getMessage());
        }
    }
}
