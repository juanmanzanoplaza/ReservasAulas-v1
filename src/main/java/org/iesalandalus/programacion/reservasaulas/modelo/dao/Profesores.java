package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

/**
 * Clase que guarda y define las operaciones que se pueden realizar sobre un conjunto de profesores
 * @see Profesor
 * @author Juan Antonio Manzano Plaza
 * @version 0
 *
 */
public class Profesores {

	private static final int MAX_PROFESORES = 100;
	private int numProfesores;
	private Profesor[] coleccionProfesores;

	/**
	 * Constructor por defecto. Inicializa el número de profesores a cero
	 */
	public Profesores() {
		this.numProfesores = 0;
		coleccionProfesores = new Profesor[MAX_PROFESORES];
	}

	/**
	 * Constructor copia. Realiza copia profunda para evitar aliasing
	 * @param profesores el objeto del que obtener los datos para inicializar
	 */
	public Profesores(Profesores profesores) {
		setProfesores(profesores);
	}

	/**
	 * Guarda en la colección actual de profesores los que hay en la recibida como parámetro
	 * @param profesores la colección a copiar
	 * @throws IllegalArgumentException si se intenta copiar un conjunto de profesores nulos
	 */
	private void setProfesores(Profesores profesores) throws IllegalArgumentException {
		if(profesores==null)
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		this.coleccionProfesores = copiaProfundaProfesores(profesores.getProfesores());
	}

	/**
	 * Realiza la copia en profundidad de cada profesor para evitar aliasing
	 * @param profesores la colección de profesores a copiar
	 * @return una copia de la colección
	 */
	private Profesor[] copiaProfundaProfesores(Profesor[] profesores) {
		Profesor[] copia = new Profesor[MAX_PROFESORES];
		for(int i = 0; i<copia.length && profesores[i]!=null; i++)
			copia[i] = new Profesor(profesores[i]);
		return copia;
	}

	/**
	 * Obtiene todos los profesores de la colección actual. Realiza una copia para evitar aliasing
	 * @return una copia de la colección
	 */
	public Profesor[] getProfesores() {
		return copiaProfundaProfesores(this.coleccionProfesores);
	}

	/**
	 * Obtiene el número de profesores que existen en la colección actual
	 * @return el número de profesores
	 */
	public int getNumProfesores() {
		return numProfesores;
	}

	/**
	 * Guarda un profesor en la colección
	 * @param profesor el profesor a guardar
	 * @throws IllegalArgumentException si el profesor es nulo
	 * @throws OperationNotSupportedException si el profesor ya existe o se supera la capacidad
	 */
	public void insertar(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException {
		if(profesor==null)
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
		int indice = buscarIndiceProfesor(profesor);
		if(indiceNoSuperaTamano(indice))
			throw new OperationNotSupportedException("El profesor ya existe.");
		if(indiceNoSuperaCantidad(indice)) {
			coleccionProfesores[indice] = profesor;
			numProfesores++;
		} else
			throw new OperationNotSupportedException("Se ha alcanzado el máximo de profesores que se pueden guardar.");
	}

	/**
	 * Busca el índice en la colección de un profesor indicado
	 * @param profesor el profesor cuyo índice queremos buscar
	 * @return el índice del profesor
	 */
	private int buscarIndiceProfesor(Profesor profesor) {
		for(int i = 0; i < coleccionProfesores.length; i++) {
			if(coleccionProfesores[i]!=null) {
				if(coleccionProfesores[i].equals(profesor))
					return i;
			} else
				return i;
		}
		return MAX_PROFESORES;
	}

	/**
	 * Comprueba que el índice no supera el número de profesores existentes
	 * @param indice el índice a comprobar
	 * @return True si no supera el tamaño, False si lo supera
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		if(indice<numProfesores)
			return true;
		return false;
	}

	/**
	 * Comprueba que el índice no supera la cantidad máxima que se puede guardar
	 * @param indice el índice a comprobar
	 * @return True si no supera la capacidad, False si la supera
	 */
	private boolean indiceNoSuperaCantidad(int indice) {
		if(indice<MAX_PROFESORES)
			return true;
		return false;
	}

	/**
	 * Busca un profesor en la colección
	 * @param profesor el profesor a buscar
	 * @return el profesor buscado o null si no lo encuentra
	 */
	public Profesor buscar(Profesor profesor) {
		if(profesor==null)
			return null;
		int indice = buscarIndiceProfesor(profesor);
		if(indiceNoSuperaTamano(indice))
			return coleccionProfesores[indice];
		return null;
	}

	/**
	 * Borra un profesor de la colección
	 * @param profesor el profesor a borrar
	 * @throws IllegalArgumentException si el profesor es nulo
	 * @throws OperationNotSupportedException si el profesor no existe
	 */
	public void borrar(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException {
		if(profesor==null)
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
		int indice = buscarIndiceProfesor(profesor);
		if(indiceNoSuperaTamano(indice)) {
			coleccionProfesores[indice] = null;
			desplazarUnaPosicionHaciaIzquierda(indice);
			numProfesores--;
		} else
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
	}

	/**
	 * Desplaza los profesores a la posición anterior desde un índice dado
	 * @param indice desde donde hay que mover los profesores
	 */
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for(int i = indice; i<coleccionProfesores.length && coleccionProfesores[i+1]!=null; i++)
			coleccionProfesores[i] = coleccionProfesores[i+1];
		coleccionProfesores[numProfesores-1] = null;
	}

	/**
	 * Obtiene las salidas de todos los profesores de la colección
	 * @return la salida de los profesores
	 */
	public String[] representar() {
		String[] representar = new String[numProfesores];
		for(int i = 0; i < representar.length; i++)
			representar[i] = coleccionProfesores[i].toString();
		return representar;
	}

}
