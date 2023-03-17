
package datos;

import java.util.List;

public interface IProductosDAO {

    boolean agregar(Producto producto);
    
    boolean actualizar(Producto producto);
    
    boolean eliminar(int id);
    
    boolean quitarStock(Producto producto , int stock);
    
    boolean agregarStock(Producto producto , int stock);
    
    Producto consultar(int id);
    
    List<Producto> consultarPorNombre(String nombre);
    
    List<Producto> consultarTodos();
    
    
}
