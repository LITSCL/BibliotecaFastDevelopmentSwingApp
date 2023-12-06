package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Autor;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

/**
 * Esta clase pretende realizar sentencias SQL sobre la tabla autor al DBMS.
 * @author MelchioT
 *
 */
public class AutorDAO {
	private BDUtil bdUtil = new BDUtil();
	
	/**
	 * Este m�todo permite agregar un registro a la tabla autor de la base de datos.
	 * @param au Es el objeto a agregar en la base de datos.
	 * @return Retorna true si se agrego exitosamente el registro, en caso contrario retorna false.
	 */
	
	public boolean save(Autor au) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO autor" + "(codigo, nombre, apellido_paterno, apellido_materno, fecha_de_nacimiento)"+" VALUES('" + au.getCodigo() + "','" + au.getNombre() + "','" + au.getApellidoPaterno() + "','"+au.getApellidoMaterno() + "','" + au.getFechaDeNacimiento() + "')"; 
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
	
	public List<Autor> getAll(){
		List<Autor> autores = new ArrayList<Autor>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, nombre, apellido_paterno, apellido_materno, fecha_de_nacimiento" + " FROM autor";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Autor au = new Autor();
				au.setCodigo(rs.getString(1));
				au.setNombre(rs.getString(2));
				au.setApellidoPaterno(rs.getString(3));
				au.setApellidoMaterno(rs.getString(4));
				au.setFechaDeNacimiento(rs.getString(5));

				autores.add(au); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			autores = null;			
		} finally { 
			bdUtil.desconectar(); 
		}

		return autores;
	}
	
	public void delete(Autor au) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM autor" + " WHERE codigo=?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, au.getCodigo());
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
	
	public void update(Autor au){
		
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE autor SET nombre = ?" + ", apellido_paterno = ?" + ", apellido_materno=  ?" + ", fecha_de_nacimiento = ?" + " WHERE codigo = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, au.getNombre());
			st.setString(2, au.getApellidoPaterno());
			st.setString(3, au.getApellidoMaterno());
			st.setString(4, au.getFechaDeNacimiento());
			st.setString(5, au.getCodigo());
			
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
	
	public List<Autor> filtrarAutor(String filtro){
		List<Autor> autores = new ArrayList<Autor>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT codigo, nombre, apellido_paterno, apellido_materno, fecha_de_nacimiento" + " FROM autor WHERE codigo = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Autor au = new Autor();
				au.setCodigo(rs.getString(1));
				au.setNombre(rs.getString(2));
				au.setApellidoPaterno(rs.getString(3));
				au.setApellidoMaterno(rs.getString(4));
				au.setFechaDeNacimiento(rs.getString(5));

				autores.add(au); 
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			autores = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return autores;
	}

}
