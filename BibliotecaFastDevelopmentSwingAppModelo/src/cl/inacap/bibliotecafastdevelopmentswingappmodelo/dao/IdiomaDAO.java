package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class IdiomaDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Idioma id) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO idioma" + "(codigo, nombre)" + " VALUES('" + id.getCodigo() + "','" + id.getNombre() + "')"; 
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
	
	public List<Idioma> getAll() {
		List<Idioma> idiomas = new ArrayList<Idioma>();
		boolean resultado;
		
		try{
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			Statement st = bdUtil.getConexion().createStatement();
			String sql = "SELECT codigo, nombre" + " FROM idioma";
			
			ResultSet rs = st.executeQuery(sql);
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			
			while (rs.next() == true) {
				Idioma id = new Idioma();
				id.setCodigo(rs.getString(1));
				id.setNombre(rs.getString(2));
				
				idiomas.add(id);
			}
			rs.close();
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			idiomas = null;
			
		} finally {
			bdUtil.desconectar();
		}
		
		return idiomas;
	}
	
	public void delete(Idioma id) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM idioma" + " WHERE codigo = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, id.getCodigo());
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
	
	public void update(Idioma id) {
		
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE idioma SET nombre = ?" + " WHERE codigo = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, id.getNombre());
			st.setString(2, id.getCodigo());
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
	
	public List<Idioma> filtrarIdioma(String filtro) {
		List<Idioma> idiomas = new ArrayList<Idioma>();
		boolean resultado;
		
		try{
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			Statement st = bdUtil.getConexion().createStatement();
			String sql = "SELECT codigo, nombre" + " FROM idioma WHERE codigo = " + "'" + filtro + "'";
			
			ResultSet rs = st.executeQuery(sql);
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			
			while (rs.next() == true) {
				Idioma id = new Idioma();
				id.setCodigo(rs.getString(1));
				id.setNombre(rs.getString(2));
				
				idiomas.add(id);
			}
			rs.close();
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			idiomas = null;
			
		} finally {
			bdUtil.desconectar();
		}	
		return idiomas;
	}

}
