package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.VentaLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class VentaLibroDAO {
private BDUtil bdUtil = new BDUtil();
	
	public boolean save(List<Libro> libros, int ventaFK, String codigoFK) {
		boolean resultado = false;

		for (Libro cl : libros) {
			try {
				System.out.println("Conexión a la DB VentaLibro: " + bdUtil.conectar());
				String sql = "INSERT INTO venta_libro" + "(venta_id, libro_numero_de_serie)" + " VALUES('" + ventaFK + "','" + cl.getNumeroDeSerie() + "')"; 
				Statement st = bdUtil.getConexion().createStatement();
				
				st.executeUpdate(sql);
				resultado = true;
				System.out.println("Ejecución del SQL VentaLibro: " + resultado);
				
				
				String sql2 = "UPDATE libro SET estado_libro_codigo = ?" + " WHERE numero_de_serie = ?"; 
				PreparedStatement ps = bdUtil.getConexion().prepareStatement(sql2); 
				ps.setString(1, codigoFK);
				ps.setInt(2, cl.getNumeroDeSerie());

				ps.executeUpdate();
				System.out.println("Ejecución del SQL VentaLibro: " + resultado);
			} catch (Exception ex) {
				resultado = false;
				System.out.println("Ejecución del SQL VentaLibro: " + resultado);	
			} finally { 
				bdUtil.desconectar(); 
			}
		}
		return resultado;
	}
	
	public List<VentaLibro> filtrarVentaLibro(int filtro, String columna) {
		List<VentaLibro> ventaLibros = new ArrayList<VentaLibro>();
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT id, venta_id, libro_numero_de_serie" + " FROM venta_libro";
			sql+=" WHERE " + columna + "=" + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				VentaLibro vl = new VentaLibro();
				vl.setId(rs.getInt(1));
				vl.setVentaFK(rs.getInt(2));
				vl.setLibroFK(rs.getInt(3));

				ventaLibros.add(vl); 
			}
			rs.close(); 
		} catch(Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			ventaLibros = null;
		} finally { 
			bdUtil.desconectar(); 
		}

		return ventaLibros;
	}
	
	public int contarLibros() {
		int cantidadLibros = 0;
		boolean resultado = false;

		try {			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT id" + " FROM venta_libro";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				cantidadLibros++;
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
		} finally { 
			bdUtil.desconectar(); 
		}	
		return cantidadLibros;
	}
}
