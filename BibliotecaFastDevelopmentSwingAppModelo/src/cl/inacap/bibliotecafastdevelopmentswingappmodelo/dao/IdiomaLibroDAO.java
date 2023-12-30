package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.IdiomaLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class IdiomaLibroDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(List<Idioma> idiomas, int libroFK) {
		boolean resultado = false;

		for (Idioma id : idiomas) {
			try {
				System.out.println("Conexión a la DB Idioma: " + bdUtil.conectar());
				String sql = "INSERT INTO idioma_libro" + "(libro_numero_de_serie, idioma_codigo)" + " VALUES('" + libroFK + "','" + id.getCodigo() + "')"; 
				Statement st = bdUtil.getConexion().createStatement();
				st.executeUpdate(sql);
				resultado = true;
				System.out.println("Ejecución del SQL Idioma: " + resultado);
			} catch (Exception ex) {
				resultado = false;
				System.out.println("Ejecución del SQL Idioma: " + resultado);
			} finally { 
				bdUtil.desconectar(); 
			}
		}
		return resultado;
	}

	public List<IdiomaLibro> getAll() {
		List<IdiomaLibro> idiomaLibros = new ArrayList<IdiomaLibro>();	
		boolean resultado;

		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT id, libro_numero_de_serie, idioma_codigo" + " FROM idioma_libro";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				IdiomaLibro il = new IdiomaLibro();
				il.setId(rs.getInt(1));
				il.setLibroFK(rs.getInt(2));
				il.setIdiomaFK(rs.getString(3));

				idiomaLibros.add(il);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			idiomaLibros = null;
		} finally { 
			bdUtil.desconectar(); 
		}

		return idiomaLibros;
	}
	
	public void delete(IdiomaLibro il) {
		boolean resultado;
		
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM idioma_libro" + " WHERE id = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setInt(1, il.getId());
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
