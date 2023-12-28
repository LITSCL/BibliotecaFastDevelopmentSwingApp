package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class TrabajadorDAO {
	
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Trabajador tr) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO trabajador" + "(rut, nombre, apellido_paterno, apellido_materno, fecha_de_contrato)" + " VALUES('" + tr.getRut() + "','" + tr.getNombre() + "','" + tr.getApellidoPaterno() + "','" + tr.getApellidoMaterno() + "','" + tr.getFechaDeContrato() + "')"; 
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
	
	public List<Trabajador> getAll() {
		List<Trabajador> trabajadores = new ArrayList<Trabajador>();
		
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			Statement st = bdUtil.getConexion().createStatement();
			String sql = "SELECT rut, nombre, apellido_paterno, apellido_materno, fecha_de_contrato" + " FROM trabajador";
			
			ResultSet rs = st.executeQuery(sql);
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			
			while (rs.next() == true) {
				Trabajador tr = new Trabajador();
				tr.setRut(rs.getString(1));
				tr.setNombre(rs.getString(2));
				tr.setApellidoPaterno(rs.getString(3));
				tr.setApellidoMaterno(rs.getString(4));
				tr.setFechaDeContrato(rs.getString(5));
				
				trabajadores.add(tr);
			}
			rs.close();
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			trabajadores = null;
			
		} finally {
			bdUtil.desconectar();
		}
		
		return trabajadores;
	}
	
	public void delete(Trabajador tr) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM trabajador" + " WHERE rut = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, tr.getRut());
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado=false;
			System.out.println("Ejecución del SQL: " + resultado);
			
		} finally {
			bdUtil.desconectar();
		}
	}
	
	public void update(Trabajador tr) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "UPDATE trabajador SET nombre = ?" + ", apellido_paterno = ?" + ", apellido_materno = ?" + ", fecha_de_contrato = ?" + " WHERE rut = ?"; 
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setString(1, tr.getNombre());
			st.setString(2, tr.getApellidoPaterno());
			st.setString(3, tr.getApellidoMaterno());
			st.setString(4, tr.getFechaDeContrato());
			st.setString(5, tr.getRut());
			
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
	
	public List<Trabajador> filtrarTrabajador(String filtro) {		
		List<Trabajador> trabajadores = new ArrayList<Trabajador>();
		
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			String sql = "SELECT rut, nombre, apellido_paterno, apellido_materno, fecha_de_contrato" + " FROM trabajador";
			sql+=" WHERE rut = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql);
				
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Trabajador tr = new Trabajador();
				tr.setRut(rs.getString(1));
				tr.setNombre(rs.getString(2));
				tr.setApellidoPaterno(rs.getString(3));
				tr.setApellidoMaterno(rs.getString(4));
				tr.setFechaDeContrato(rs.getString(5));
				trabajadores.add(tr);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			trabajadores = null;
				
		} finally { 
			bdUtil.desconectar();
		}
		return trabajadores;
	}

}
