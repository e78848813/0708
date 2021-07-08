package com.hazards.controller;

import com.hazards.bean.Page;
import com.hazards.bean.Student;
import com.hazards.bean.User;
import com.hazards.service.StudentService;
import com.hazards.util.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@MultipartConfig
@WebServlet("/stu")
public class StudentServlet extends BaseServlet {

    //定义全局变量service,用于操作逻辑功能
    private StudentService studentService = new StudentService();

    /**
     * 添加学生信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String sbir = request.getParameter("sbir");
        String[] hobbies = request.getParameterValues("hobby");
        String hobby = Arrays.toString(hobbies);
        hobby = hobby.substring(1, hobby.length() - 1);
        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + fileName;
        String path = "D:/upload";
        String filePath = path + "/" + fileName;
        part.write(filePath);
        //数据封装
        Student student = new Student(sname, gender, DateUtils.getDateByString(sbir), hobby, fileName);
        boolean flag = studentService.addStudent(student);
        if (flag) {
            response.sendRedirect("stu?method=queryStudent");
        } else {
            request.getRequestDispatcher("adderror.jsp").forward(request, response);
        }
    }

    /**
     * 查询学生信息,并将其存放在request作用域中,在前段页面显示出来
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            response.sendRedirect("login.html");
            return;
        }
        //获取前端页面的数据
        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String currentPage = request.getParameter("currentPage");
        //封装Student对象
        Student student = new Student(null, sname, gender, null, null, null);
        //生成Page对象
        Page<Student> studentPage = studentService.selectPage(currentPage, student);
        //将查询的信息存入作用域中,在前端页面中显示出来
        request.setAttribute("studentPage", studentPage);
        request.setAttribute("student", student); //回显查询条件
        request.getRequestDispatcher("queryStu.jsp").forward(request, response);
    }

    /**
     * 批量删除学生信息
     *
     * @param request
     * @param response
     */
    public void batchDelete(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        boolean b = studentService.batchDeleteByIds(ids);
        if (b) {
            try {
                response.sendRedirect("stu?method=queryStudent");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据id查询学生信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getStudentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("sid");
        Student student = studentService.selectStudentById(id);
        request.setAttribute("stu", student);
        request.getRequestDispatcher("updateStu.jsp").forward(request, response);
    }

    /**
     * 根据id修改学生信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void updateStudentById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String sbir = request.getParameter("sbir");
        String[] hobbies = request.getParameterValues("hobby");
        String hobby = Arrays.toString(hobbies);
        hobby=hobby.substring(1,hobby.length()-1);
        String oldPhoto = request.getParameter("oldPhoto");
        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        if (fileName.length() != 0) {
            fileName = part.getSubmittedFileName();
            String uuid = UUID.randomUUID().toString();
            fileName = uuid + fileName;
            String filePath = "D:/upload/" + fileName;
            part.write(filePath);
        }
        if (fileName.length() == 0) {
            fileName = oldPhoto;
        }

        boolean isSaveSuccess = studentService.updateStudentById(id, new Student(sname, gender, DateUtils.getDateByString(sbir), hobby, fileName));
        response.sendRedirect("stu?method=queryStudent");
    }

    /**
     * 管理员登录,必须登录后才可以访问管理系统
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = studentService.loginByPassword(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user != null) {
            request.getRequestDispatcher("stu?method=queryStudent").forward(request, response);
        } else {
            request.setAttribute("msg", "用户名或密码错误,请重试!");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

}