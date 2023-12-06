package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.TelefonoCliente;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class TelefonoClienteDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(List<String> telefonos, String rutCliente) {
		boolean resultado = true;
		if (telefonos.size() > 0) {
			try {
				for (int i = 0; i < telefonos.size(); i++) {
				System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
				String sql = "INSERT INTO telefono_cliente" + "(telefono, cliente_rut)" + " VALUES(?, ?)"; 
				PreparedStatement st = bdUtil.getConexion().prepareStatement(sql);
				st.setString(1, telefonos.get(i));
				st.setString(2, rutCliente);
				st.executeUpdate();
				resultado = true;
				System.out.println("Ejecuci�n del SQL: " + resultado);
				}
			} catch (Exception ex) {
				resultado = false;
				System.out.println("Ejecuci�n del SQL: " + resultado);
					
			} finally { 
				bdUtil.desconectar(); 
			}
			return resultado;
		}
		return resultado;
	}
	
	public List<TelefonoCliente> filtrarTelefonoCliente(String filtro) {
		
		List<TelefonoCliente> telefonosCliente = new ArrayList<TelefonoCliente>();
		boolean resultado;
		try {
				
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
				
			String sql = "SELECT id, telefono, cliente_rut" + " FROM telefono_cliente";
			sql+=" WHERE cliente_rut = " + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql);
				
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				TelefonoCliente tc = new TelefonoCliente();
				tc.setId(rs.getInt(1));
				tc.setTelefono(rs.getString(2));
				tc.setClienteFK(rs.getString(3));
				telefonosCliente.add(tc);
			}
				rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			telefonosCliente = null;
				
		}finally { 
			bdUtil.desconectar();
		}
		return telefonosCliente;
	}
}
	
			
		
		

