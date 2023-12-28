package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.Statement;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

public class CategoriaLibroDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(List<Categoria> categorias, int libroFK) {
		boolean resultado = false;
		
		for (Categoria ca : categorias) {
			try {
				System.out.println("Conexión a la DB Categoria: " + bdUtil.conectar());
				String sql = "INSERT INTO categoria_libro" + "(libro_numero_de_serie, categoria_codigo)" + " VALUES('" + libroFK + "','" + ca.getCodigo() + "')"; 
				Statement st = bdUtil.getConexion().createStatement();
				st.executeUpdate(sql);
				resultado = true;
				System.out.println("Ejecución del SQL Categoria: " + resultado);
			} catch (Exception ex) {
				resultado = false;
				System.out.println("Ejecución del SQL Categoria: " + resultado);
				
			} finally { 
				bdUtil.desconectar(); 
			}
		}
		return resultado;
	}
}
	

