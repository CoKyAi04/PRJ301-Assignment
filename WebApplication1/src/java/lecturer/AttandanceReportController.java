/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecturer;

import controller.auth.BaseRoleController;
import dal.GroupDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;
import model.Group;

/**
 *
 * @author minh0
 */
public class AttandanceReportController extends BaseRoleController {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        int groupid = Integer.parseInt(req.getParameter("gid"));
        int lid = Integer.parseInt(req.getParameter("lid"));
        int subid = Integer.parseInt(req.getParameter("subid"));
        GroupDBContext groupDB = new GroupDBContext();
        Group group = groupDB.get(groupid, lid, subid);
        req.setAttribute("group", group);
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
