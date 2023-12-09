package cl.inacap.bibliotecafastdevelopmentswingapp.util;

/**
 * Esta clase pretende validar el formato Time del DBMS.
 * @author MelchioT
 *
 */
public class TiempoUtil {
	
	/**
	 * Este método valida el formato Time.
	 * @param tiempo Este parámetro es el formatoo Time ingresado por el usuario.
	 * @return Retorna true si el formato es valido, de lo contrario retorna false.
	 */
	public boolean validarHora(String tiempo) {
		int contador = 0;
		int horaInt = 0;
		int minutoInt = 0;
		int segundoInt = 0;
		boolean tiempoValidado = false;
		
		//Tiempo (Hora:Minuto:Segundo)
		if (tiempo.length() == 8) {
			String primerDosPuntos = Character.toString(tiempo.charAt(2));
			String segundoDosPuntos = Character.toString(tiempo.charAt(5));
			
			if (primerDosPuntos.equalsIgnoreCase(":")) {
				contador++;
			}
			if (segundoDosPuntos.equalsIgnoreCase(":")) {
				contador++;
			}
			
			String hora = Character.toString(tiempo.charAt(0))
					+ Character.toString(tiempo.charAt(1));
			
			try {
				horaInt = Integer.parseInt(hora);
				if (horaInt >= 00 && horaInt <= 23) {
					contador++;
				}
			} catch (Exception ex) {
				
			}
			
			String minuto = Character.toString(tiempo.charAt(3))
					+ Character.toString(tiempo.charAt(4));
			
			try {
				minutoInt = Integer.parseInt(minuto);
				if (minutoInt >= 00 && minutoInt <= 59) {
					contador++;
				}
			} catch(Exception ex) {
				
			}
			
			String segundo = Character.toString(tiempo.charAt(6))
					+ Character.toString(tiempo.charAt(7));
			
			try {
				segundoInt = Integer.parseInt(segundo);
				if (segundoInt >= 00 && segundoInt <= 59) {
					contador++;
				}
			} catch (Exception ex) {
				
			}
			
			if (contador == 5) {
				tiempoValidado = true;
			}
		}
		return tiempoValidado;
	}
}
