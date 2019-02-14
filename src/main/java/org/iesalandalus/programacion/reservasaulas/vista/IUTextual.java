package org.iesalandalus.programacion.reservasaulas.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

/**
 *
 * Clase encargada de tratar las excepciones y llamar a las distintas funciones del modelo ModeloReservasAulas
 * @see ModeloReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 1
 *
 */
public class IUTextual {

	//Previo al mensaje del error.
	private static final String ERROR = "ERROR: ";
//	private static final String NOMBRE_VALIDO = "Juan";
	private static final String CORREO_VALIDO = "a@a.a";
	protected ModeloReservasAulas modelo;

	/**
	 * Constructor de la clase
	 */
	public IUTextual() {
		this.modelo = new ModeloReservasAulas();
		Opcion.setVista(this);
	}

	/**
	 * Método que inicia el programa y mientras que no se elija la opción salir sigue ofreciendo opciones.
	 */
	public void comenzar() {
		Opcion opcion;
		do {
			Consola.mostrarMenu();
			//Lectura de la opción
			opcion = Opcion.getOpcionSegunOrdinal(Consola.elegirOpcion());
			// Ejecución de la opción
			opcion.ejecutar();
		} while (opcion != Opcion.SALIR);
	}

	/**
	 * Ejecuta la orden salir de Opcion
	 */
	public void salir() {
		System.out.println("Fin de la ejecución.");
	}

	/**
	 * Ejecuta la orden insertarAula de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void insertarAula() {
		Consola.mostrarCabecera("INSERTAR AULA");
		try {
			modelo.insertarAula(Consola.leerAula());
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
		System.out.println("Aula insertada.");
	}

	/**
	 * Ejecuta la orden borrarAula de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void borrarAula() {
		Consola.mostrarCabecera("BORRAR AULA");
		try {
			modelo.borrarAula(new Aula(Consola.leerNombreAula()));
			System.out.println("Aula eliminada.");
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	/**
	 * Ejecuta la orden buscarAula de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void buscarAula() {
		Consola.mostrarCabecera("BUSCAR AULA");
		Aula leida = new Aula(Consola.leerNombreAula());
		Aula buscada = modelo.buscarAula(leida);
		if(buscada==null)
			System.out.println(ERROR + "El aula buscada no existe.");
		else
			System.out.println("Se ha encontrado el aula buscada: " + buscada);
	}

	/**
	 * Ejecuta la orden listarAulas de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void listarAulas() {
		Consola.mostrarCabecera("LISTAR AULAS");
		List<String> aulas = modelo.representarAulas();
		if(aulas.size()==0)
			System.out.println(ERROR + "No hay ningún aula guardada.");
		for(String s : aulas)
			System.out.println(s);
	}

	/**
	 * Ejecuta la orden insertarProfesor de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void insertarProfesor() {
		Consola.mostrarCabecera("INSERTAR PROFESOR");
		try {
			modelo.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado.");
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	/**
	 * Ejecuta la orden borrarProfesor de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void borrarProfesor() {
		Consola.mostrarCabecera("BORRAR PROFESOR");
		Profesor borrar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		try {
			modelo.borrarProfesor(borrar);
			System.out.println("Profesor borrado.");
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	/**
	 * Ejecuta la orden buscarProfesor de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void buscarProfesor() {
		Consola.mostrarCabecera("BUSCAR PROFESOR");
		Profesor buscado = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		Profesor encontrado = modelo.buscarProfesor(buscado);
		if(encontrado == null)
			System.out.println(ERROR + "El profesor buscado no existe.");
		else
			System.out.println("Se ha encontrado el profesor buscado: " + buscado);
	}

	/**
	 * Ejecuta la orden listarProfesores de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void listarProfesores() {
		Consola.mostrarCabecera("LISTAR PROFESORES");
		List<String> profesores = modelo.representarProfesores();
		if(profesores.size()==0)
			System.out.println(ERROR + "No hay ningún profesor guardado.");
		for(String s : profesores)
			System.out.println(s);
	}

	/**
	 * Ejecuta la orden realizarReserva de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void realizarReserva() {
		Consola.mostrarCabecera("REALIZAR RESERVA");
		Profesor profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		boolean lecturaCorrecta = true;
		if(modelo.buscarProfesor(profesor)==null) {
			System.out.println(ERROR + "El profesor introducido no existe.");
			lecturaCorrecta = false;
		}
		Reserva reserva = null;
		if(lecturaCorrecta) {
			reserva = leerReserva(profesor);
			if(reserva==null)
				System.out.println(ERROR + "El aula introducida no existe.");
		}
		if(reserva==null)
			System.out.println(ERROR + "La reserva no se pudo realizar.");
		else {
			try {
				modelo.realizarReserva(reserva);
				System.out.println("Reserva realizada correctamente.");
			} catch (OperationNotSupportedException e) {
				System.out.println(ERROR + e.getMessage());
			}
		}
	}

	/**
	 * Método privado que se encarga de leer una reserva
	 * @param profesor el profesor responsable de la reserva
	 * @return null si el profesor o el aula no existen, la reserva leída si existen ambos
	 */
	private Reserva leerReserva(Profesor profesor) {
		Profesor buscado = modelo.buscarProfesor(profesor);
		if(buscado == null)
			return null;
		Aula buscada = modelo.buscarAula(new Aula(Consola.leerNombreAula()));
		if(buscada==null)
			return null;
		return new Reserva(buscado, buscada, new Permanencia(Consola.leerDia(), Consola.leerTramo()));
	}

	/**
	 * Ejecuta la orden anularReserva de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void anularReserva() {
		Consola.mostrarCabecera("ANULAR RESERVA");
		Profesor buscado = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		boolean lecturaCorrecta = true;
		if(modelo.buscarProfesor(buscado)==null) {
			System.out.println("El profesor introducido no existe.");
			lecturaCorrecta = false;
		}
		Reserva reserva = null;
		if(lecturaCorrecta) {
			reserva = leerReserva(buscado);
			if(reserva==null)
				System.out.println("El aula introducida no existe.");
		}
		if(reserva==null)
			System.out.println("La reserva no se pudo anular.");
		else {
			try {
				modelo.anularReserva(reserva);
			} catch (OperationNotSupportedException e) {
				System.out.println(ERROR + e.getMessage());
			}
			System.out.println("Reserva anulada correctamente.");
		}
	}

	/**
	 * Ejecuta la orden listarReservas de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void listarReservas() {
		Consola.mostrarCabecera("LISTAR RESERVAS");
		List<String> reservas = modelo.representarReservas();
		if(reservas.size()==0)
			System.out.println("No hay ninguna reserva hecha.");
		for(String s : reservas)
			System.out.println(s);
	}

	/**
	 * Ejecuta la orden listarReservasAula de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void listarReservasAula() {
		Consola.mostrarCabecera("LISTAR RESERVAS AULA");
		Aula aula = new Aula(Consola.leerNombreAula());
		boolean lecturaCorrecta = true;
		if(modelo.buscarAula(aula)==null){
			System.out.println(ERROR + "El aula introducida no existe.");
			lecturaCorrecta = false;
		}
		List<Reserva> reservas = modelo.getReservasAula(aula);
		if(lecturaCorrecta && reservas.size()==0)
			System.out.println("El aula indicada no está reservada.");
		if(lecturaCorrecta) {
			for(Reserva r : reservas)
				System.out.println(r);
		}
	}

	/**
	 * Ejecuta la orden listarReservasProfesor de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("LISTAR RESERVAS PROFESOR");
		Profesor profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		boolean lecturaCorrecta = true;
		if(modelo.buscarProfesor(profesor)==null){
			System.out.println(ERROR + "El profesor introducido no existe.");
			lecturaCorrecta = false;
		}
		List<Reserva> reservas = modelo.getReservasProfesor(profesor);
		if(lecturaCorrecta && reservas.size()==0)
			System.out.println("El profesor indicado no tiene ningún aula reservada.");
		if(lecturaCorrecta) {
			for(Reserva r : reservas)
				System.out.println(r);
		}
	}

	/**
	 * Ejecuta la orden listarReservasPermanencia de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("LISTAR RESERVAS PERMANENCIA");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		List<Reserva> reservas = modelo.getReservasPermanencia(permanencia);
		if(reservas.size()==0)
			System.out.println("En ese tramo no hay ningún aula reservada.");
		for(Reserva r : reservas)
			System.out.println(r);
	}

	/**
	 * Ejecuta la orden consultarDisponibilidad de Opcion llamando al método correspondiente de ModeloReservasAulas
	 */
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("CONSULTAR DISPONIBILIDAD");
		Aula aula = new Aula(Consola.leerNombreAula());
		boolean lecturaCorrecta = true;
		if(modelo.buscarAula(aula) == null) {
			System.out.println(ERROR + "El aula indicada no existe.");
			lecturaCorrecta = false;
		}
		if(lecturaCorrecta) {
			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			boolean disponible = modelo.consultarDisponibilidad(aula, permanencia);
			if(disponible)
				System.out.println("El aula consultada está disponible para el tramo especificado.");
			else
				System.out.println("El aula consultada no está disponible para el tramo especificado.");
		}
	}

}
