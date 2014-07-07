package srv.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IFRS-Restinga
 */
public class BarraNavegacao {
    public static void setarNavegacao(HttpServletRequest request, String menu, String linkmenu, String sub, String linksub) {
        HttpSession session = request.getSession(true);
        session.setAttribute("menu", menu);
        session.setAttribute("linkmenu", linkmenu);
        session.setAttribute("sub", sub);
        session.setAttribute("linksub", linksub);
        session.removeAttribute("submenu");
        session.removeAttribute("submenusub");
    }
}
