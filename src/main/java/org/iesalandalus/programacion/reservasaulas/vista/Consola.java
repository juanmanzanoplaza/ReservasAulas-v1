package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 *
 * Clase dedicada a la interacción con el usuario. Pide y valida los datos por teclado.
 * @see IUTextual
 * @see Opcion
 * @author Juan Antonio Manzano Plaza
 * @version 0
 *
 */
public class Consola {

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/uuuu");

	/**
	 * Constructor privado para evitar que se instancien objetos de la clase.
	 */
	private Consola() {
	}

	/**
	 * Método estático encargado de mostrar por pantalla todas las opciones de Opcion
	 */
	public static void mostrarMenu() {
		System.out.println("*************************************************************");
		System.out.println("*           RESERVAS DE AULAS DEL IES AL-ÁNDALUS            *");
		System.out.println("*************************************************************");
		int i = 0;
		for (Opcion o: Opcion.values()) {
			System.out.printf("* %-2d- %-54s*\n", i, o.getMensaje());
			i++;
		}
		System.out.println("*************************************************************");
	}

	/**
	 * Imprime por pantalla el título de la opción seleccionada.
	 * @param cabecera el título de la opción
	 */
	public static void mostrarCabecera(String cabecera) {
		System.out.println(cabecera);
	}

	/**
	 * Lee por consola el valor ordinal de la opción que desea realizarse.
	 * @return el valor ordinal de la opción a realizar
	 */
	public static int elegirOpcion() {
		int opcion;
		do {
			System.out.print("¿Qué opción desea elegir?:");
			opcion = Entrada.entero();
		} while(!Opcion.esOrdinalValido(opcion));
		return opcion;
	}

	/**
	 * Lee por consola el nombre de un aula y la crea.
	 * @return el aula leída
	 */
	public static Aula leerAula() {
		Aula leida = null;
		do {
			try {
				leida = new Aula(leerNombreAula());
				System.out.println("Aula leída correctamente.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while(leida==null);
		return leida;
	}

	/**
	 * Lee por consola el nombre de un aula.
	 * @return el nombre leído
	 */
	public static String leerNombreAula() {
		String nombre;
		do {
			System.out.print("Introduzca el nombre del aula:");
			nombre = Entrada.cadena();
		}while(nombre.equals(""));
		return nombre;
	}

	/**
	 * Lee por consola todos los atributos de un profesor y lo crea.
	 * @return el profesor leído
	 */
	public static Profesor leerProfesor() {
		Profesor leido = null;
		do {
			try {
				String nombre = leerNombreProfesor();
				System.out.print("Introduzca el correo electrónico del profesor:");
				String correo = Entrada.cadena();
				System.out.print("Introduzca el teléfono del profesor (Puede dejarse vacío):");
				String telefono = Entrada.cadena();
				if(telefono.equals(""))
					leido = new Profesor(nombre, correo);
				else
					leido = new Profesor(nombre, correo, telefono);
				System.out.println("Profesor leído correctamente.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while(leido==null);
		return leido;
	}

	/**
	 * Lee por consola el nombre de un profesor.
	 * @return el nombre leído
	 */
	public static String leerNombreProfesor() {
		String nombre;
		do {
			System.out.print("Introduzca el nombre del profesor:");
			nombre = Entrada.cadena();
		} while(nombre.equals(""));
		return nombre;
	}

	/**
	 * Lee por consola un tramo. En esta versión sólo están las opciones de mañana (0) y de tarde (1)
	 * Si añadimos más opciones a Tramo
	 * será necesario cambiar la salida por pantalla, pero no el bucle ni el return.
	 * @return el tramo horario leído
	 */
	public static Tramo leerTramo() {
		int opcion;
		do {
			System.out.print("¿Tramo de mañana(0) o de tarde(1)?:");
			opcion = Entrada.entero();
		} while(opcion<0 || opcion>Tramo.values().length);
		return Tramo.values()[opcion];

	}

	/**
	 * Lee una fecha con un formato específico.
	 * @return la fecha leída
	 */
	public static LocalDate leerDia() {
		LocalDate leido = null;
		do {
			System.out.print("Introduzca una fecha en el formato dd/mm/aaaa: ");
			try {
				leido = LocalDate.parse(Entrada.cadena(), FORMATO_DIA);
			} catch (DateTimeParseException e) {
				System.out.println("La fecha introducida no está en el formato correcto o no es válida.");
			}
		} while (leido == null);
		return leido;
	}

}
