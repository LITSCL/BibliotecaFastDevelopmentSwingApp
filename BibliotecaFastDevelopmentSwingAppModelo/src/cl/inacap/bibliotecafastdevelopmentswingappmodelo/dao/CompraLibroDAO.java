package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.CompraLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class CompraLibroDAO {
private BDUtil bdUtil = new BDUtil();
	
	public boolean save(List<Libro> libros, int compraFK, String codigoFK) {
		boolean resultado = false;

		for(Libro cl : libros) {
			try {
				System.out.println("Conexi�n a la DB CompraLibro: " + bdUtil.conectar());
				String sql = "INSERT INTO compra_libro" + "(compra_id, libro_numero_de_serie)" + " VALUES('" + compraFK + "','" + cl.getNumeroDeSerie() + "')"; 
				Statement st = bdUtil.getConexion().createStatement();
				
				st.executeUpdate(sql);
				resultado = true;
				System.out.println("Ejecuci�n del SQL CompraLibro: " + resultado);
				
				
				String sql2 = "UPDATE libro SET estado_libro_codigo = ?" + " WHERE numero_de_serie = ?"; 
				PreparedStatement ps = bdUtil.getConexion().prepareStatement(sql2); 
				ps.setString(1, codigoFK);
				ps.setInt(2, cl.getNumeroDeSerie());

				ps.executeUpdate();
				System.out.println("Ejecuci�n del SQL CompraLibro: " + resultado);
			} catch (Exception ex) {
				resultado = false;
				System.out.println("Ejecuci�n del SQL CompraLibro: " + resultado);
				
			} finally { 
				bdUtil.desconectar(); 
			}
		}
		return resultado;
	}
	
	public List<CompraLibro> filtrarCompraLibro(int filtro, String columna) {
		List<CompraLibro> compraLibros = new ArrayList<CompraLibro>();
		
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT id, compra_id, libro_numero_de_serie" + " FROM compra_libro";
			sql+=" WHERE " + columna + "=" + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				CompraLibro cl = new CompraLibro();
				cl.setId(rs.getInt(1));
				cl.setCompraFK(rs.getInt(2));
				cl.setLibroFK(rs.getInt(3));

				compraLibros.add(cl); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			compraLibros = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return compraLibros;
	}
	
	public int contarLibros() {
		int cantidadLibros = 0;
		boolean resultado = false;
		try {			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT id" + " FROM compra_libro";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				cantidadLibros++;
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			
		} finally { 
			bdUtil.desconectar(); 
		}
		
		return cantidadLibros;
	}
}
