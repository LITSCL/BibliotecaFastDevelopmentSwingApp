package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Boleta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class BoletaDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Boleta bo) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO boleta" + "(folio, precio_neto, precio_con_iva, costo_iva, fecha_de_venta, hora_de_venta, metodo_de_pago_boleta_codigo, cliente_rut, trabajador_rut, venta_id)" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql);
			st.setString(1, bo.getFolio());
			st.setDouble(2, bo.getPrecioNeto());
			st.setDouble(3, bo.getPrecioConIVA());
			st.setDouble(4, bo.getCostoIVA());
			st.setString(5, bo.getFechaDeVenta());
			st.setString(6, bo.getHoraDeVenta());
			st.setString(7, bo.getMetodoDePagoBoletaFK());
			st.setString(8, bo.getClienteFK());
			st.setString(9, bo.getTrabajadorFK());
			st.setInt(10, bo.getVentaFK());
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			
		} finally { 
		bdUtil.desconectar(); 
		}
		return resultado;
	}
	
	public List<Boleta> getAll(){
		List<Boleta> boletas = new ArrayList<Boleta>();
		
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT folio, precio_neto, precio_con_iva, costo_iva, fecha_de_venta, hora_de_venta, metodo_de_pago_boleta_codigo, cliente_rut, trabajador_rut, venta_id" + " FROM boleta";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Boleta bo = new Boleta();
				bo.setFolio(rs.getString(1));
				bo.setPrecioNeto(rs.getDouble(2));
				bo.setPrecioConIVA(rs.getDouble(3));
				bo.setCostoIVA(rs.getDouble(4));
				bo.setFechaDeVenta(rs.getString(5));
				bo.setHoraDeVenta(rs.getString(6));
				bo.setMetodoDePagoBoletaFK(rs.getString(7));
				bo.setClienteFK(rs.getString(8));
				bo.setTrabajadorFK(rs.getString(9));
				bo.setVentaFK(rs.getInt(10));
				
				boletas.add(bo); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			boletas = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return boletas;
	}
	
	public boolean delete(Boleta bo) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM boleta" + " WHERE folio = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, bo.getFolio());
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			
		} finally {
			bdUtil.desconectar();
		}
		return resultado;
	}
	
	public List<Boleta> filtrarBoleta(String filtro){
		List<Boleta> boletas = new ArrayList<Boleta>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT folio, precio_neto, precio_con_iva, costo_iva, fecha_de_venta, hora_de_venta, trabajador_rut, metodo_de_pago_boleta_codigo, cliente_rut" + " FROM boleta";
			sql+=" WHERE folio=" + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while(rs.next()) { 
				Boleta bo = new Boleta();
				bo.setFolio(rs.getString(1));
				bo.setPrecioNeto(rs.getDouble(2));
				bo.setPrecioConIVA(rs.getDouble(3));
				bo.setCostoIVA(rs.getDouble(4));
				bo.setFechaDeVenta(rs.getString(5));
				bo.setHoraDeVenta(rs.getString(6));
				bo.setTrabajadorFK(rs.getString(7));
				bo.setMetodoDePagoBoletaFK(rs.getString(8));
				bo.setClienteFK(rs.getString(9));

				boletas.add(bo); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			boletas = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return boletas;
	}
}

