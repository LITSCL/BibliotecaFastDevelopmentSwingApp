package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class EstadoLibroDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(EstadoLibro es) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO estado_libro" + "(codigo, descripcion)" + " VALUES('" + es.getCodigo() + "','" + es.getDescripcion() + "')"; 
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
	
	public List<EstadoLibro> getAll() {
		List<EstadoLibro> estadoLibros = new ArrayList<EstadoLibro>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, descripcion" + " FROM estado_libro";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				EstadoLibro es = new EstadoLibro();
				es.setCodigo(rs.getString(1));
				es.setDescripcion(rs.getString(2));

				estadoLibros.add(es); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			estadoLibros = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return estadoLibros;
	}
	
	public boolean delete(EstadoLibro el) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM estado_libro" + " WHERE codigo = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, el.getCodigo());
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
	
	public void update(EstadoLibro el){
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE estado_libro SET descripcion = ?" + " WHERE codigo = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, el.getDescripcion());
			st.setString(2, el.getCodigo());
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: "+resultado);
			
		} catch(Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: "+resultado);
		} finally { 
			bdUtil.desconectar(); 

		}
		
	}
	
	public List<EstadoLibro> filtrarEstadoLibro(String filtro) {
		List<EstadoLibro> estadoLibros = new ArrayList<EstadoLibro>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, descripcion" + " FROM estado_libro WHERE codigo = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: "+resultado);
			while (rs.next()) { 
				EstadoLibro es = new EstadoLibro();
				es.setCodigo(rs.getString(1));
				es.setDescripcion(rs.getString(2));

				estadoLibros.add(es); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: "+resultado);
			estadoLibros = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}
		return estadoLibros;
	}

}
