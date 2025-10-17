public class ListaInvertida {

    // Clase interna que representa un nodo
    static class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Clase para manejar la lista enlazada
    static class Lista {
        Nodo cabeza;

        // Insertar elemento al inicio
        public void insertarInicio(int dato) {
            Nodo nuevo = new Nodo(dato);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }

        // Insertar elemento al final
        public void insertarFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Nodo actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
        }

        // Mostrar lista
        public void mostrar() {
            Nodo actual = cabeza;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }

        // Invertir la lista
        public void invertir() {
            Nodo anterior = null;
            Nodo actual = cabeza;
            Nodo siguiente = null;

            while (actual != null) {
                siguiente = actual.siguiente; // guarda el siguiente
                actual.siguiente = anterior;   // invierte el enlace
                anterior = actual;             // avanza anterior
                actual = siguiente;            // avanza actual
            }
            cabeza = anterior; // nueva cabeza
        }
    }

    // Método principal
    public static void main(String[] args) {
        Lista lista = new Lista();

        // Insertar elementos
        lista.insertarFinal(10);
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        lista.insertarFinal(40);
        lista.insertarFinal(50);

        System.out.println("Lista original:");
        lista.mostrar();

        // Invertir la lista
        lista.invertir();

        System.out.println("\nLista invertida:");
        lista.mostrar();
    }
}