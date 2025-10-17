import java.util.LinkedList;

public class HashTableEncadenamiento {
    
    // Cada posición del arreglo contendrá una lista (encadenamiento)
    private LinkedList<Elemento>[] tabla;
    private int tamaño;
    
    // Clase interna para representar los pares clave-valor
    private static class Elemento {
        String clave;
        String valor;
        
        Elemento(String clave, String valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }
    
    // Constructor
    public HashTableEncadenamiento(int tamaño) {
        this.tamaño = tamaño;
        tabla = new LinkedList[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new LinkedList<>();
        }
    }
    
    // Función hash simple
    private int hash(String clave) {
        return Math.abs(clave.hashCode() % tamaño);
    }
    
    // Insertar clave-valor
    public void insertar(String clave, String valor) {
        int indice = hash(clave);
        for (Elemento elem : tabla[indice]) {
            if (elem.clave.equals(clave)) {
                elem.valor = valor; // Actualiza si ya existe
                return;
            }
        }
        tabla[indice].add(new Elemento(clave, valor));
    }
    
    // Buscar valor por clave
    public String buscar(String clave) {
        int indice = hash(clave);
        for (Elemento elem : tabla[indice]) {
            if (elem.clave.equals(clave)) {
                return elem.valor;
            }
        }
        return null;
    }
    
    // Eliminar por clave
    public void eliminar(String clave) {
        int indice = hash(clave);
        tabla[indice].removeIf(elem -> elem.clave.equals(clave));
    }
    
    // Mostrar contenido de la tabla
    public void mostrarTabla() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Índice " + i + ": ");
            for (Elemento elem : tabla[i]) {
                System.out.print("[" + elem.clave + " -> " + elem.valor + "] ");
            }
            System.out.println();
        }
    }
    
    // Método principal para probar
    public static void main(String[] args) {
        HashTableEncadenamiento tabla = new HashTableEncadenamiento(5);
        
        tabla.insertar("Juan", "1234");
        tabla.insertar("Pedro", "5678");
        tabla.insertar("Ana", "91011");
        tabla.insertar("Maria", "1213");
        
        tabla.mostrarTabla();
        
        System.out.println("\nBuscar 'Pedro': " + tabla.buscar("Pedro"));
        
        tabla.eliminar("Juan");
        
        System.out.println("\nDespués de eliminar 'Juan':");
        tabla.mostrarTabla();
    }
}