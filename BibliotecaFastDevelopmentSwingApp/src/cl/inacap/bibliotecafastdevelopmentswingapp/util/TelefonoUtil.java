package cl.inacap.bibliotecafastdevelopmentswingapp.util;


public class TelefonoUtil {
	
	public boolean validarTelefono(String telefono) {
		boolean validado = false;
			
		//123456789
		if (telefono.length() == 9) {
			try {
				int telefonoInt = Integer.parseInt(telefono);
				validado = true;
			} catch (Exception ex) {
				
			}
		}
		
		return validado;
	}
}
