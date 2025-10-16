package busquedainterna;

import java.util.Scanner;

public class BusquedaInterna {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Lista ordenada (importante para búsqueda binaria)
        int[] numeros = {2, 4, 6, 8, 10, 12, 14};

        System.out.print("Ingrese el numero que desea buscar: ");
        int valorBuscado = sc.nextInt();

        int resultado = busquedaBinaria(numeros, valorBuscado);

        if (resultado != -1) {
            System.out.println("✅ El numero " + valorBuscado + " se encuentra en la posición " + resultado);
        } else {
            System.out.println("❌ El numero no se encuentra en la lista.");
        }
    }

    // Método de búsqueda binaria
    public static int busquedaBinaria(int[] lista, int valor) {
        int inicio = 0;
        int fin = lista.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;

            if (lista[medio] == valor) {
                return medio; // encontrado
            } else if (lista[medio] < valor) {
                inicio = medio + 1; // buscar en la derecha
            } else {
                fin = medio - 1; // buscar en la izquierda
            }
        }

        return -1; // no encontrado
    }
}
