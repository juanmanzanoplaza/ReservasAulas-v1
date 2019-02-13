package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

/**
 * Clase que guarda y define las operaciones que se pueden realizar sobre un conjunto de reservas.
 * @see Reserva
 * @author Juan Antonio Manzano Plaza
 * @version 0
 *
 */
public class Reservas {
	private static final int MAX_RESERVAS = 100;
	private int numReservas;
	private Reserva[] coleccionReservas;

	/**
	 * Constructor por defecto. Inicializa el n�mero de profesores a cero
	 */
	public Reservas() {
		this.numReservas = 0;
		coleccionReservas = new Reserva[MAX_RESERVAS];
	}

	/**
	 * Constructor copia. Realiza copia profunda para evitar aliasing
	 * @param reservas el objeto del que obtener los datos para inicializar
	 */
	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}

	/**
	 * Guarda en la colecci�n actual de reservas los que hay en la colecci�n recibida como par�metro
	 * @param reservas la colecci�n a copiar
	 * @throws IllegalArgumentException si se intenta copiar un conjunto de reservas nulo
	 */
	private void setReservas(Reservas reservas) throws IllegalArgumentException {
		if(reservas==null)
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		this.coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
	}

	/**
	 * Realiza la copia en profundidad de cada reserva para evitar aliasing
	 * @param reservas la colecci�n de reservas a copiar
	 * @return una copia de la colecci�n
	 */
	private Reserva[] copiaProfundaReservas(Reserva[] reservas) {
		Reserva[] copia = new Reserva[MAX_RESERVAS];
		for(int i = 0; i<copia.length && reservas[i]!=null; i++)
			copia[i] = new Reserva(reservas[i]);
		return copia;
	}

	/**
	 * Obtiene todas las reservas de la colecci�n actual. Realiza una copia para evitar aliasing
	 * @return una copia de la colecci�n
	 */
	public Reserva[] getReservas() {
		return copiaProfundaReservas(this.coleccionReservas);
	}

	/**
	 * Obtiene el n�mero de reservas que existen en la colecci�n actual
	 * @return el n�mero de reservas
	 */
	public int getNumReservas() {
		return numReservas;
	}

	/**
	 * Guarda una reserva en la colecci�n
	 * @param reserva la reserva a guardar
	 * @throws IllegalArgumentException si la reserva es nula
	 * @throws OperationNotSupportedException si la reserva ya existe o se supera la capacidad
	 */
	public void insertar(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException{
		if(reserva==null)
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		int indice = buscarIndiceReserva(reserva);
		if(indiceNoSuperaTamano(indice))
			throw new OperationNotSupportedException("La reserva ya existe.");
		if(indiceNoSuperaCapacidad(indice)) {
			coleccionReservas[indice] = reserva;
			numReservas++;
		} else
			throw new OperationNotSupportedException("Se ha alcanzado el m�ximo de reservas que se pueden guardar.");
	}

	/**
	 * Busca el �ndice en la colecci�n de una reserva indicada
	 * @param reserva la reserva cuyo �ndice queremos buscar
	 * @return el �ndice de la reserva
	 */
	private int buscarIndiceReserva(Reserva reserva) {
		for(int i = 0; i < coleccionReservas.length; i++) {
			if(coleccionReservas[i]!=null) {
				if(coleccionReservas[i].equals(reserva))
					return i;
			} else
				return i;
		}
		return MAX_RESERVAS;
	}

	/**
	 * Comprueba que el �ndice no supera el n�mero de reservas existentes
	 * @param indice el �ndice a comprobar
	 * @return True si no supera el tama�o, False si lo supera
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		if(indice<numReservas)
			return true;
		return false;
	}

	/**
	 * Comprueba que el �ndice no supera la cantidad m�xima que se puede guardar
	 * @param indice el �ndice a comprobar
	 * @return True si no supera la capacidad, False si la supera
	 */
	private boolean indiceNoSuperaCapacidad(int indice) {
		if(indice<MAX_RESERVAS)
			return true;
		return false;
	}

	/**
	 * Busca una reserva en la colecci�n
	 * @param reserva la reserva a buscar
	 * @return la reserva buscada o null si no la encuentra
	 */
	public Reserva buscar(Reserva reserva) {
		if(reserva==null)
			return null;
		int indice = buscarIndiceReserva(reserva);
		if(indiceNoSuperaTamano(indice))
			return coleccionReservas[indice];
		return null;
	}

	/**
	 * Borra una reserva de la colecci�n
	 * @param reserva la reserva a borrar
	 * @throws IllegalArgumentException si la reserva es nula
	 * @throws OperationNotSupportedException si la reserva no existe
	 */
	public void borrar(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException {
		if(reserva==null)
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		int indice = buscarIndiceReserva(reserva);
		if(indiceNoSuperaTamano(indice)) {
			coleccionReservas[indice] = null;
			desplazarUnaPosicionHaciaIzquierda(indice);
			numReservas--;
		} else
			throw new OperationNotSupportedException("La reserva a anular no existe.");
	}

	/**
	 * Desplaza las reservas a la posici�n anterior desde un �ndice dado
	 * @param indice desde donde hay que mover las reservas
	 */
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for(int i = indice; i<coleccionReservas.length && coleccionReservas[i+1]!=null; i++)
			coleccionReservas[i] = coleccionReservas[i+1];
		coleccionReservas[numReservas-1] = null;
	}

	/**
	 * Obtiene las salidas de todas las reservas de la colecci�n
	 * @return la salida de las reservas
	 */
	public String[] representar() {
		String[] representar = new String[numReservas];
		for(int i = 0; i < representar.length; i++)
			representar[i] = coleccionReservas[i].toString();
		return representar;
	}

	/**
	 * Obtiene las reservas a nombre de un profesor indicado
	 * @param profesor el profesor que ha reservado
	 * @return las reservas del profesor
	 * @throws IllegalArgumentException si el profesor es nulo
	 */
	public Reserva[] getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
		if(profesor==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de un profesor nulo.");
		Reserva[] devolver = new Reserva[MAX_RESERVAS];
		int indice = 0;
		for(int i = 0; i<numReservas; i++) {
			if(coleccionReservas[i].getProfesor().equals(profesor)) {
				devolver[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return devolver;
	}

	/**
	 * Obtiene las reservas realizadas a un aula indicada
	 * @param aula el aula reservada
	 * @return las reservas del aula
	 * @throws IllegalArgumentException si el aula es nula
	 */
	public Reserva[] getReservasAula(Aula aula) throws IllegalArgumentException {
		if(aula==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		Reserva[] devolver = new Reserva[MAX_RESERVAS];
		int indice = 0;
		for(int i = 0; i<numReservas; i++) {
			if(coleccionReservas[i].getAula().equals(aula)) {
				devolver[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return devolver;
	}

	/**
	 * Obtiene las reservas realizadas en una fecha y tramo concretos
	 * @param permanencia la fecha y el tramo de las reservas
	 * @return las reservas de esa fecha y tramo
	 * @throws IllegalArgumentException si la permanencia es nula
	 */
	public Reserva[] getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
		if(permanencia==null)
			throw new IllegalArgumentException("No se pueden consultar las reservas de una permanencia nula.");
		Reserva[] devolver = new Reserva[MAX_RESERVAS];
		int indice = 0;
		for(int i = 0; i<numReservas; i++) {
			if(coleccionReservas[i].getPermanencia().equals(permanencia)) {
				devolver[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return devolver;
	}

	/**
	 * Comprueba si un aula est� disponible en una fecha y tramos indicados
	 * @param aula el aula a comprobar
	 * @param permanencia la fecha y tramo en las que comprobar el aula
	 * @return True si est� disponible, False si est� reservada
	 */
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
		if(aula==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		if(permanencia==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		for(int i = 0; i<coleccionReservas.length && coleccionReservas[i]!=null; i++) {
			if(coleccionReservas[i].getAula().equals(aula) && coleccionReservas[i].getPermanencia().equals(permanencia))
				return false;
		}
		return true;
	}

}
