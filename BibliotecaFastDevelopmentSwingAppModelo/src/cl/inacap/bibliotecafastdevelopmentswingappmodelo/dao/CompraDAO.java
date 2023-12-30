package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Compra;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

/**
* Esta clase pretende realizar sentencias SQL sobre la tabla compra al DBMS.
 * @author MelchioT
 *
 */
public class CompraDAO {
	private BDUtil bdUtil = new BDUtil();
	
	/**
	 * Este método permite agregar un registro a la tabla compra de la base de datos.
	 * @param co Es el objeto a agregar en la base de datos.
	 * @param estadoFK Este parámetro hace referencia a la clave foránea de la tabla estado.
	 * @return Retorna true si se agrego exitosamente el registro, en caso contrario retorna false.
	 */
	public boolean save(double precio) { //Acá se genera la compra.
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO compra" + "(precio)" + " VALUES('" + precio + "')"; 
			Statement st = bdUtil.getConexion().createStatement();
			st.executeUpdate(sql);
			
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
		} finally { 
			bdUtil.desconectar(); 
		}
		return resultado;
	}
	
	public List<Compra> getAll() {
		List<Compra> compras = new ArrayList<Compra>();
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT id, precio" + " FROM compra";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Compra co = new Compra();
				co.setId(rs.getInt(1));
				co.setPrecio(rs.getDouble(2));
				
				compras.add(co);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			compras = null;
		} finally { 
			bdUtil.desconectar(); 
		}
		return compras;
	}
}
