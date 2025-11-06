package co.edu.poli.actividad.vista;

import co.edu.poli.actividad.modelo.Moneda;
import co.edu.poli.actividad.modelo.Conservacion;
import co.edu.poli.actividad.modelo.Especificacion;
import co.edu.poli.actividad.modelo.Coleccion;
import co.edu.poli.actividad.modelo.Pais;
import co.edu.poli.actividad.modelo.Certificacion;
import co.edu.poli.actividad.modelo.Conmemorativa;
import co.edu.poli.actividad.modelo.MonedaGenerica;
import co.edu.poli.actividad.servicios.Implementacion_Operacion_CRUD;
import java.util.Scanner;

/**
 * @author Jhosayde
 */
public class Principal {

    public static void main(String[] args) {
        
        Implementacion_Operacion_CRUD crud = new Implementacion_Operacion_CRUD();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n====== MENÚ CRUD MONEDAS ======");
            System.out.println("1. Crear moneda");
            System.out.println("2. Leer moneda por ID");
            System.out.println("3. Actualizar moneda");
            System.out.println("4. Eliminar moneda");
            System.out.println("5. Listar todas las monedas");
            System.out.println("6. Serializar (Guardar en archivo)");
            System.out.println("7. Deserializar (Cargar desde archivo)");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);
            sc.nextLine();

            switch (opcion) {

                case 1:
                    crearMoneda(crud, sc);
                    break;

                case 2:
                    leerMoneda(crud, sc);
                    break;

                case 3:
                    actualizarMoneda(crud, sc);
                    break;

                case 4:
                    eliminarMoneda(crud, sc);
                    break;

                case 5:
                    crud.listar();
                    break;

                case 6:
                    crud.serializar();
                    System.out.println("Datos guardados correctamente.");
                    break;

                case 7:
                    crud.deserializar();
                    System.out.println("Datos cargados correctamente.");
                    break;

                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 8);

        sc.close();
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Error: Debes ingresar un número.");
            System.out.print("Intenta de nuevo: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double leerDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Error: Debes ingresar un número.");
            System.out.print("Intenta de nuevo: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    private static void crearMoneda(Implementacion_Operacion_CRUD crud, Scanner sc) {
        System.out.println("\n=== Crear Moneda ===");

        // Solo datos esenciales de la moneda
        System.out.print("ID de la moneda: ");
        String id = sc.nextLine();

        System.out.print("Valor: ");
        double valor = leerDouble(sc);
        sc.nextLine();

        System.out.print("Año de creación: ");
        int annio = leerEntero(sc);
        sc.nextLine();

        System.out.print("Nombre del país: ");
        String nombrePais = sc.nextLine();

        // Crear objetos con valores predeterminados
        Pais pais = new Pais("N/A", "N/A", "N/A", "N/A", nombrePais);
        Especificacion especificacion = new Especificacion("ESP-" + id, 0.0, "N/A", 0.0, "N/A");
        Coleccion coleccion = new Coleccion("COL-" + id, "Sin colección", "N/A", "N/A");
        Conservacion conservacion = new Conservacion("CONS-" + id, false, false, "N/A", "N/A", "N/A");
        Certificacion certificacion = new Certificacion("CERT-" + id, 0, "N/A", "N/A", "N/A");

        // Preguntar tipo de moneda
        System.out.print("¿Es una moneda conmemorativa? (s/n): ");
        String tipo = sc.nextLine();

        Moneda nueva;
        if (tipo.equalsIgnoreCase("s")) {
            System.out.print("Motivo de conmemoración: ");
            String motivo = sc.nextLine();
            
            System.out.print("Año del evento: ");
            int annioEvento = leerEntero(sc);
            sc.nextLine();
            
            nueva = new Conmemorativa(id, valor, pais, especificacion, coleccion, annio, 
                                     conservacion, certificacion, 8000, motivo, annioEvento);
        } else {
            nueva = new MonedaGenerica(id, valor, pais, especificacion, coleccion, annio, 
                                      conservacion, certificacion, 5000);
        }

        crud.crear(nueva);
        
        // IMPRIME EL REGISTRO DE LA MONEDA
        System.out.println("\n" + "=".repeat(50));
        nueva.registrarMoneda();
        System.out.println("=".repeat(50));
        System.out.println("Moneda creada correctamente.");
    }

    private static void leerMoneda(Implementacion_Operacion_CRUD crud, Scanner sc) {
        System.out.println("\n=== Leer Moneda ===");
        System.out.print("Ingrese ID: ");
        String idBuscar = sc.nextLine();
        
        Moneda encontrada = crud.leer(idBuscar);
        if (encontrada != null) {
            System.out.println("Resultado: " + encontrada);
        } else {
            System.out.println("No se encontró una moneda con ese ID.");
        }
    }

    private static void actualizarMoneda(Implementacion_Operacion_CRUD crud, Scanner sc) {
        System.out.println("\n=== Actualizar Moneda ===");
        System.out.print("Ingrese ID de la moneda: ");
        String idActualizar = sc.nextLine();

        Moneda existente = crud.leer(idActualizar);
        if (existente == null) {
            System.out.println("No se encontró ninguna moneda con ese ID.");
            return;
        }

        System.out.println("Moneda encontrada. Ingrese nuevos datos:");
        System.out.print("Nuevo valor: ");
        double nuevoValor = leerDouble(sc);
        sc.nextLine();
        
        System.out.print("Nuevo año de creación: ");
        int nuevoAnio = leerEntero(sc);
        sc.nextLine();
        
        existente.setValor(nuevoValor);
        existente.setAnnyo_creacion(nuevoAnio);
        crud.actualizar(idActualizar, existente);
        System.out.println("Moneda actualizada correctamente.");
    }

    private static void eliminarMoneda(Implementacion_Operacion_CRUD crud, Scanner sc) {
        System.out.println("\n=== Eliminar Moneda ===");
        System.out.print("Ingrese ID a eliminar: ");
        String idEliminar = sc.nextLine();
        crud.eliminar(idEliminar);
        System.out.println("Moneda eliminada correctamente.");
    }
}