package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidades.Usuario;

/**
 * 
 * Clase (Servlet) que representa el controlador para unir el modelo con la vista del Login de la aplicacion web 
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor vacio necesario para un servlet que puede ser instanciado desde otras clases
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rutear(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rutear(request,response);
	}
	
	private void rutear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta = (request.getParameter("ruta"))==null?"ingresar":request.getParameter("ruta");
		
		switch (ruta) {
		case "ingresar":
			this.ingresar(request,response);
			break;
		case "login":
			this.login(request,response);
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + ruta);
		}
	}
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario= request.getParameter("usuario");
		String clave= request.getParameter("password");
		Usuario autorizada=null;
		autorizada =DAOFactory.getFactory().getUsuarioDAO().autorizar(usuario, clave);
		if (autorizada!=null) {
			//creo la sesion 
			HttpSession sesion= request.getSession();
			sesion.setAttribute("usuario", autorizada);
			
			request.getRequestDispatcher("DashboardController?ruta=ver").forward(request, response);
		}else {
			response.sendRedirect("LoginController");
		}
		
	}
	private void ingresar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}
		
		
	
	

}
