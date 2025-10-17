package indiceinvertido;

import java.util.*;

public class IndiceInvertido {

    // mapa donde cada palabra tiene una lista de libros en los que aparece
    private Map<String, List<String>> indice;

    public IndiceInvertido() {
        indice = new HashMap<>();
    }

    // construye el índice invertido a partir de una lista de libros
    public void construirIndice(List<Libro> libros) {
        for (Libro libro : libros) {
            String nombreLibro = libro.getTitulo();
            for (String palabra : libro.getPalabras()) {
                // Si la palabra no existe, se crea una nueva entrada
                if (!indice.containsKey(palabra)) {
                    indice.put(palabra, new ArrayList<>());
                }

                // Evitamos duplicar el nombre del libro
                if (!indice.get(palabra).contains(nombreLibro)) {
                    indice.get(palabra).add(nombreLibro);
                }
            }
        }
    }

    // muestra todo el índice invertido
    public void mostrarIndice() {
        System.out.println("\n===== ÍNDICE INVERTIDO =====");
        for (String palabra : indice.keySet()) {
            System.out.println(palabra + " → " + indice.get(palabra));
        }
    }

    // busca una palabra y muestra en qué libros aparece
    public void buscarPalabra(String palabra) {
        palabra = palabra.toLowerCase();
        if (indice.containsKey(palabra)) {
            System.out.println("\n📚 La palabra '" + palabra + "' se encuentra en: " + indice.get(palabra));
        } else {
            System.out.println("\n❌ La palabra '" + palabra + "' no se encuentra en ningún libro.");
        }
    }
}
