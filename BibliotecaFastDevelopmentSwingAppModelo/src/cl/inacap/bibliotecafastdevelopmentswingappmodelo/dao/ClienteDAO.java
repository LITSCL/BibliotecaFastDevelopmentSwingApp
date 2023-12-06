package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class ClienteDAO {
	
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Cliente cl) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO cliente" + "(rut, nombre, apellido_paterno, apellido_materno, fecha_de_nacimiento)" + " VALUES('" + cl.getRut()+"','" + cl.getNombre() + "','" + cl.getApellidoPaterno() + "','" + cl.getApellidoMaterno() + "','" + cl.getFechaDeNacimiento() + "')"; 
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
	
	public List<Cliente> getAll(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT rut, nombre, apellido_paterno, apellido_materno, fecha_de_nacimiento" + " FROM cliente";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Cliente cl = new Cliente();
				cl.setRut(rs.getString(1));
				cl.setNombre(rs.getString(2));
				cl.setApellidoPaterno(rs.getString(3));
				cl.setApellidoMaterno(rs.getString(4));
				cl.setFechaDeNacimiento(rs.getString(5));
				
				clientes.add(cl);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			clientes = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return clientes;
	}
	
	public void delete(Cliente cl) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM cliente" + " WHERE rut = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, cl.getRut());
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
	
	public void update(Cliente cl) {
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE cliente SET nombre = ?" + ", apellido_paterno = ?" + ", apellido_materno = ?" + ", fecha_de_nacimiento = ?" + " WHERE rut = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, cl.getNombre());
			st.setString(2, cl.getApellidoPaterno());
			st.setString(3, cl.getApellidoMaterno());
			st.setString(4, cl.getFechaDeNacimiento());
			st.setString(5, cl.getRut());
			
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
	
	public List<Cliente> filtrarCliente(String filtro) {
			
		List<Cliente> clientes = new ArrayList<Cliente>();
		boolean resultado;
		try {
				
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
				
			String sql = "SELECT rut, nombre, apellido_paterno, apellido_materno, fecha_de_nacimiento" + " FROM cliente";
			sql+=" WHERE rut=" + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql);
				
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Cliente cl = new Cliente();
				cl.setRut(rs.getString(1));
				cl.setNombre(rs.getString(2));
				cl.setApellidoPaterno(rs.getString(3));
				cl.setApellidoMaterno(rs.getString(4));
				cl.setFechaDeNacimiento(rs.getString(5));
				clientes.add(cl);
			}
				rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			clientes = null;
				
		}finally { 
			bdUtil.desconectar();
		}
		return clientes;
	}
}
