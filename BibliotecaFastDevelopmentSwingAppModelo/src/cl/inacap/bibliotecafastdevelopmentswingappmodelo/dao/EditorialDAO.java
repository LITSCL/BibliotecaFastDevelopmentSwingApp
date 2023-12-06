package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Editorial;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class EditorialDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Editorial ed) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO editorial" + "(codigo, nombre)" + " VALUES('" + ed.getCodigo() + "','" + ed.getNombre() + "')"; 
			Statement st = bdUtil.getConexion().createStatement();
			st.executeUpdate(sql);
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
	
	public List<Editorial> getAll() {
		List<Editorial> editoriales = new ArrayList<Editorial>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, nombre" + " FROM editorial";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Editorial ed = new Editorial();
				ed.setCodigo(rs.getString(1));
				ed.setNombre(rs.getString(2));

				editoriales.add(ed); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			editoriales = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return editoriales;
	}
	
	public boolean delete(Editorial ed) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM editorial" + " WHERE codigo = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, ed.getCodigo());
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
	
	public void update(Editorial ed) {
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE editorial SET nombre = ?" + " WHERE codigo = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, ed.getNombre());
			st.setString(2, ed.getCodigo());
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
		} finally { 
			bdUtil.desconectar(); 

		}
		
	}
	
	public List<Editorial> filtrarEditorial(String filtro) {
		List<Editorial> editoriales = new ArrayList<Editorial>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, nombre" + " FROM editorial WHERE codigo = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Editorial ed = new Editorial();
				ed.setCodigo(rs.getString(1));
				ed.setNombre(rs.getString(2));

				editoriales.add(ed); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			editoriales = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}
		return editoriales;
	}
}
