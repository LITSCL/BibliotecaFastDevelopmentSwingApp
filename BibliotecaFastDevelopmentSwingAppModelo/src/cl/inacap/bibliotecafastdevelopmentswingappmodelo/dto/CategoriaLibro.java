package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class CategoriaLibro {
	int id;
	String categoriaFK;
	int libroFK;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriaFK() {
		return categoriaFK;
	}

	public void setCategoriaFK(String categoriaFK) {
		this.categoriaFK = categoriaFK;
	}

	public int getLibroFK() {
		return libroFK;
	}
	
	public void setLibroFK(int libroFK) {
		this.libroFK = libroFK;
	}
}
