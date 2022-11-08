/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attandance;
import model.Group;
import model.Lecturer;
import model.Session;
import model.Student;
import model.Subject;

/**
 *
 * @author nguye
 */
public class StudentDBContext extends DBContext<Student> {

    public ArrayList<Student> getBySesid(int sesid) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select s.stdid, stdname, ses.sesid from Student s\n"
                    + "join Attendance a on a.stdid=s.stdid\n"
                    + "join [Session] ses on a.sesid=ses.sesid\n"
                    + "where ses.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("stdid"));
                s.setName(rs.getString("stdname"));
                students.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public ArrayList<Student> getByGid(int gid) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select s.stdid, stdname from [Student] s\n"
                    + "join Student_Group sg on s.stdid=sg.stdid\n"
                    + "join [Group] g on sg.gid=g.gid\n"
                    + "where g.gid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("stdid"));
                s.setName(rs.getString("stdname"));
                students.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    public ArrayList<Student> list(int gid) {
        try {
            ArrayList<Student> students = new ArrayList<>();
            String sql = "SELECT DISTINCT s.stdid,s.stdname\n"
                    + "FROM [Session] ses \n"
                    + "	LEFT JOIN [Group] g ON g.gid = ses.gid\n"
                    + "	INNER JOIN [Student_Group] sg ON sg.gid = g.gid\n"
                    + "	INNER JOIN Student s ON sg.stdid = s.stdid\n"
                    + "WHERE g.gid = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("stdid"));
                student.setName(rs.getString("stdname"));
                students.add(student);
            }
            return students;
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
