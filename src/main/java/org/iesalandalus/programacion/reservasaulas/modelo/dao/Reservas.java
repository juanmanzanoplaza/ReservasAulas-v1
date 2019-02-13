package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

/**
 * Clase que guarda y define las operaciones que se pueden realizar sobre un conjunto de reservas.
 * @see Reserva
 * @author Juan Antonio Manzano Plaza
 * @version 1
 *
 */
public class Reservas {

	private List<Reserva> coleccionReservas;

	/**
	 * Constructor por defecto. Inicializa el número de profesores a cero
	 */
	public Reservas() {
		coleccionReservas = new ArrayList<Reserva>();
	}

	/**
	 * Constructor copia. Realiza copia profunda para evitar aliasing
	 * @param reservas el objeto del que obtener los datos para inicializar
	 */
	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}

	/**
	 * Guarda en la colección actual de reservas los que hay en la colección recibida como parámetro
	 * @param reservas la colección a copiar
	 * @throws IllegalArgumentException si se intenta copiar un conjunto de reservas nulo
	 */
	private void setReservas(Reservas reservas) throws IllegalArgumentException {
		if(reservas==null)
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		this.coleccionReservas = copiaProfundaReservas(reservas.getReservas());
	}

	/**
	 * Realiza la copia en profundidad de cada reserva para evitar aliasing
	 * @param reservas la colección de reservas a copiar
	 * @return una copia de la colección
	 */
	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> copia = new ArrayList<Reserva>();
		for(Reserva r : reservas)
			copia.add(new Reserva(r));
		return copia;
	}

	/**
	 * Obtiene todas las reservas de la colección actual. Realiza una copia para evitar aliasing
	 * @return una copia de la colección
	 */
	public List<Reserva> getReservas() {
		return copiaProfundaReservas(this.coleccionReservas);
	}

	/**
	 * Obtiene el número de reservas que existen en la colección actual
	 * @return el número de reservas
	 */
	public int getNumReservas() {
		return this.coleccionReservas.size();
	}

	/**
	 * Guarda una reserva en la colección
	 * @param reserva la reserva a guardar
	 * @throws IllegalArgumentException si la reserva es nula
	 * @throws OperationNotSupportedException si la reserva ya existe o se supera la capacidad
	 */
	public void insertar(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException{
		if(reserva==null)
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		if(this.coleccionReservas.contains(reserva))
			throw new OperationNotSupportedException("La reserva ya existe.");
		coleccionReservas.add(reserva);
	}

	/**
	 * Busca una reserva en la colección
	 * @param reserva la reserva a buscar
	 * @return la reserva buscada o null si no la encuentra
	 */
	public Reserva buscar(Reserva reserva) {
		if(reserva==null)
			return null;
		if(this.coleccionReservas.indexOf(reserva) == -1)
			return null;
		return this.coleccionReservas.get(this.coleccionReservas.indexOf(reserva));
	}

	/**
	 * Borra una reserva de la colección
	 * @param reserva la reserva a borrar
	 * @throws IllegalArgumentException si la reserva es nula
	 * @throws OperationNotSupportedException si la reserva no existe
	 */
	public void borrar(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException {
		if(reserva==null)
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		if(!this.coleccionReservas.remove(reserva))
			throw new OperationNotSupportedException("La reserva a anular no existe.");
	}

	/**
	 * Obtiene las salidas de todas las reservas de la colección
	 * @return la salida de las reservas
	 */
	public List<String> representar() {
		List<String> representar = new ArrayList<String>();
		for(Reserva r : this.coleccionReservas)
			representar.add(r.toString());
		return representar;
	}

	/**
	 * Obtiene las reservas a nombre de un profesor indicado
	 * @param profesor el profesor que ha reservado
	 * @return las reservas del profesor
	 * @throws IllegalArgumentException si el profesor es nulo
	 */
	public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
		if(profesor==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de un profesor nulo.");
		List<Reserva> devolver = new ArrayList<Reserva>();
		for(Reserva r : this.coleccionReservas) {
			if(r.getProfesor().equals(profesor))
				devolver.add(new Reserva(r));
		}
		return devolver;
	}

	/**
	 * Obtiene las reservas realizadas a un aula indicada
	 * @param aula el aula reservada
	 * @return las reservas del aula
	 * @throws IllegalArgumentException si el aula es nula
	 */
	public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException {
		if(aula==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		List<Reserva> devolver = new ArrayList<Reserva>();
		for(Reserva r : this.coleccionReservas) {
			if(r.getAula().equals(aula))
				devolver.add(new Reserva(r));
		}
		return devolver;
	}

	/*
	 * public List<Reserva> getReservasAula(Aula aula) {
		if(aula==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		List<Reserva> reservaAula = new ArrayList<>();

		for (Reserva reserva : coleccionReservas) {
                  if( coleccionReservas.contains(aula))

				reservaAula.add(new Reserva(reserva));

			}

		return reservaAula;
	}
	 */

	/**
	 * Obtiene las reservas realizadas en una fecha y tramo concretos
	 * @param permanencia la fecha y el tramo de las reservas
	 * @return las reservas de esa fecha y tramo
	 * @throws IllegalArgumentException si la permanencia es nula
	 */
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
		if(permanencia==null)
			throw new IllegalArgumentException("No se pueden consultar las reservas de una permanencia nula.");
		List<Reserva> devolver = new ArrayList<Reserva>();
		for(Reserva r : this.coleccionReservas) {
			if(r.getPermanencia().equals(permanencia))
				devolver.add(new Reserva(r));
		}
		return devolver;
	}

	/**
	 * Comprueba si un aula está disponible en una fecha y tramos indicados
	 * @param aula el aula a comprobar
	 * @param permanencia la fecha y tramo en las que comprobar el aula
	 * @return True si está disponible, False si está reservada
	 */
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
		if(aula==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		if(permanencia==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		for(Reserva r : this.coleccionReservas) {
			if(r.getAula().equals(aula) && r.getPermanencia().equals(permanencia))
				return false;
		}
		return true;
	}

}
