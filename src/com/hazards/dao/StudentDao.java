package com.hazards.dao;

import com.hazards.bean.Student;
import com.hazards.bean.User;
import com.hazards.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDao {
    private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    /**
     * 添加学生对象到数据库中
     * @param student 学生对象
     * @return 返回影响行数, 如果添加成功返回1, 添加失败返回0
     */
    public int addStudent(Student student) {
        String sql = "insert into stu values(0,?,?,?,?,?)";
        int rows = 0;
        try {
            rows = qr.update(sql, student.getSname(), student.getGender(), student.getSbir(), student.getHobby(), student.getPhoto());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 查询数据总数
     * @param student 学生对象
     * @return 返回查询到的数据总数
     */
    public long countNum(Student student) {
        StringBuilder sb = new StringBuilder("select count(sid) from stu where 1 = 1 ");
        sqlCondition(student, sb);
        String sql = sb.toString();
        long count = 0;
        try {
            count = qr.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*
    拼接sql语句
     */
    private void sqlCondition(Student student, StringBuilder sb) {
        if (student.getSname() != null && student.getSname().trim().length() != 0) {
            sb.append(" and sname like '%").append(student.getSname()).append("%'");
        }
        if (student.getGender() != null && student.getGender().trim().length() != 0) {
            sb.append(" and gender = '").append(student.getGender()).append("'");
        }
    }

    /**
     * 查询所有数据
     *
     * @param student 学生对象
     * @param start   索引开始
     * @param end     索引长度
     * @return 返回查询出来的集合
     */
    public List<Student> selectStudent(Student student, int start, int end) {
        StringBuilder sb = new StringBuilder("select * from stu where 1 = 1 ");
        sqlCondition(student, sb);
        sb.append(" limit ? , ?");
        String sql = sb.toString();
        List<Student> studentList = null;
        try {
            studentList = qr.query(sql, new BeanListHandler<>(Student.class), start, end);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    /**
     * 通过id数组ids批量删除信息
     * @param ids id数组
     * @return 返回成功删除的数据数
     */
    public int deleteStudentByIds(String ids) {
        String sql = "delete from stu where sid in(" + ids + ")";
        int rows = 0;
        try {
            rows = qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return rows;
    }

    /**
     * 根据传入的id和student对象修改信息
     * @param id 要修改的id
     * @param student 修改后的Student对象
     * @return 返回影响行数
     */
    public int updateStudentById(String id,Student student){
        String sql="update stu set sname = ? , gender = ? , sbir = ? , hobby = ? , photo = ? where sid = ?";
        int rows = 0;
        System.out.println(student);
        try {
            rows = qr.update(sql, student.getSname(), student.getGender(), student.getSbir(),
                    student.getHobby(), student.getPhoto(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 根据id查询单个学生信息
     * @param id 查询学生的id
     * @return 学生信息对象
     */
    public Student selectStudentById(String id){
        String sql="select * from stu where sid = ?";
        Student student = null;
        try {
            student = qr.query(sql, new BeanHandler<>(Student.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * 根据用户名和密码查询出用户
     * @param username 用户名
     * @param password 密码
     * @return 返回查询的User对象
     */
    public User getUserByPassword(String username,String password){
        String sql="select * from user where username = ? and password = ?";
        User user = null;
        try {
            user = qr.query(sql, new BeanHandler<>(User.class), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
