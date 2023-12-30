package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Factura;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

/**
* Esta clase pretende realizar sentencias SQL sobre la tabla factura al DBMS.
 * @author MelchioT
 *
 */
public class FacturaDAO {
	private BDUtil bdUtil = new BDUtil();
	
	/**
	 * Este método permite agregar un registro a la tabla compra de la base de datos.
	 * @param fa Es el objeto a agregar en la base de datos.
	 * @return Retorna true si se agrego exitosamente el registro, en caso contrario retorna false.
	 */
	public boolean save(Factura fa) {
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO factura" + "(folio, precio_neto, precio_con_iva, costo_iva, fecha_de_compra, hora_de_compra, metodo_de_pago_factura_codigo, distribuidor_rut, compra_id)" + " VALUES('" + fa.getFolio() + "','" + fa.getPrecioNeto() + "','" + fa.getPrecioConIVA() + "','" + fa.getCostoIVA() + "','" + fa.getFechaDeCompra() + "','" + fa.getHoraDeCompra() + "','" + fa.getMetodoDePagoFK() + "','" + fa.getDistribuidorFK() + "','" + fa.getCompraFK() + "')"; 
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
	
	public List<Factura> getAll() {
		List<Factura> facturas = new ArrayList<Factura>();
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT folio, precio_neto, precio_con_iva, costo_iva, fecha_de_compra, hora_de_compra, metodo_de_pago_factura_codigo, distribuidor_rut, compra_id" + " FROM factura";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Factura fa = new Factura();
				fa.setFolio(rs.getString(1));
				fa.setPrecioNeto(rs.getDouble(2));
				fa.setPrecioConIVA(rs.getDouble(3));
				fa.setCostoIVA(rs.getDouble(4));
				fa.setFechaDeCompra(rs.getString(5));
				fa.setHoraDeCompra(rs.getString(6));
				fa.setMetodoDePagoFK(rs.getString(7));
				fa.setDistribuidorFK(rs.getString(8));
				fa.setCompraFK(rs.getInt(9));

				facturas.add(fa); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			facturas = null;
		} finally { 
			bdUtil.desconectar(); 
		}
		return facturas;
	}
	
	public boolean delete(Factura fa) {
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM factura" + " WHERE folio = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, fa.getFolio());
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
	
	public List<Factura> filtrarFactura(String filtro) {
		List<Factura> facturas = new ArrayList<Factura>();
		boolean resultado;
		
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT folio, precio_neto, precio_con_iva, costo_iva, fecha_de_compra, hora_de_compra, metodo_de_pago_factura_codigo, distribuidor_rut, compra_id" + " FROM factura";
			sql+=" WHERE folio = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Factura fa = new Factura();
				fa.setFolio(rs.getString(1));
				fa.setPrecioNeto(rs.getDouble(2));
				fa.setPrecioConIVA(rs.getDouble(3));
				fa.setCostoIVA(rs.getDouble(4));
				fa.setFechaDeCompra(rs.getString(5));
				fa.setHoraDeCompra(rs.getString(6));
				fa.setMetodoDePagoFK(rs.getString(7));
				fa.setDistribuidorFK(rs.getString(8));
				fa.setCompraFK(rs.getInt(9));

				facturas.add(fa); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			facturas = null;
		} finally { 
			bdUtil.desconectar(); 
		}
		return facturas;
	}
}
