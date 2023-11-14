package com.ra.lecture.md04ss09.controller;

import com.ra.lecture.md04ss09.dto.ClassroomDTO;
import com.ra.lecture.md04ss09.dto.StudentDTO;
import com.ra.lecture.md04ss09.model.entity.Classroom;
import com.ra.lecture.md04ss09.model.service.ClassroomService;
import com.ra.lecture.md04ss09.model.service.StudentService;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;
    private ClassroomService classroomService;
    @Override
    public void init() {
        studentService = new StudentService();
        classroomService = new ClassroomService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        List<ClassroomDTO> classroomList = new ArrayList<>();
        classroomList = classroomService.getAll();
        int studentId = 0;
        if (action == null) {
            showStudentList(request, response);
        } else {
            switch (action) {
                case "add":
                    request.setAttribute("classroomList", classroomList);
                    request.getRequestDispatcher("views/student-add.jsp").forward(request,response);
                    break;
                case "edit":
                    request.setAttribute("classroomList", classroomList);
                    studentId = Integer.parseInt(request.getParameter("id"));
                    StudentDTO studentDTO = studentService.getById(studentId);
                    request.setAttribute("studentDTO", studentDTO);
                    request.getRequestDispatcher("views/student-edit.jsp").forward(request,response);
                    break;
                case "delete":
                    studentId = Integer.parseInt(request.getParameter("id"));
                    studentService.delete(studentId);
                    showStudentList(request,response);
                    break;
                case "sort":
                    request.setAttribute("studentList", studentService.getSortedStudentDTOList());
                    request.getRequestDispatcher("views/student.jsp").forward(request,response);
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String studentName = request.getParameter("studentName");
        int studentId;
        int classId = -1;
        Date birthday;
        String className;
        List<ClassroomDTO> classroomList;
        StudentDTO studentDTO;

        if (action == null) {
            showStudentList(request, response);
        } else {
            switch (action) {
                case "add":
                    studentId = Integer.parseInt(request.getParameter("id"));
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = simpleDateFormat.parse(request.getParameter("birthday"));
                        birthday = new Date(date.getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                     className = request.getParameter("className");
//        TODO: check lại logic đoạn này
                    if (className == null) {
                        className = "Math";
                    }
                    classroomList = classroomService.getAll();
                    for (ClassroomDTO classroomDTO : classroomList) {
                        if (classroomDTO.getName().equals(className)) {
                            classId = classroomDTO.getId();
                        }
                    }
                    studentDTO = new StudentDTO();
                    studentDTO.setName(studentName);
                    studentDTO.setBirthday(birthday);
                    studentDTO.setClassId(classId);

                    studentService.save(studentDTO);
                    showStudentList(request,response);
                    break;
                case "update":
                    studentId = Integer.parseInt(request.getParameter("id"));
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = simpleDateFormat.parse(request.getParameter("birthday"));
                        birthday = new Date(date.getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    className = request.getParameter("className");
//        TODO: check lại logic đoạn này
                    if (className == null) {
                        className = "Math";
                    }
                    classroomList = classroomService.getAll();
                    for (ClassroomDTO classroomDTO : classroomList) {
                        if (classroomDTO.getName().equals(className)) {
                            classId = classroomDTO.getId();
                        }
                    }
                    studentDTO = new StudentDTO();
                    studentDTO.setName(studentName);
                    studentDTO.setBirthday(birthday);
                    studentDTO.setClassId(classId);

                    studentService.update(studentDTO, studentId);
                    showStudentList(request,response);
                    break;
                case "search":
                    String searchTerm = request.getParameter("searchTerm");
                    List<StudentDTO> searchResult = studentService.searchByClass(searchTerm);
                    request.setAttribute("studentList", searchResult);
                    request.getRequestDispatcher("views/student.jsp").forward(request,response);
                    break;
            }
        }
    }

    @Override
    public void destroy() {

    }

    private void showStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentDTO> studentList = studentService.getAll();
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("views/student.jsp").forward(request,response);
    }


}