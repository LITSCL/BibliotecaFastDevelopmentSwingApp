package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Distribuidor;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;


public class DistribuidorDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Distribuidor di) {
				boolean resultado;
				try {
					System.out.println("Conexión a la DB: " + bdUtil.conectar());
					String sql = "INSERT INTO distribuidor" + "(rut, nombre, pais, comuna, calle, numero, telefono, year_servicio)" + " VALUES('" + di.getRut() + "','" + di.getNombre() + "','" + di.getPais() + "','" + di.getComuna() + "','" + di.getCalle() + "','" + di.getNumero() + "','" + di.getTelefono() + "','" + di.getYearServicio() + "')"; 
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
	
	public List<Distribuidor> getAll() {
		List<Distribuidor> distribuidores = new ArrayList<Distribuidor>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT rut, nombre, pais, comuna, calle, numero, telefono, year_servicio" + " FROM distribuidor";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Distribuidor di = new Distribuidor();
				di.setRut(rs.getString(1));
				di.setNombre(rs.getString(2));
				di.setPais(rs.getString(3));
				di.setComuna(rs.getString(4));
				di.setCalle(rs.getString(5));
				di.setNumero(rs.getString(6));
				di.setTelefono(rs.getString(7));
				di.setYearServicio(rs.getString(8));
				
				distribuidores.add(di);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			distribuidores = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return distribuidores;
	}
	
	public void delete(Distribuidor di) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM distribuidor" + " WHERE rut = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, di.getRut());
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
	
	public void update(Distribuidor di) {
		
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE distribuidor SET nombre = ?" + ", pais = ?" + ", comuna = ?" + ", calle = ?" + ", numero = ?" + ", telefono = ?" + ", year_servicio = ?" + " WHERE rut = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, di.getNombre());
			st.setString(2, di.getPais());
			st.setString(3, di.getComuna());
			st.setString(4, di.getCalle());
			st.setString(5, di.getNumero());
			st.setString(6, di.getTelefono());
			st.setString(7, di.getYearServicio());
			st.setString(8, di.getRut());
			
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
	
	public List<Distribuidor> filtrarDistribuidor(String filtro) {
		List<Distribuidor> distribuidores = new ArrayList<Distribuidor>();
		
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT rut, nombre, pais, comuna, calle, numero, telefono, year_servicio" + " FROM distribuidor WHERE rut = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Distribuidor di = new Distribuidor();
				di.setRut(rs.getString(1));
				di.setNombre(rs.getString(2));
				di.setPais(rs.getString(3));
				di.setComuna(rs.getString(4));
				di.setCalle(rs.getString(5));
				di.setNumero(rs.getString(6));
				di.setTelefono(rs.getString(7));
				di.setYearServicio(rs.getString(8));
				
				distribuidores.add(di);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			distribuidores = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return distribuidores;
	}

}
