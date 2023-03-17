package ws;

import conexion.ConexionBD;
import datos.ProductosDAO;
import datosInterfaces.IProductosDAO;
import entidades.Producto;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("producto")
public class ProductoResource {

    @Context
    private UriInfo context;
    private IProductosDAO productosDAO;

    public ProductoResource() {
        productosDAO = new ProductosDAO(new ConexionBD());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProductoPorID(@PathParam("id") String id) {
        Producto productoABuscar = null;
        try {
            productoABuscar = productosDAO.consultar(Integer.parseInt(id));
            if (productoABuscar == null) {
                return Response.ok().entity(productoABuscar).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error; " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProductos() {
        List<Producto> listaProductos = null;
        try {
            listaProductos = productosDAO.consultarTodos();
            if (listaProductos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                for (Producto producto : listaProductos) {
                    JsonObjectBuilder productoJsonBuilder = Json.createObjectBuilder()
                            .add("id", producto.getId())
                            .add("nombre", producto.getNombre());
                    jsonArrayBuilder.add(productoJsonBuilder.build());
                }
                JsonArray jsonArray = jsonArrayBuilder.build();
                return Response.ok().entity(jsonArray.toString()).build();
            }
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(@PathParam("id") String id, Producto productoActualizado) {
        try {
            Producto actualizar = productosDAO.consultar(Integer.parseInt(id));
            if (actualizar == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                productosDAO.actualizar(productoActualizado);
                return Response.status(Response.Status.OK).entity(actualizar).build();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error; " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(@PathParam("id") String id) {
        try {
            Producto eliminar = productosDAO.consultar(Integer.parseInt(id));
            if (eliminar == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                productosDAO.eliminar(Integer.parseInt(id));
                return Response.status(Response.Status.OK).entity(eliminar).build();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error; " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarProducto(Producto producto) {
        try {
            System.out.println(producto.toString());
            boolean productoAgregar = productosDAO.agregar(producto);
            if (productoAgregar) {
                return Response.status(Response.Status.OK).entity(producto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            System.out.println("Error; " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
