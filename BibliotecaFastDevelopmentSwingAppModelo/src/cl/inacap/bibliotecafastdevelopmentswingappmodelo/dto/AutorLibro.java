package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class AutorLibro {
	private int id;
	private int libroFK;
	private String autorFK;
	
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

	public String getAutorFK() {
		return autorFK;
	}
	
	public void setAutorFK(String autorFK) {
		this.autorFK = autorFK;
	}
}
