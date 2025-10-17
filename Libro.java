package indiceinvertido;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private final String titulo;
    private final List<String> palabras; // lista de palabras del libro

    public Libro(String titulo, String contenido) {
        this.titulo = titulo;
        this.palabras = procesarContenido(contenido);
    }

    // Convierte el texto del libro en una lista de palabras
    private List<String> procesarContenido(String contenido) {
        String[] tokens = contenido.toLowerCase().replaceAll("[^a-záéíóúñ ]", "").split("\\s+");
        List<String> lista = new ArrayList<>();
        for (String palabra : tokens) {
            if (!palabra.isEmpty()) {
                lista.add(palabra);
            }
        }
        return lista;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getPalabras() {
        return palabras;
    }
}
