package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.TelefonoTrabajador;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class TelefonoTrabajadorDAO {
private BDUtil bdUtil = new BDUtil();
	
	public boolean save(List<String> telefonos, String rutTrabajador) {
		boolean resultado = true;
		if (telefonos.size() > 0) {
			try {
				for (int i = 0; i < telefonos.size(); i++) {
				System.out.println("Conexión a la DB: " + bdUtil.conectar());
				String sql = "INSERT INTO telefono_trabajador" + "(telefono, trabajador_rut)" + " VALUES(?, ?)"; 
				PreparedStatement st = bdUtil.getConexion().prepareStatement(sql);
				st.setString(1, telefonos.get(i));
				st.setString(2, rutTrabajador);
				st.executeUpdate();
				resultado = true;
				System.out.println("Ejecución del SQL: " + resultado);
				}
			} catch (Exception ex) {
				resultado = false;
				System.out.println("Ejecución del SQL: " + resultado);
					
			} finally { 
				bdUtil.desconectar(); 
			}
			return resultado;
		}
		return resultado;
	}
	
	public List<TelefonoTrabajador> filtrarTelefonoTrabajador(String filtro) {
		
		List<TelefonoTrabajador> telefonosTrabajador = new ArrayList<TelefonoTrabajador>();
		boolean resultado;
		try {
				
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
				
			String sql = "SELECT id, telefono, trabajador_rut" + " FROM telefono_trabajador";
			sql+=" WHERE trabajador_rut = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql);
				
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				TelefonoTrabajador tt = new TelefonoTrabajador();
				tt.setId(rs.getInt(1));
				tt.setTelefono(rs.getString(2));
				tt.setTrabajadorFK(rs.getString(3));
				telefonosTrabajador.add(tt);
			}
				rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			telefonosTrabajador = null;
				
		} finally { 
			bdUtil.desconectar();
		}
		return telefonosTrabajador;
	}
}