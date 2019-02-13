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
	 * Constructor por defecto. Inicializa el n�mero de profesores a cero
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
	 * Guarda en la colecci�n actual de profesores los que hay en la recibida como par�metro
	 * @param profesores la colecci�n a copiar
	 * @throws IllegalArgumentException si se intenta copiar un conjunto de profesores nulos
	 */
	private void setProfesores(Profesores profesores) throws IllegalArgumentException {
		if(profesores==null)
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		this.coleccionProfesores = copiaProfundaProfesores(profesores.getProfesores());
	}

	/**
	 * Realiza la copia en profundidad de cada profesor para evitar aliasing
	 * @param profesores la colecci�n de profesores a copiar
	 * @return una copia de la colecci�n
	 */
	private Profesor[] copiaProfundaProfesores(Profesor[] profesores) {
		Profesor[] copia = new Profesor[MAX_PROFESORES];
		for(int i = 0; i<copia.length && profesores[i]!=null; i++)
			copia[i] = new Profesor(profesores[i]);
		return copia;
	}

	/**
	 * Obtiene todos los profesores de la colecci�n actual. Realiza una copia para evitar aliasing
	 * @return una copia de la colecci�n
	 */
	public Profesor[] getProfesores() {
		return copiaProfundaProfesores(this.coleccionProfesores);
	}

	/**
	 * Obtiene el n�mero de profesores que existen en la colecci�n actual
	 * @return el n�mero de profesores
	 */
	public int getNumProfesores() {
		return numProfesores;
	}

	/**
	 * Guarda un profesor en la colecci�n
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
			throw new OperationNotSupportedException("Se ha alcanzado el m�ximo de profesores que se pueden guardar.");
	}

	/**
	 * Busca el �ndice en la colecci�n de un profesor indicado
	 * @param profesor el profesor cuyo �ndice queremos buscar
	 * @return el �ndice del profesor
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
	 * Comprueba que el �ndice no supera el n�mero de profesores existentes
	 * @param indice el �ndice a comprobar
	 * @return True si no supera el tama�o, False si lo supera
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		if(indice<numProfesores)
			return true;
		return false;
	}

	/**
	 * Comprueba que el �ndice no supera la cantidad m�xima que se puede guardar
	 * @param indice el �ndice a comprobar
	 * @return True si no supera la capacidad, False si la supera
	 */
	private boolean indiceNoSuperaCantidad(int indice) {
		if(indice<MAX_PROFESORES)
			return true;
		return false;
	}

	/**
	 * Busca un profesor en la colecci�n
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
	 * Borra un profesor de la colecci�n
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
	 * Desplaza los profesores a la posici�n anterior desde un �ndice dado
	 * @param indice desde donde hay que mover los profesores
	 */
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for(int i = indice; i<coleccionProfesores.length && coleccionProfesores[i+1]!=null; i++)
			coleccionProfesores[i] = coleccionProfesores[i+1];
		coleccionProfesores[numProfesores-1] = null;
	}

	/**
	 * Obtiene las salidas de todos los profesores de la colecci�n
	 * @return la salida de los profesores
	 */
	public String[] representar() {
		String[] representar = new String[numProfesores];
		for(int i = 0; i < representar.length; i++)
			representar[i] = coleccionProfesores[i].toString();
		return representar;
	}

}
