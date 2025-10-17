package AlgoritmoBusquedaPorBloques;

import java.util.Scanner;

public class BusquedaPorBloques {

    // metodo de busqueda por bloques
    static int jumpSearch(int arr[], int n, int key) {
        int paso = (int) Math.sqrt(n); // tamaño del bloque
        int prev = 0;

        // saltar bloques hasta encontrar el bloque posible
        while (arr[Math.min(paso, n) - 1] < key) {
            prev = paso;
            paso += (int) Math.sqrt(n);
            if (prev >= n)
                return -1;
        }

        // Búsqueda lineal dentro del bloque encontrado
        while (arr[prev] < key) {
            prev++;
            if (prev == Math.min(paso, n))
                return -1;
        }

        // si se encontr el elemento
        if (arr[prev] == key)
            return prev;

        return -1;
    }

    // metodo principal
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int[] arr = {0, 6, 12, 14, 19, 22, 45, 66, 79, 88, 104, 126, 150, 151, 153, 154};
            int n = arr.length;
            
            System.out.println("Los elementos del arreglo son:");
            for (int x : arr)
                System.out.print(x + " ");
            System.out.println("\n");
            
            System.out.print("Ingrese el numero que desea buscar: ");
            int key = sc.nextInt();
            
            int index = jumpSearch(arr, n, key);
            
            if (index != -1)
                System.out.println("✅ El elemento " + key + " se encontro en la posición: " + (index + 1));
            else
                System.out.println("❌ Búsqueda fallida. El elemento no está en el arreglo.");
        }
    }
}