import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MezclaDirecta {

    public static void main(String[] args) {
        // Nombre del archivo a ordenar
        String nombreArchivoOriginal = "datos_originales.txt";
        
        // Crear un archivo de ejemplo con números desordenados
        crearArchivoDeEjemplo(nombreArchivoOriginal, 20);

        System.out.println("--- Contenido Original del Archivo ---");
        leerArchivo(nombreArchivoOriginal);

        // Llamar al método de ordenamiento
        ordenamientoPorMezclaDirecta(nombreArchivoOriginal);

        System.out.println("\n--- Contenido del Archivo Ordenado ---");
        leerArchivo(nombreArchivoOriginal);
    }

    public static void ordenamientoPorMezclaDirecta(String nombreArchivo) {
        int tamanoBloque = 1;
        int numeroDeRegistros = contarRegistros(nombreArchivo);

        while (tamanoBloque < numeroDeRegistros) {
            // Nombres para los archivos auxiliares
            String aux1 = "auxiliar1.txt";
            String aux2 = "auxiliar2.txt";

            // Fase de partición
            particionar(nombreArchivo, aux1, aux2, tamanoBloque);

            // Fase de fusión
            fusionar(nombreArchivo, aux1, aux2, tamanoBloque);

            System.out.println("\nBloque de " + tamanoBloque + " registros fusionado.");
            leerArchivo(nombreArchivo);


            tamanoBloque *= 2;
        }
    }

    private static void particionar(String nombreOriginal, String nombreAux1, String nombreAux2, int tamanoBloque) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreOriginal));
             BufferedWriter escritorAux1 = new BufferedWriter(new FileWriter(nombreAux1));
             BufferedWriter escritorAux2 = new BufferedWriter(new FileWriter(nombreAux2))) {

            String linea;
            boolean esParaAux1 = true;
            int contador = 0;

            while ((linea = lector.readLine()) != null) {
                if (esParaAux1) {
                    escritorAux1.write(linea + "\n");
                } else {
                    escritorAux2.write(linea + "\n");
                }
                contador++;
                if (contador == tamanoBloque) {
                    esParaAux1 = !esParaAux1;
                    contador = 0;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la partición: " + e.getMessage());
        }
    }

    private static void fusionar(String nombreOriginal, String nombreAux1, String nombreAux2, int tamanoBloque) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreOriginal));
             BufferedReader lectorAux1 = new BufferedReader(new FileReader(nombreAux1));
             BufferedReader lectorAux2 = new BufferedReader(new FileReader(nombreAux2))) {

            String lineaAux1 = lectorAux1.readLine();
            String lineaAux2 = lectorAux2.readLine();
            
            while (lineaAux1 != null || lineaAux2 != null) {
                List<Integer> bloque1 = new ArrayList<>();
                List<Integer> bloque2 = new ArrayList<>();

                // Leer bloque de aux1
                for (int i = 0; i < tamanoBloque && lineaAux1 != null; i++) {
                    bloque1.add(Integer.parseInt(lineaAux1));
                    lineaAux1 = lectorAux1.readLine();
                }

                // Leer bloque de aux2
                for (int i = 0; i < tamanoBloque && lineaAux2 != null; i++) {
                    bloque2.add(Integer.parseInt(lineaAux2));
                    lineaAux2 = lectorAux2.readLine();
                }

                // Fusionar los dos bloques
                List<Integer> bloqueFusionado = new ArrayList<>();
                int i = 0, j = 0;
                while (i < bloque1.size() && j < bloque2.size()) {
                    if (bloque1.get(i) <= bloque2.get(j)) {
                        bloqueFusionado.add(bloque1.get(i++));
                    } else {
                        bloqueFusionado.add(bloque2.get(j++));
                    }
                }
                while (i < bloque1.size()) {
                    bloqueFusionado.add(bloque1.get(i++));
                }
                while (j < bloque2.size()) {
                    bloqueFusionado.add(bloque2.get(j++));
                }
                
                // Escribir el bloque fusionado en el archivo original
                for(Integer num : bloqueFusionado) {
                    escritor.write(num + "\n");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error en la fusión: " + e.getMessage());
        }
    }

    private static int contarRegistros(String nombreArchivo) {
        int contador = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            while (lector.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar registros: " + e.getMessage());
        }
        return contador;
    }

    public static void crearArchivoDeEjemplo(String nombreArchivo, int cantidadNumeros) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            List<Integer> numeros = new ArrayList<>();
            for (int i = 0; i < cantidadNumeros; i++) {
                numeros.add((int) (Math.random() * 100));
            }
            for (Integer numero : numeros) {
                escritor.write(numero.toString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de ejemplo: " + e.getMessage());
        }
    }
    
    public static void leerArchivo(String nombreArchivo){
        try(BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            System.out.print("[ ");
            while((linea = lector.readLine()) != null){
                System.out.print(linea + " ");
            }
            System.out.println("]");
        } catch(IOException e){
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
