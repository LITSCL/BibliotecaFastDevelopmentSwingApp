package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.util.BDUtil;

/**
* Esta clase pretende realizar sentencias SQL sobre la tabla libro al DBMS.
 * @author MelchioT
 *
 */
public class LibroDAO {
	private BDUtil bdUtil = new BDUtil();
	
	/**
	 * Este método permite agregar un registro a la tabla libro de la base de datos.
	 * @param li Es el objeto a agregar en la base de datos.
	 * @return Retorna true si se agrego exitosamente el registro, en caso contrario retorna false.
	 */
	public boolean save(Libro li) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO libro" + "(numero_de_serie, isbn, titulo, numero_de_paginas, precio_de_referencia, fecha_de_publicacion, estado_libro_codigo, editorial_codigo)" + " VALUES('" + li.getNumeroDeSerie() + "','" + li.getIsbn() + "','" + li.getTitulo() + "','"+li.getNumeroDePaginas() + "','" + li.getPrecioDeReferencia() + "','" + li.getFechaDePublicacion() + "','"+li.getEstadoLibroFK() + "','" + li.getEditorialFK() + "')"; 
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
	
	public List<Libro> getAll() {
		List<Libro> libros = new ArrayList<Libro>();
		boolean resultado;
		
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			
			Statement st = bdUtil.getConexion().createStatement();
			String sql = "SELECT numero_de_serie, isbn, titulo, numero_de_paginas, precio_de_referencia, fecha_de_publicacion, estado_libro_codigo, editorial_codigo" + " FROM libro";
			
			ResultSet rs = st.executeQuery(sql);
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next() == true) {
				Libro li = new Libro();
				li.setNumeroDeSerie(rs.getInt(1));
				li.setIsbn(rs.getString(2));
				li.setTitulo(rs.getString(3));
				li.setNumeroDePaginas(rs.getInt(4));
				li.setPrecioDeReferencia(Double.parseDouble(rs.getString(5)));
				li.setFechaDePublicacion(rs.getString(6));
				li.setEstadoLibroFK(rs.getString(7));
				li.setEditorialFK(rs.getString(8));
				
				libros.add(li);
			}
			rs.close();
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			libros = null;
			
		} finally {
			bdUtil.desconectar();
		}
		
		return libros;
	}
	
	public void delete(Libro li) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "DELETE FROM libro" + " WHERE numero_de_serie = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
			st.setInt(1, li.getNumeroDeSerie());
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

}
