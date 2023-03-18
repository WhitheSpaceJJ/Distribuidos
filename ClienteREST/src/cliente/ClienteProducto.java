package cliente;

import entidades.Producto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class ClienteProducto {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/EjemploREST/webresources";

    public ClienteProducto() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto");
    }

    public void close() {
        client.close();
    }

    public boolean eliminarProducto(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        Producto productoEliminado = null;
        try {
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            productoEliminado = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).delete(Producto.class);
            if (productoEliminado != null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean agregarProducto(Producto productoAgregar) throws ClientErrorException {
        WebTarget resource = webTarget;
        Producto productoAgregado = null;
        try {
            productoAgregado = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).
                    post(javax.ws.rs.client.Entity.
                            entity(productoAgregar, javax.ws.rs.core.MediaType.APPLICATION_JSON),
                            Producto.class);
            if(productoAgregado!=null){
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
            return false;
        }
        return false;

    }

    public Producto actualizarProducto(Producto productoActualizar, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        Producto productoActualizado = null;
        try {
            resource = resource.path(java.text.MessageFormat.format("{0}",
                    new Object[]{id}));
            productoActualizado = resource.request(
                    javax.ws.rs.core.MediaType.APPLICATION_JSON).
                    put(javax.ws.rs.client.Entity.entity(productoActualizar,
                            javax.ws.rs.core.MediaType.APPLICATION_JSON), Producto.class);

        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
            return null;
        }
        return productoActualizado;
    }

    public Producto obtenerProductoPorID(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        Producto productoID = null;
        try {
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            productoID = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Producto.class);
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
            return null;
        }
        return productoID;
    }

    public List<Producto> obtenerProductos() throws ClientErrorException {
        WebTarget resource = webTarget;
        List<Producto> listaProductos = null;
        try {
            Producto[] productos = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Producto[].class);
            listaProductos = Arrays.asList(productos);
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
            return null;
        }
        return listaProductos;
    }

}
