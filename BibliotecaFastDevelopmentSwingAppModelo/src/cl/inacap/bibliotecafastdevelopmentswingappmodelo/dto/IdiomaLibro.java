package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class IdiomaLibro {
	int id;
	int libroFK;
	String idiomaFK;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLibroFK() {
		return libroFK;
	}
	public void setLibroFK(int libroFK) {
		this.libroFK = libroFK;
	}
	public String getIdiomaFK() {
		return idiomaFK;
	}
	public void setIdiomaFK(String idiomaFK) {
		this.idiomaFK = idiomaFK;
	}

}
