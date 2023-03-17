/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import entidades.Producto;
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("producto")
public class ProductoResource {

    @Context
    private UriInfo context;
    private static final List<Producto> lista = new ArrayList<>();
    private static int relleno = 0;

    /**
     * Creates a new instance of ProductoResource
     */
    public ProductoResource() {
        if (relleno == 0) {
            for (int i = 0; i < 10; i++) {
                Producto get = new Producto(String.valueOf(i), "Producto " + i);
                lista.add(get);
            }
            relleno = 1;
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProductoPorID(@PathParam("id") String id) {
        for (Producto producto : lista) {
            if (producto.getId().equals(id)) {
                return Response.ok().entity(producto).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProductos() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Producto producto : lista) {
            JsonObjectBuilder productoJsonBuilder = Json.createObjectBuilder()
                    .add("id", producto.getId())
                    .add("nombre", producto.getNombre());
            jsonArrayBuilder.add(productoJsonBuilder.build());
        }
        JsonArray jsonArray = jsonArrayBuilder.build();
        return Response.ok().entity(jsonArray.toString()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(@PathParam("id") String id, Producto productoActualizado) {
        for (Producto producto : lista) {
            if (producto.getId().equals(id)) {
                producto.setNombre(productoActualizado.getNombre());
                return Response.status(Response.Status.OK).entity(producto).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(@PathParam("id") String id) {
        for (Producto producto : lista) {
            if (producto.getId().equals(id)) {
                Producto productoElinminado=producto;
                lista.remove(producto);
                return Response.status(Response.Status.GONE).entity(productoElinminado).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarProducto(Producto producto) {
        if (producto != null) {
            Producto nuevoProducto = new Producto(producto.getId(), producto.getNombre());
            lista.add(nuevoProducto);
            return Response.status(Response.Status.CREATED).entity(nuevoProducto).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }
}
