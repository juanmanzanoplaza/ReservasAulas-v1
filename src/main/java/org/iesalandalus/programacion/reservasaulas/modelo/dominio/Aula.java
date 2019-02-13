package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.util.Objects;

/**
 * Clase que representa un aula de un instituto
 * @author Juan Antonio Manzano Plaza
 * @version 1
 *
 */
public class Aula {

	private String nombre;

	/**
	 * Constructor de la clase
	 * @param nombre el nombre del aula
	 */
	public Aula(String nombre) {
		setNombre(nombre);
	}

	/**
	 * Constructor copia
	 * @param otra el aula a copiar
	 */
	public Aula(Aula otra) throws IllegalArgumentException {
		if(otra==null)
			throw new IllegalArgumentException("No se puede copiar un aula nula.");
		setNombre(otra.getNombre());
	}

	/**
	 * M�todo set para el nombre del aula
	 * @param nombre el nombre del aula
	 * @throws IllegalArgumentException si el nombre es nulo o vac�o
	 */
	private void setNombre(String nombre) throws IllegalArgumentException {
		if(nombre==null)
			throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");
		if(nombre.equals(""))
			throw new IllegalArgumentException("El nombre del aula no puede estar vac�o.");
		this.nombre = nombre;
	}

	/**
	 * M�todo get para el nombre del aula
	 * @return el nombre del aula
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * M�todo hashCode de la clase. Sirve para diferenciar objetos
	 * @return el c�digo hash del objeto
	 */
	public int hashCode() {
		return Objects.hash(nombre);
	}

	/**
	 * M�todo equals de la clase
	 * @return True si son iguales, False si no
	 */
	public boolean equals(Object o) {
		if(o==null)
			return false;
		if(!(o instanceof Aula))
			return false;
		Aula otra = (Aula) o;
		if(this.getNombre().equals(otra.getNombre()) && this.hashCode()==otra.hashCode())
			return true;
		return false;
	}

	/**
	 * Representa un aula como una cadena de caracteres
	 * @return la representaci�n del aula
	 */
	public String toString() {
		return "[nombre=" + nombre + "]";
	}
}
