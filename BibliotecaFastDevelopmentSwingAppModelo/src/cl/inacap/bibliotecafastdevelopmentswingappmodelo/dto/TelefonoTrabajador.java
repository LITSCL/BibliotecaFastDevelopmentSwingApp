package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class TelefonoTrabajador {
	private int id;
	private String telefono;
	private String trabajadorFK;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTrabajadorFK() {
		return trabajadorFK;
	}
	
	public void setTrabajadorFK(String trabajadorFK) {
		this.trabajadorFK = trabajadorFK;
	}
}
