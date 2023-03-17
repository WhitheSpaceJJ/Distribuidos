package datos;

import datosInterfaces.IConexionBD;
import datosInterfaces.IProductosDAO;
import entidades.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProductosDAO implements IProductosDAO {

    private IConexionBD conexion;

    public ProductosDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Producto producto) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible agregar el producto");
            return false;
        }
    }

    @Override
    public boolean actualizar(Producto producto) {
        try {

            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Producto productoBD = em.find(Producto.class, producto.getId());
            em.merge(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible actualizar el producto");
            return false;
        }

    }

    @Override
    public boolean eliminar(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();
            Producto productoBD = em.find(Producto.class, id);
            em.getTransaction().begin();
            em.remove(productoBD);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el producto");
            return false;
        }
    }

    @Override
    public Producto consultar(int id) {

        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Producto productoBD = em.find(Producto.class, id);
            em.getTransaction().commit();
            return productoBD;
        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar el producto");
            return null;
        }
    }

    @Override
    public List<Producto> consultarTodos() {
        try {
            EntityManager em = this.conexion.crearConexion();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
            TypedQuery<Producto> query = em.createQuery(criteria);
            return query.getResultList();
        } catch (IllegalStateException ise) {
            System.err.println("No se pudierón consultar los Productos");
            return null;
        }

    }

    @Override
    public List<Producto> consultarEspecial(Object paramtroEspecial) {
        try {
            EntityManager em = this.conexion.crearConexion();

            CriteriaBuilder builder = em.getCriteriaBuilder();

            CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);

            Root<Producto> root = criteria.from(Producto.class);
//Dependiendo del paramatero sera el tipo de busqueda, inventarnos algo que valide eso
            criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%" +paramtroEspecial + "%"));

            TypedQuery<Producto> query = em.createQuery(criteria);

            return query.getResultList();

        } catch (IllegalStateException ise) {
            System.err.println("No se pudo consultar los productos por nombre");
            return null;
        }
    }
}
