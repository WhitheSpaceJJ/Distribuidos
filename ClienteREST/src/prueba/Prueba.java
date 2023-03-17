/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import cliente.ClienteProducto;
import entidades.Producto;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author josej
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClienteProducto clienteProducto=new ClienteProducto();
        System.out.println("Producto por ID");
        Producto p=clienteProducto.obtenerProductoPorID("0");
        System.out.println(p.toString());
        System.out.println("-----------------------");
        System.out.println("Prueba de Productos");
        List<Producto> productos=Arrays.asList(clienteProducto.obtenerProductos());
        for (int i = 0; i < productos.size(); i++) {
            Producto get = productos.get(i);
            System.out.println(get.toString());
        }
        
    }
    
}
