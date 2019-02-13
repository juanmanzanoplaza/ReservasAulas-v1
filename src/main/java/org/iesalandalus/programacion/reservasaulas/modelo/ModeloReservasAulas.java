package org.iesalandalus.programacion.reservasaulas.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dao.Aulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Profesores;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Reservas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.vista.IUTextual;

/**
 *
 * Clase modelo utilizada como intermediaria entre IUTextual, que accede a todas las funciones de la aplicaci�n.
 * @see IUTextual
 * @see Profesores
 * @see Aulas
 * @see Reservas
 * @author Juan Antonio Manzano Plaza
 * @version 0
 *
 */
public class ModeloReservasAulas {

	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;

	/**
	 * Constructor de la clase. Crea los distintos atributos de la clase.
	 */
	public ModeloReservasAulas() {
		this.profesores = new Profesores();
		this.aulas = new Aulas();
		this.reservas = new Reservas();
	}

	/**
	 * Obtiene todas las aulas registradas. Llama al m�todo getAulas de Aulas
	 * @return todas las aulas guardadas
	 */
	public List<Aula> getAulas() {
		return aulas.getAulas();
	}

	/**
	 * Obtiene el n�mero de aulas registradas. Llama al m�todo getNumAulas de Aulas
	 * @return el n�mero de aulas guardadas
	 */
	public int getNumAulas() {
		return aulas.getNumAulas();
	}

	/**
	 * Obtiene la salida de todas las aulas registradas. Llama al m�todo representar de Aulas
	 * @return un array con la representaci�n de cada aula en forma de cadena
	 */
	public List<String> representarAulas() {
		return aulas.representar();
	}

	/**
	 * Busca el aula indicada entre las que han sido registradas. Llama al m�todo buscar de Aulas
	 * @param buscar el aula que estamos buscando
	 * @return el aula buscada o null si no la encuentra
	 */
	public Aula buscarAula(Aula buscar) {
		return aulas.buscar(buscar);
	}

	/**
	 * Guarda el aula indicada. Llama al m�todo insertar de Aulas
	 * @param insertar el aula que queremos guardar
	 * @throws OperationNotSupportedException si se intenta insertar un aula nula, ya existente o se ha superado la capacidad
	 */
	public void insertarAula(Aula insertar) throws OperationNotSupportedException, IllegalArgumentException {
		aulas.insertar(insertar);
	}

	/**
	 * Borra el aula indicada si existe entre las guardadas. Llama al m�todo borrar de Aulas
	 * @param borrar el aula que queremos borrar
	 * @throws OperationNotSupportedException si se intenta borrar un aula nula o que no existe
	 */
	public void borrarAula(Aula borrar) throws OperationNotSupportedException, IllegalArgumentException {
		aulas.borrar(borrar);
	}

	/**
	 * Obtiene todos los profesores registrados. Llama al m�todo getProfesores de Profesores
	 * @return todos los profesores guardados
	 */
	public List<Profesor> getProfesores() {
		return profesores.getProfesores();
	}

	/**
	 * Obtiene el n�mero de profesores registrados. Llama al m�todo getNumProfesores de Profesores
	 * @return el n�mero de profesores guardados
	 */
	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}

	/**
	 * Obtiene la salida de todos los profesores registrados. Llama al m�todo representar de Profesores
	 * @return un array con la representaci�n de cada profesor en forma de cadena
	 */
	public List<String> representarProfesores() {
		return profesores.representar();
	}

	/**
	 * Busca el profesor indicado entre los que han sido registrados. Llama al m�todo buscar de Profesores
	 * @param buscar el profesor que buscamos
	 * @return el profesor buscado o null si no lo encuentra
	 */
	public Profesor buscarProfesor(Profesor buscar) {
		return profesores.buscar(buscar);
	}

	/**
	 * Guarda el profesor indicado. Llama al m�todo insertar de Profesores
	 * @param insertar el profesor que queremos guardar
	 * @throws OperationNotSupportedException si se intenta insertar un profesor nulo, ya existente o se ha superado la capacidad
	 */
	public void insertarProfesor(Profesor insertar) throws OperationNotSupportedException, IllegalArgumentException {
		profesores.insertar(insertar);
	}

	/**
	 * Borra el profesor indicado si existe entre los que han sido registrados. Llama al m�todo borrar de Profesores
	 * @param borrar el profesor que queremos borrar
	 * @throws OperationNotSupportedException si se intenta borrar un profesor nulo o que no existe
	 */
	public void borrarProfesor(Profesor borrar) throws OperationNotSupportedException, IllegalArgumentException {
		profesores.borrar(borrar);
	}

	/**
	 * Obtiene todas las reservas realizadas. Llama al m�todo getReservas de Reservas
	 * @return todas las reservas realizadas
	 */
	public Reserva[] getReservas() {
		return reservas.getReservas();
	}

	/**
	 * Obtiene el n�mero de reservas realizadas. Llama al m�todo getNumReservas de Reservas
	 * @return el n�mero de reservas guardadas
	 */
	public int getNumReservas() {
		return reservas.getNumReservas();
	}

	/**
	 * Obtiene la salida de todas las reservas realizadas. Llama al m�todo representar de Reservas
	 * @return un array con la representaci�n de cada reserva en forma de cadena
	 */
	public String[] representarReservas() {
		return reservas.representar();
	}

	/**
	 * Busca la reserva indicada entre las que se han realizado. Llama al m�todo buscar de Reservas
	 * @param buscar la reserva que buscamos
	 * @return la reserva buscada o null si no la encuentra
	 */
	public Reserva buscarReserva(Reserva buscar) {
		return reservas.buscar(buscar);
	}

	/**
	 * Guarda la reserva indicada. Llama al m�todo insertar de Reservas
	 * @param realizar la reserva a realizar
	 * @throws OperationNotSupportedException si se intenta realizar una reserva nula, ya existente o se ha superado la capacidad
	 */
	public void realizarReserva(Reserva realizar) throws OperationNotSupportedException, IllegalArgumentException {
		reservas.insertar(realizar);
	}

	/**
	 * Borra la reserva indicada si existe entre las realizadas. Llama al m�todo borrar de Reserva
	 * @param anular la reserva a anular
	 * @throws OperationNotSupportedException si se intenta anular una reserva nula o que no existe
	 */
	public void anularReserva(Reserva anular) throws OperationNotSupportedException, IllegalArgumentException {
		reservas.borrar(anular);
	}

	/**
	 * Obtiene todas las reservas correspondientes al aula indicada. Llama al m�todo getReservasAula de Reservas
	 * @param aula el aula sobre la que est�n hechas las reservas
	 * @return un array con todas las reservas sobre el aula indicada
	 */
	public Reserva[] getReservasAula(Aula aula) throws IllegalArgumentException {
		return reservas.getReservasAula(aula);
	}

	/**
	 * Obtiene todas las reservas realizadas por el profesor indicado. Llama al m�todo getReservasProfesor de Reservas
	 * @param profesor el profesor a nombre del que est�n hechas las reservas
	 * @return un array con todas las reservas a nombre del profesor indicado
	 */
	public Reserva[] getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
		return reservas.getReservasProfesor(profesor);
	}

	/**
	 * Obtiene todas las reservas realizadas en un d�a y tramo indicados. Llama al m�todo getReservasPermanencia de Reservas
	 * @param permanencia la fecha de las reservas
	 * @return un array con todas las reservas de ese d�a y tramo
	 */
	public Reserva[] getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
		return reservas.getReservasPermanencia(permanencia);
	}

	/**
	 * Comprueba si hay alguna reserva realizada sobre un aula en una permanencia indicadas. Llama al m�todo consultarDisponibilidad de Reservas
	 * @param aula el aula sobre la que queremos consultar la disponibilidad
	 * @param permanencia el d�a que queremos comprobar si est� reservada el aula
	 * @return True si est� disponible (no est� reservada) y False si no est� disponible (est� reservada)
	 */
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}

}