package com.hazards.service;

import com.hazards.bean.Page;
import com.hazards.bean.Student;
import com.hazards.bean.User;
import com.hazards.dao.StudentDao;

import java.util.List;

public class StudentService {
    private StudentDao dao = new StudentDao();

    /**
     * 添加学生到数据库中
     *
     * @param student 学生对象
     * @return 添加成功返回true, 失败返回false
     */
    public boolean addStudent(Student student) {
        int row = dao.addStudent(student);
        return row != 0;
    }

    /**
     * 根据当前页数,和传入的学生信息查询出对应页面对象信息
     *
     * @param currentPage
     * @param student
     * @return
     */
    public Page<Student> selectPage(String currentPage, Student student) {
        if (currentPage == null) currentPage = "1";
        int countNum = (int) dao.countNum(student);//统计总共学生数据
        int currentPageNum = 1; //初始化当前页
        if (currentPage.trim().length() != 0) {
            currentPageNum = Integer.parseInt(currentPage);
        }
        int initSize = 3; //每页显示条数
        int start = (currentPageNum - 1) * initSize;
        List<Student> list = dao.selectStudent(student, start, initSize);
        Page<Student> studentPage = new Page<>(initSize, countNum, currentPageNum, list);
        return studentPage;
    }

    /**
     * 根据传入的id数组ids删除对应数据,并判断是否删除成功
     * @param ids id数组
     * @return 是否成功删除
     */
    public boolean batchDeleteByIds(String ids) {
        int rows = dao.deleteStudentByIds(ids);
        return rows > 0;
    }

    /**
     * 根据传入的id和student修改信息
     * @param id 需要修改的学生信息的id
     * @param student 修改后的学生对象
     * @return 是否修改成功
     */
    public boolean updateStudentById(String id, Student student) {
        int rows = dao.updateStudentById(id, student);
        return rows > 0;
    }

    /**
     * 查询单个学生信息
     * @param id 查询学生的id
     * @return 查询到的学生信息对象
     */
    public Student selectStudentById(String id){
        return dao.selectStudentById(id);
    }


    /**
     * 根据用户名和密码判断是否登录成功
     * @param username 用户名
     * @param password 密码
     * @return 返回用户对象
     */
    public User loginByPassword(String username,String password){
        return dao.getUserByPassword(username, password);
    }
}
