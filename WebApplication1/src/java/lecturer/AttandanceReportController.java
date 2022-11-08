/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecturer;

import controller.auth.BaseRoleController;
import dal.AttandanceDBContext;
import dal.GroupDBContext;
import dal.LecturerDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Attandance;
import model.Group;
import model.Lecturer;
import model.Session;
import model.Student;

/**
 *
 * @author minh0
 */
public class AttandanceReportController extends BaseRoleController {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        LecturerDBContext ldb = new LecturerDBContext();
        int lid = ldb.getLecturer(account.getUsername()).getId();
        int gid = Integer.parseInt(req.getParameter("gid"));
        
        LecturerDBContext lecturerDB = new LecturerDBContext();
        Lecturer lecturer = lecturerDB.get(lid);
        req.setAttribute("lecturer", lecturer);

        GroupDBContext groupDB = new GroupDBContext();
        ArrayList<Group> groups = groupDB.getGroups(lid);
        req.setAttribute("groups", groups);

        AttandanceDBContext attendDB = new AttandanceDBContext();
        ArrayList<Attandance> attandances = attendDB.getByGroup(gid);
        req.setAttribute("attendances", attandances);

        StudentDBContext studentDB = new StudentDBContext();
        ArrayList<Student> students = studentDB.getByGid(gid);
        req.setAttribute("students", students);

        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<Session> sessions = sessionDB.getByGroup(gid);
        req.setAttribute("sessions", sessions);

        int numSes = sessions.size();
        int numAtt = attandances.size();
        int numStu = students.size();

        ArrayList<Float> totals = new ArrayList<>();
        //(a.student.id eq s.id) and (a.session.index eq ses.index stu ses att

        for (int i = 0; i < numStu; i++) {
            float total = 0;
            for (int j = 0; j < numSes; j++) {
                for (int k = 0; k < numAtt; k++) {
                    if (attandances.get(k).getStudent().getId() == students.get(i).getId()
                            && attandances.get(k).getSession().getIndex() == sessions.get(j).getIndex()
                            && !attandances.get(k).isPresent()) {
                        total++;
                    }
                }
            }
            totals.add(total / numSes * 100);
        }
        req.setAttribute("totals", totals);

        req.getRequestDispatcher("../view/lecturer/attandancereport.jsp").forward(req, resp);
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }
}
