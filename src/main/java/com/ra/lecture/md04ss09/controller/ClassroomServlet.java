package com.ra.lecture.md04ss09.controller;

import com.ra.lecture.md04ss09.dto.ClassroomDTO;
import com.ra.lecture.md04ss09.model.service.ClassroomService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ClassroomServlet", value = "/class")
public class ClassroomServlet extends HttpServlet {
    private ClassroomService classroomService;
    @Override
    public void init() {
        classroomService = new ClassroomService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<ClassroomDTO> classroomList = classroomService.getAll();
        request.setAttribute("classroomList", classroomList);
        request.getRequestDispatcher("views/class.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    }

    @Override
    public void destroy() {

    }

}