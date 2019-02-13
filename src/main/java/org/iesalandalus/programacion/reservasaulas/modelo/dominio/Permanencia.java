package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase que representa un tramo de reservas Reserva
 * @author Juan Antonio Manzano Plaza
 * @version 1
 *
 */
public class Permanencia {

	private LocalDate dia;
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private Tramo tramo;

	/**
	 * Constructor de la clase
	 * @param dia el día de la reserva
	 * @param tramo el tramo de la reserva
	 */
	public Permanencia(LocalDate dia, Tramo tramo) {
		setDia(dia);
		setTramo(tramo);
	}

	/**
	 * Constructor copia
	 * @param p la permanencia a copiar
	 */
	public Permanencia(Permanencia p) throws IllegalArgumentException {
		if(p==null)
			throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
		setDia(p.getDia());
		setTramo(p.getTramo());
	}

	/**
	 * Método get para el día de la reserva
	 * @return el día de la reserva
	 */
	public LocalDate getDia() {
		return LocalDate.of(dia.getYear(), dia.getMonth(), dia.getDayOfMonth());
	}

	/**
	 * Método set para el día de la reserva
	 * @param dia el día de la reserva
	 * @throws IllegalArgumentException si el día es nulo
	 */
	private void setDia(LocalDate dia) throws IllegalArgumentException {
		if(dia==null)
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
		this.dia = LocalDate.of(dia.getYear(), dia.getMonth(), dia.getDayOfMonth());
	}

	/**
	 * Método get para el tramo de la reserva
	 * @return el tramo de la reserva
	 */
	public Tramo getTramo() {
		return tramo;
	}

	/**
	 * Método set para el tramo de la reserva
	 * @param tramo el tramo de la reserva
	 * @throws IllegalArgumentException si el tramo es nulo
	 */
	private void setTramo(Tramo tramo) throws IllegalArgumentException {
		if(tramo == null)
			throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
		this.tramo = tramo;
	}

	/**
	 * Método hashCode de la clase. Sirve para diferenciar objetos
	 * @return el código hash del objeto
	 */
	public int hashCode() {
		return Objects.hash(dia, tramo);
	}

	/**
	 * Método equals de la clase
	 * @return True si son iguales, False si no
	 */
	public boolean equals(Object o) {
		if(o==null)
			return false;
		if(!(o instanceof Permanencia))
			return false;
		Permanencia otra = (Permanencia) o;
		if(this.getDia().equals(otra.getDia()) && this.getTramo().equals(otra.getTramo()))
			return true;
		return false;
	}

	/**
	 * Representa una permanencia como una cadena de caracteres
	 * @return la representación de la permanencia
	 */
	public String toString() {
		return "[dia=" + getDia().format(FORMATO_DIA) + ", tramo=" + getTramo() + "]";
	}
}
