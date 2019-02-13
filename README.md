# Tarea Reservas de Aulas
## Profesor: Andr�s Rubio del R�o
## Alumno: Juan Antonio Manzano Plaza

Desde el IES Al-�ndalus nos acaban de comentar que por favor eliminemos la restricci�n de tama�o en los datos de la aplicaci�n. Por lo que decidimos utilizar estructuras din�micas de datos, en concreto Listas. Para las diferentes clases del modelo que contienen las colecciones de objetos del dominio (las que est�n incluidas en el paquete **dao**) deberemos sustituir los **Array** por **ArrayList** y, c�mo no, ajustar los diferentes m�todos para que sigan haciendo lo mismo que antes, pero utilizando las nuevas estructuras de datos. Como observar�s, los m�todos privados que ten�amos antes desaparecen ya que ahora no ser�n necesarios.

El diagrama de clases queda como te muestro a continuaci�n y poco a poco te ir� explicando los diferentes pasos a realizar:

![Diagrama de clases para reservasaulas](src/main/resources/reservasAulas.png)

He subido a GitHub un esqueleto de proyecto gradle que ya lleva incluidos todos los test necesarios que el programa debe pasar. Dichos test est�n todos comentados y deber�s ir descoment�ndolos conforme vayas avanzando con la tarea. La URL del repositorio es en la que te encuentras.

Por tanto, tu tarea va a consistir en completar los siguientes apartados:

1. Lo primero que debes hacer es realizar un fork del repositorio donde he colocado el proyecto gradle con la estructura del proyecto y todos los test necesarios.
2. Clona tu repositorio remoto reci�n copiado en github a un repositorio local que ser� donde ir�s realizando lo que a continuaci�n se te pide. A�ade tu nombre al fichero `README.md` en el apartado "Alumno". Haz tu primer commit.
3. Modifica la clase `Aulas` para que utilice un `ArrayList` en vez de un `Array`. Modifica tambi�n los m�todos, eliminando los necesarios, para que sigan haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el m�todo `representar` ahora tambi�n devuelve una lista. Haz un commit.
4. Modifica la clase `Profesores` para que utilice un `ArrayList` en vez de un `Array`. Modifica tambi�n los m�todos, eliminando los necesarios, para que sigan haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el m�todo `representar` ahora tambi�n devuelve una lista. Haz un commit.
5. Modifica la clase `Reservas` para que utilice un `ArrayList` en vez de un `Array`. Modifica tambi�n los m�todos, eliminando los necesarios, para que sigan haciendo lo mismo pero utilizando esta estructura. Ten en cuenta que el m�todo `representar` ahora tambi�n devuelve una lista. Haz un commit.
6. Haz los ajustes necesarios en la clase `IUTextual` para que todo siga funcionando igual. Haz un commit.



###### Se valorar�:
- La nomenclatura del repositorio de GitHub y del archivo entregado sigue las indicaciones de entrega.
- La indentaci�n debe ser correcta en cada uno de los apartados.
- El nombre de las variables debe ser adecuado.
- Se debe utilizar la clase `Entrada` para realizar la entrada por teclado.
- El proyecto debe pasar todas las pruebas que van en el esqueleto del mismo y toda entrada del programa ser� validada para evitar que el programa termine abruptamente debido a una excepci�n.
- Se deben utilizar los comentarios adecuados.
- Se valorar� la correcci�n ortogr�fica tanto en los comentarios como en los mensajes que se muestren al usuario.

