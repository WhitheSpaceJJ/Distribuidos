/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebaDAO;

import conexion.ConexionBD;
import datos.ProductosDAO;
import datosInterfaces.IProductosDAO;
import entidades.Producto;
import java.util.List;
import java.util.Scanner;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        IProductosDAO productosDAO = new ProductosDAO(new ConexionBD());
        do {
            System.out.println("|---------- Menú de Prueba Productos DAO ---------|");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Actualizar Producto");
            System.out.println("3. Eliminar Prodicuto");
            System.out.println("4. Consultar Producto ID");
            System.out.println("5. Consultar Productos");
            System.out.println("0. Salir");
            System.out.println("|------------------------------------|");

            opcion = scanner.nextInt();
                       scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del producto:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la descripción del producto:");
                    String descripcion = scanner.nextLine();
                    System.out.println("Ingrese la marca del producto:");
                    String marca = scanner.nextLine();
                    Producto productoNuevo = new Producto(nombre, descripcion, marca);
                    productosDAO.agregar(productoNuevo);
                    System.out.println("Producto agregado correctamente.");
                    break;
                case 2:
                    System.out.println("Ingrese el ID del producto a actualizar:");
                    int idActualizar = scanner.nextInt();
                    Producto productoActualizar = productosDAO.consultar(idActualizar);
                    if (productoActualizar != null) {
                        System.out.println("Ingrese el nuevo nombre del producto:");
                        productoActualizar.setNombre(scanner.nextLine());
                        System.out.println("Ingrese la nueva descripción del producto:");
                        productoActualizar.setDescripcion(scanner.nextLine());
                        System.out.println("Ingrese la nueva marca del producto:");
                        productoActualizar.setMarca(scanner.nextLine());
                        productosDAO.actualizar(productoActualizar);
                        System.out.println("Producto actualizado correctamente.");
                    } else {
                        System.out.println("El producto con ID " + idActualizar + " no existe.");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el ID del producto a eliminar:");
                    int idEliminar = scanner.nextInt();
                    productosDAO.eliminar(idEliminar);
                    System.out.println("Producto eliminado correctamente.");
                    break;
                case 4:
                    System.out.println("Ingrese el ID del producto a consultar:");
                    int idConsultar = scanner.nextInt();
                    Producto productoConsultar = productosDAO.consultar(idConsultar);
                    if (productoConsultar != null) {
                        System.out.println(productoConsultar.toString());
                    } else {
                        System.out.println("El producto con ID " + idConsultar + " no existe.");
                    }
                    break;
                case 5:
                    List<Producto> productos = productosDAO.consultarTodos();
                    if (productos.isEmpty()) {
                        System.out.println("No se encontraron productos.");
                    } else {
                        System.out.println("Listado de productos:");
                        for (Producto producto : productos) {
                            System.out.println(producto);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

}
