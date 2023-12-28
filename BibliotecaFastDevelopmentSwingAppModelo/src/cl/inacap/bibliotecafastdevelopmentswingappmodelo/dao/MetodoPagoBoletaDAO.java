package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoBoleta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class MetodoPagoBoletaDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(MetodoPagoBoleta mpb) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO metodo_de_pago_boleta" + "(codigo, descripcion)" + " VALUES('" + mpb.getCodigo() + "','" + mpb.getDescripcion() + "')"; 
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
	
	public List<MetodoPagoBoleta> getAll() {
		List<MetodoPagoBoleta> metodoPagoBoletas = new ArrayList<MetodoPagoBoleta>();
		
		boolean resultado;
		try {	
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, descripcion" + " FROM metodo_de_pago_boleta";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				MetodoPagoBoleta mpb = new MetodoPagoBoleta();
				mpb.setCodigo(rs.getString(1));
				mpb.setDescripcion(rs.getString(2));
				
				metodoPagoBoletas.add(mpb);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			metodoPagoBoletas = null;			
		} finally { 
			bdUtil.desconectar(); 
		}
		return metodoPagoBoletas;
	}
	
	public boolean delete(MetodoPagoBoleta mpb) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM metodo_de_pago_boleta" + " WHERE codigo = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, mpb.getCodigo());
			st.executeUpdate();
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
	
	public void update(MetodoPagoBoleta mpb) {	
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE metodo_de_pago_boleta SET descripcion = ?" + " WHERE codigo = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, mpb.getDescripcion());
			st.setString(2, mpb.getCodigo());

			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
		} finally { 
			bdUtil.desconectar(); 
		}
	}
}
