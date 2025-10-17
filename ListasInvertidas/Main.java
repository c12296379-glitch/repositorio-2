package indiceinvertido;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //crear algunos libros con contenido simulado
        Libro libro1 = new Libro("Libro1", "Java es un lenguaje de programación orientado a objetos.");
        Libro libro2 = new Libro("Libro2", "Python es un lenguaje de programación muy popular.");
        Libro libro3 = new Libro("Libro3", "Java y Python son usados para crear algoritmos y sistemas.");

        // guardar los libros en una lista
        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(libro1);
        listaLibros.add(libro2);
        listaLibros.add(libro3);

        // crear el índice invertido
        IndiceInvertido indice = new IndiceInvertido();
        indice.construirIndice(listaLibros);

        // mostrar todo el índice
        indice.mostrarIndice();

        // buscar palabras
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n busqueda de palabras (escribe 'salir' para terminar)");
        while (true) {
            System.out.print("buscar palabra: ");
            String palabra = entrada.nextLine();
            if (palabra.equalsIgnoreCase("salir")) {
                break;
            }
            indice.buscarPalabra(palabra);
        }

        System.out.println("\nPrograma finalizado ");
    }
}
