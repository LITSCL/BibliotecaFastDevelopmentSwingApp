package cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto;

public class TelefonoCliente {
	private int id;
	private String telefono;
	private String clienteFK;
	
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

	public String getClienteFK() {
		return clienteFK;
	}
	
	public void setClienteFK(String clienteFK) {
		this.clienteFK = clienteFK;
	}
}
