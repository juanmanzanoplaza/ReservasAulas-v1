package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.util.Objects;

/**
 * Clase que representa la reserva de un aula en un instituto
 * @see Profesor
 * @see Aula
 * @see Permanencia
 * @author Juan Antonio Manzano Plaza
 * @version 1
 */
public class Reserva {
	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;

	/**
	 * Constructor de la clase
	 * @param profesor el profesor que reserva el aula
	 * @param aula el aula que es reservada
	 * @param permanencia el día y el tramo en el que es reservada
	 */
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	/**
	 * Constructor copia de la clase
	 * @param otra la reserva a copiar
	 * @throws IllegalArgumentException si la reserva a copiar es nula
	 */
	public Reserva(Reserva otra) throws IllegalArgumentException {
		if(otra == null)
			throw new IllegalArgumentException("No se puede copiar una reserva nula.");
		setProfesor(otra.getProfesor());
		setAula(otra.getAula());
		setPermanencia(otra.getPermanencia());
	}

	/**
	 * Método set para el profesor de la reserva
	 * @param profesor el profesor que realiza la reserva
	 * @throws IllegalArgumentException si el profesor es nulo
	 */
	private void setProfesor(Profesor profesor) throws IllegalArgumentException {
		if(profesor == null)
			throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
		this.profesor = new Profesor(profesor);
	}

	/**
	 * Método get para el profesor de la reserva
	 * @return el profesor de la reserva
	 */
	public Profesor getProfesor() {
		return new Profesor(this.profesor);
	}

	/**
	 * Método set para el aula de la reserva
	 * @param aula el aula reservada
	 */
	private void setAula(Aula aula) throws IllegalArgumentException {
		if(aula == null)
			throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
		this.aula = new Aula(aula);
	}

	/**
	 * Método get para el aula de la reserva
	 * @return el aula de la reserva
	 */
	public Aula getAula() {
		return new Aula(this.aula);
	}

	/**
	 * Método set para la permanencia de la reserva
	 * @param permanencia la permanencia de la reserva
	 * @throws IllegalArgumentException si la permanencia es nula
	 */
	private void setPermanencia(Permanencia permanencia) throws IllegalArgumentException {
		if(permanencia == null)
			throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
		this.permanencia = new Permanencia(permanencia);
	}

	/**
	 * Método get para la permanencia de la reserva
	 * @return la permanencia de la reserva
	 */
	public Permanencia getPermanencia() {
		return new Permanencia(this.permanencia);
	}

	/**
	 * Método hashCode de la clase. Sirve para diferenciar objetos
	 * @return el código hash del objeto
	 */
	public int hashCode() {
		return Objects.hash(profesor, aula, permanencia);
	}

	/**
	 * Método equals de la clase
	 * @return True si son iguales, False si no
	 */
	public boolean equals(Object o) {
		if(o==null)
			return false;
		if(!(o instanceof Reserva))
			return false;
		Reserva otra = (Reserva) o;
		if(this.aula.equals(otra.aula) && this.permanencia.equals(otra.permanencia))
			return true;
		return false;
	}

	/**
	 * Representa una reserva como una cadena de caracteres
	 * @return la representación de la reserva
	 */
	public String toString() {
		return "[profesor=" + getProfesor() + ", aula=" + getAula() + ", permanencia=" + getPermanencia() + "]";
	}

}
