package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoFactura;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class MetodoPagoFacturaDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(MetodoPagoFactura mpf) {
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO metodo_de_pago_factura" + "(codigo, descripcion)" + " VALUES('" + mpf.getCodigo() + "','" + mpf.getDescripcion() + "')"; 
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
	
	public List<MetodoPagoFactura> getAll() {
		List<MetodoPagoFactura> metodoPagoFacturas = new ArrayList<MetodoPagoFactura>();	
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, descripcion" + " FROM metodo_de_pago_factura";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				MetodoPagoFactura mpf = new MetodoPagoFactura();
				mpf.setCodigo(rs.getString(1));
				mpf.setDescripcion(rs.getString(2));
				
				metodoPagoFacturas.add(mpf);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			metodoPagoFacturas = null;
		} finally { 
			bdUtil.desconectar(); 
		}
		return metodoPagoFacturas;
	}
	
	public boolean delete(MetodoPagoFactura mpf) {
		boolean resultado;
		
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM metodo_de_pago_factura" + " WHERE codigo = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, mpf.getCodigo());
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
	
	public void update(MetodoPagoFactura mpf) {
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE metodo_de_pago_factura SET descripcion = ?" + " WHERE codigo = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, mpf.getDescripcion());
			st.setString(2, mpf.getCodigo());

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
