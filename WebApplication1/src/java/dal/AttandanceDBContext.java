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
import model.Session;
import model.Student;

/**
 *
 * @author nguye
 */
public class AttandanceDBContext extends DBContext<Attandance> {

    public ArrayList<Attandance> getByGroup(int gid) {
        ArrayList<Attandance> attandances = new ArrayList<>();
        try {
            String sql = "select a.stdid, stdname, ses.[index],a.description, a.present from  Attendance a \n"
                    + "join Student s on a.stdid=s.stdid\n"
                    + "join [Session] ses on a.sesid=ses.sesid\n"
                    + "join [Group] g on ses.gid=g.gid\n"
                    + "where g.gid=?\n"
                    + "order by stdid, [index]";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("stdid"));
                s.setName(rs.getString("stdname"));

                Session ses = new Session();
                ses.setIndex(rs.getInt("index"));

                Attandance a = new Attandance();
                a.setSession(ses);
                a.setStudent(s);
                a.setDescription(rs.getString("description"));
                a.setPresent(rs.getBoolean("present"));

                attandances.add(a);
            }
        } catch (SQLException e) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, e);

        }
        return attandances;
    }

    public ArrayList<Attandance> filter(int sesid) {
        ArrayList<Attandance> attandances = new ArrayList<>();
        try {
            String sql = "select  a.stdid, s.stdname, a.present, a.description\n"
                    + "from Attendance a\n"
                    + "join Student s on a.stdid = s.stdid\n"
                    + "where sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attandance attandance = new Attandance();
                Student student = new Student();

                attandance.setId(sesid);
                attandance.setDescription(rs.getString("description"));
                attandance.setPresent(rs.getBoolean("present"));

                student.setId(rs.getInt("stdid"));
                student.setName(rs.getString("stdname"));

                attandance.setStudent(student);

                attandances.add(attandance);
            }
        } catch (SQLException e) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, e);

        }
        return attandances;
    }

    @Override
    public void insert(Attandance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attandance model) {
        try {
            String sql = "update Attendance \n"
                    + "set [present] = ?\n"
                    + "where sesid = ?\n"
                    + "and stdid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, model.isPresent());
            stm.setInt(2, model.getSession().getId());
            stm.setInt(3, model.getStudent().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attandance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attandance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attandance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
