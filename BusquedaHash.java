package Hash.Busqueda;

public class BusquedaHash {

    static final int TAMANIO = 10; // Tamaño de la tabla hash
    static Dato[] tablaHash = new Dato[TAMANIO]; // Arreglo que representa la tabla

    // Clase interna para representar un dato con su clave
    static class Dato {
        int clave;

        Dato(int clave) {
            this.clave = clave;
        }
    }

    // Función hash (simple)
    static int codigoHash(int clave) {
        return clave % TAMANIO;
    }

    // Insertar un dato en la tabla
    static void insertar(int clave) {
        int indice = codigoHash(clave);
        int intentos = 0;

        // Busca una celda vacía usando exploración lineal
        while (tablaHash[indice] != null && intentos < TAMANIO) {
            indice = (indice + 1) % TAMANIO;
            intentos++;
        }

        if (intentos < TAMANIO) {
            tablaHash[indice] = new Dato(clave);
            System.out.println("Insertado " + clave + " en posición " + indice);
        } else {
            System.out.println(" La tabla está llena, no se pudo insertar " + clave);
        }
    }

    // Buscar un dato por clave
    static Dato buscar(int clave) {
        int indice = codigoHash(clave);
        int intentos = 0;

        // Recorre hasta encontrar la clave o una celda vacía
        while (tablaHash[indice] != null && intentos < TAMANIO) {
            if (tablaHash[indice].clave == clave) {
                System.out.println("Encontrado " + clave + " en posición " + indice);
                return tablaHash[indice];
            }
            indice = (indice + 1) % TAMANIO;
            intentos++;
        }

        System.out.println(" Clave " + clave + " no encontrada.");
        return null;
    }

    // Mostrar el contenido de la tabla hash
    static void mostrarTabla() {
        System.out.println("\n--- Contenido de la tabla hash ---");
        for (int i = 0; i < TAMANIO; i++) {
            if (tablaHash[i] != null)
                System.out.println("[" + i + "] → " + tablaHash[i].clave);
            else
                System.out.println("[" + i + "] → vacío");
        }
    }

    // Método principal para probar
    public static void main(String[] args) {
        insertar(15);
        insertar(25);
        insertar(35);
        insertar(5);
        insertar(12);

        mostrarTabla();

        buscar(25);
        buscar(7);
    }
}