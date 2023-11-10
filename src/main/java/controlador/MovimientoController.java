package controlador;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.dto.CuentaDTO;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaTipo;
import modelo.entidades.Movimiento;
import utilidades.Mes;

/**
 * 
 * Clase (Servlet) que representa el controlador para unir el modelo con la vista de los movimientos de la aplicacion web 
 * @author Juan Posso, Javier Revelo, Valery Vallejo, Cristian Verduga, Fernando Soto
 * @version 1.1
 *
 */
@WebServlet("/MovimientoController")
public class MovimientoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
     * Constructor vacio necesario para un servlet que puede ser instanciado desde otras clases
     * @see HttpServlet#HttpServlet()
     */
	public MovimientoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {
				rutear(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			try {
				rutear(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private void rutear(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ruta = (request.getParameter("ruta") == null ? "cargarFormularioIngreso" : request.getParameter("ruta"));

		switch (ruta) {
		case "cargarFormularioIngreso":
			cargarFormularioIngreso(request, response);
			break;
		case "cargarFormularioEgreso":
			cargarFormularioEgreso(request, response);
			break;
		case "crearIngreso":
			crearIngreso(request, response);
			break;
		case "crearEgreso":
			crearEgreso(request, response);
			break;
		case "error":
			errorEgreso(request, response);
			break;
		}

	}

	private void errorEgreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/gastoFallido.jsp").forward(request, response);
	}

	private void crearEgreso(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Obtención de datos del formulario
		Integer idIngresoGastoCuenta = Integer.parseInt(request.getParameter("ingresoGastoCuenta"));
		String concepto = request.getParameter("concepto");
		java.util.Date fecha = null;

		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor = Double.valueOf(request.getParameter("valor"));
		Integer idGastoCuenta = Integer.parseInt(request.getParameter("gastoCuenta"));

		// Obtener objeto cuenta by ID
		Cuenta cuentaOrigen = DAOFactory.getFactory().getCuentaDAO().getById(idIngresoGastoCuenta);
		Cuenta cuentaDestino = DAOFactory.getFactory().getCuentaDAO().getById(idGastoCuenta);

		// Verificar fondos
		double valorAntesCuentaOrigen = cuentaOrigen.getTotal();
		if (valorAntesCuentaOrigen < valor) {
			request.getRequestDispatcher("MovimientoController?ruta=error").forward(request, response);
			throw new Exception("Cuenta sin fondos");

		}

		// Actualizar cuenta IngresoGasto

		cuentaOrigen.setTotal(valorAntesCuentaOrigen - valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaOrigen);

		// Actualizar cuenta Gasto
		double valorAntesCuentaDestino = cuentaDestino.getTotal();
		cuentaDestino.setTotal(valorAntesCuentaDestino + valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaDestino);

		// Crear objeto movitmiento
		Movimiento movimiento = new Movimiento(concepto, valor, fecha, cuentaOrigen, cuentaDestino);

		// Innsertar objeto movimiento en la base de datos
		DAOFactory.getFactory().getMovimientoDAO().create(movimiento);

		System.out.println(cuentaDestino.toString());

		// Volver dashboard
		request.getRequestDispatcher("/DashboardController?ruta=ver").forward(request, response);

	}

	private void crearIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		// Obtención de datos del formulario
		Integer idIngreso = Integer.parseInt(request.getParameter("ingresoCuenta"));
		String concepto = request.getParameter("concepto");
		java.util.Date fecha = null;

		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor = Double.valueOf(request.getParameter("valor"));
		Integer idIngresoGasto = Integer.parseInt(request.getParameter("ingresoGastoCuenta"));

		// Obtener objeto cuenta by ID
		Cuenta cuentaOrigen = DAOFactory.getFactory().getCuentaDAO().getById(idIngreso);
		Cuenta cuentaDestino = DAOFactory.getFactory().getCuentaDAO().getById(idIngresoGasto);

		// Actualizar cuento Ingreso
		double valorAntesCuentaOrgien = cuentaOrigen.getTotal();
		cuentaOrigen.setTotal(valorAntesCuentaOrgien - valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaDestino);
		
		// Actualizar cuenta IngresoGasto
		double valorAntesCuentaDestino = cuentaDestino.getTotal();
		cuentaDestino.setTotal(valorAntesCuentaDestino + valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaDestino);
		

		// Crear objeto movitmiento
		Movimiento movimiento = new Movimiento(concepto, valor, fecha, cuentaOrigen, cuentaDestino);

		// Innsertar objeto movimiento en la base de datos
		DAOFactory.getFactory().getMovimientoDAO().create(movimiento);
		

		// Volver dashboard
		request.getRequestDispatcher("/DashboardController?ruta=ver").forward(request, response);

	}

	private void cargarFormularioIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int mes = LocalDate.now().getMonth().getValue();

		if (request.getParameter("mes") != null) {
			mes = Integer.parseInt(request.getParameter("mes"));
		}

		List<Cuenta> conjuntoingresos = DAOFactory.getFactory().getCuentaDAO().getCuentasByType(CuentaTipo.INGRESO);
		List<Cuenta> conjuntoingresogasto = DAOFactory.getFactory().getCuentaDAO()
				.getCuentasByType(CuentaTipo.INGRESOGASTO);

		request.setAttribute("ingresos", conjuntoingresos);
		request.setAttribute("ingresogastos", conjuntoingresogasto);
		request.getRequestDispatcher("/jsp/ingreso.jsp").forward(request, response);

	}

	private void cargarFormularioEgreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int mes = LocalDate.now().getMonth().getValue();

		if (request.getParameter("mes") != null) {
			mes = Integer.parseInt(request.getParameter("mes"));
		}

		List<Cuenta> conjuntoingresogasto = DAOFactory.getFactory().getCuentaDAO()
				.getCuentasByType(CuentaTipo.INGRESOGASTO);
		List<Cuenta> conjuntogastos = DAOFactory.getFactory().getCuentaDAO().getCuentasByType(CuentaTipo.GASTO);
		request.setAttribute("ingresogastos", conjuntoingresogasto);
		request.setAttribute("gastos", conjuntogastos);

		request.getRequestDispatcher("/jsp/egreso.jsp").forward(request, response);

	}

}
