package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Venta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class VentaDAO {
private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Venta ve) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO venta" + "(precio, cliente_rut, trabajador_rut)" + " VALUES('" + ve.getPrecio() + "','" + ve.getClienteFK() + "','" + ve.getTrabajadorFK() + "')"; 
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
	
	public List<Venta> getAll() {
		List<Venta> ventas = new ArrayList<Venta>();
		
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT id, precio, cliente_rut, trabajador_rut" + " FROM venta";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Venta ve = new Venta();
				ve.setId(rs.getInt(1));
				ve.setPrecio(rs.getDouble(2));
				ve.setClienteFK(rs.getString(3));
				ve.setTrabajadorFK(rs.getString(4));
				
				ventas.add(ve);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			ventas = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}
		return ventas;
	}
}
