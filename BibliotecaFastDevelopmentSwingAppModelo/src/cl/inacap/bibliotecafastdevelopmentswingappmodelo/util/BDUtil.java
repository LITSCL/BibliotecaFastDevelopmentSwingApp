package cl.inacap.bibliotecafastdevelopmentswingappmodelo.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Esta clase permite realizar una conxión a la base de datos.
 * @author MelchioT
 *
 */
public class BDUtil {
	private final String servidor = "localhost"; 
	private final String baseDeDatos = "dbbibliotecafastdevelopmentswingapp"; 
	private final String usuario = "root"; 
	private final String clave = "root"; 
	private Connection conexion;
	
	public Connection getConexion() {
		return conexion;
	}
	
	/**
	 * Este método permite conectar el programa a la base de datos.
	 * @return Retorna true si la conexión fue exitosa, de lo contrario retorna false.
	 */
	public boolean conectar() {
		try {
			String url = "jdbc:mysql://" + servidor + ":3306/" + baseDeDatos; 
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conexion = DriverManager.getConnection(url, usuario, clave);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * Este método desconecta el programa de la base de datos.
	 */
	public void desconectar() {
		try {
			this.conexion.close();	
		} catch (Exception ex) {
			
		}
	}
} 
