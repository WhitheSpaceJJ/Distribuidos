package prueba;

import cliente.ClienteProducto;
import entidades.Producto;
import java.util.List;
import java.util.Scanner;

public class Prueba {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        ClienteProducto productosDAO = new ClienteProducto();
        do {
            System.out.println("|---------- Menu de Cliente REST ---------|");
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Actualizar Producto");
            System.out.println("3. Eliminar Prodicuto");
            System.out.println("4. Consultar Producto ID");
            System.out.println("5. Consultar Productos");
            System.out.println("6. Consulta Especial");
            System.out.println("0. Salir");
            System.out.println("|-------------------------------------------------|");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Ingreso de producto");
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Ingrese el nombre del producto:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la descripcon del producto:");
                    String descripcion = scanner.nextLine();
                    System.out.println("Ingrese la marca del producto:");
                    String marca = scanner.nextLine();
                    Producto productoNuevo = new Producto(nombre, descripcion, marca);
                    boolean agregar = productosDAO.agregarProducto(productoNuevo);
                    if (agregar) {
                        System.out.println("Producto agregado correctamente.");
                    } else {
                        System.out.println("El producto no se pudo agregar correctamente");
                    }
                    break;
                case 2:
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Actualizacion de Producto");
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Ingrese el ID del producto a actualizar:");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    Producto productoActualizar = productosDAO.obtenerProductoPorID(String.valueOf(idActualizar));
                    if (productoActualizar != null) {
                        System.out.println("Ingrese el nuevo nombre del producto:");
                        productoActualizar.setNombre(scanner.nextLine());
                        System.out.println("Ingrese la nueva descripcion del producto:");
                        productoActualizar.setDescripcion(scanner.nextLine());
                        System.out.println("Ingrese la nueva marca del producto:");
                        productoActualizar.setMarca(scanner.nextLine());
                        productosDAO.actualizarProducto(productoActualizar, String.valueOf(idActualizar));
                        System.out.println("Producto actualizado correctamente.");
                    } else {
                        System.out.println("El producto con ID " + idActualizar + " no existe.");
                    }
                    break;
                case 3:
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Eliminacion de Producto");
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Ingrese el ID del producto a eliminar:");
                    int idEliminar = scanner.nextInt();
                    boolean eliminar = productosDAO.eliminarProducto(String.valueOf(idEliminar));
                    if (eliminar) {
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar el producto Correctamente");
                    }
                    break;
                case 4:
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Consulta por ID de Producto");
                    System.out.println("|-------------------------------------------------|");

                    System.out.println("Ingrese el ID del producto a consultar:");
                    int idConsultar = scanner.nextInt();
                    Producto productoConsultar = productosDAO.obtenerProductoPorID(String.valueOf(idConsultar));
                    if (productoConsultar != null) {
                        System.out.println("ID; " + productoConsultar.getId() + ", Nombre; " + productoConsultar.getNombre() + ", Marca ; " + productoConsultar.getMarca());
                        System.out.println("Descripcion; " + productoConsultar.getDescripcion());
                    } else {
                        System.out.println("El producto con ID " + idConsultar + " no existe.");
                    }
                    break;
                case 5:
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Consulta de Productos ");
                    System.out.println("|-------------------------------------------------|");
                    List<Producto> productos = productosDAO.obtenerProductos();
                    if (productos == null) {
                        System.out.println("No se encontraron productos.");
                    } else {
                        System.out.println("Listado de productos:");
                        for (Producto producto : productos) {
                            System.out.println("ID; " + producto.getId() + ", Nombre; " + producto.getNombre() + ", Marca ; " + producto.getMarca());
                            System.out.println("Descripcion; " + producto.getDescripcion());
                        }
                    }
                    break;

                case 6:
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Consulta Especial de Productos");
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Seleccione una opcion de busqueda:");
                    System.out.println("1. Buscar por ID");
                    System.out.println("2. Buscar por nombre");
                    System.out.println("3. Buscar por marca");
                    System.out.println("4. Buscar por descripcion");
                    int opcionBusqueda = scanner.nextInt();
                    scanner.nextLine();
                    boolean salir = true;
                    String nombre2 = null;
                    String marca2 = null;
                    String descripcion2 = null;
                    int idConsultar2 = 0;
                    do {
                        switch (opcionBusqueda) {
                            case 1 -> {
                                System.out.println("Ingrese el ID del producto a consultar:");
                                idConsultar2 = scanner.nextInt();
                                salir = false;
                            }
                            case 2 -> {
                                System.out.println("Ingrese el nombre del producto:");
                                nombre2 = scanner.nextLine();
                                salir = false;
                            }
                            case 3 -> {
                                System.out.println("Ingrese la marca del producto:");
                                marca2 = scanner.nextLine();
                                salir = false;
                            }
                            case 4 -> {
                                System.out.println("Ingrese la descripcon del producto:");
                                descripcion2 = scanner.nextLine();
                                salir = false;
                            }
                            default ->
                                System.out.println("Opcion invalida. Intente nuevamente.");
                        }
                    } while (salir);
                    String[] consultas = new String[4];
                    consultas[0] = String.valueOf(idConsultar2);
                    consultas[1] = nombre2;
                    consultas[2] = descripcion2;
                    consultas[3] = marca2;
                    System.out.println("|-------------------------------------------------|");
                    System.out.println("Consulta de Productos Especial ");
                    System.out.println("|-------------------------------------------------|");
                    List<Producto> productosEspecial = productosDAO.obtenerProductosEspecial(consultas);
                    if (productosEspecial == null) {
                        System.out.println("No se encontraron productos.");
                    } else {
                        System.out.println("Listado de productos:");
                        for (Producto producto : productosEspecial) {
                            System.out.println("ID; " + producto.getId() + ", Nombre; " + producto.getNombre() + ", Marca ; " + producto.getMarca());
                            System.out.println("Descripcion; " + producto.getDescripcion());
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

}
