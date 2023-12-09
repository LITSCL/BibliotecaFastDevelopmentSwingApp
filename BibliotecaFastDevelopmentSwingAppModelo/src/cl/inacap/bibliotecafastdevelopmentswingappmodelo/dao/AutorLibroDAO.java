package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.Statement;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class AutorLibroDAO {
private BDUtil bdUtil = new BDUtil();
	
	public boolean save(List<Object> autores, int libroFK) {
		boolean resultado = false;

		for (Object autor : autores) {
			try {
				System.out.println("Conexión a la DB Autor: " + bdUtil.conectar());
				String sql = "INSERT INTO autor_libro" + "(libro_numero_de_serie, autor_codigo)" + " VALUES('" + libroFK + "','" + autor + "')"; 
				Statement st = bdUtil.getConexion().createStatement();
				st.executeUpdate(sql);
				resultado = true;
				System.out.println("Ejecución del SQL Autor: " + resultado);
			} catch (Exception ex) {
				resultado = false;
				System.out.println("Ejecución del SQL Autor: " + resultado);
				
			} finally { 
			bdUtil.desconectar(); 
			}
		}
		return resultado;
		}
}
