package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class CategoriaDAO {
	
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Categoria ca) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO categoria" + "(codigo, nombre)" + " VALUES('" + ca.getCodigo() + "','" + ca.getNombre() + "')"; 
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
	
	public List<Categoria> getAll(){
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, nombre" + " FROM categoria";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Categoria ca = new Categoria();
				ca.setCodigo(rs.getString(1));
				ca.setNombre(rs.getString(2));
				
				categorias.add(ca);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			categorias = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return categorias;
	}
	
	public void delete(Categoria ca) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM categoria" + " WHERE codigo = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, ca.getCodigo());
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
	
	public void update(Categoria ca){
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE categoria SET nombre = ?" + " WHERE codigo = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, ca.getNombre());
			st.setString(2, ca.getCodigo());
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			
		} catch(Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
		} finally { 
			bdUtil.desconectar(); 
		}
	}
	
	public List<Categoria> filtrarCategoria(String filtro){
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, nombre"+" FROM categoria WHERE codigo = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Categoria ca = new Categoria();
				ca.setCodigo(rs.getString(1));
				ca.setNombre(rs.getString(2));

				categorias.add(ca); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			categorias = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return categorias;
	}
}
