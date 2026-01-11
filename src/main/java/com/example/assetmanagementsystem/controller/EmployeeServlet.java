package com.example.assetmanagementsystem.controller;
import com.example.assetmanagementsystem.dao.EmployeeDAO;
import com.example.assetmanagementsystem.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class EmployeeServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final EmployeeDAO dao = new EmployeeDAO();

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
        Employee employee = mapper.readValue(req.getReader(), Employee.class);
        try{
            dao.create(employee);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().println("Employee created successfully with id: " + employee.getId());
        }catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = mapper.readValue(req.getReader(), Employee.class);
        try{
            dao.update(employee);
            resp.getWriter().println("Employee updated successfully for id " + employee.getId());
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        try{
            dao.delete(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            resp.getWriter().println("Employee deleted successfully");
        }catch (Exception e){
            resp.sendError(500,e.getMessage());
        }
    }

}
