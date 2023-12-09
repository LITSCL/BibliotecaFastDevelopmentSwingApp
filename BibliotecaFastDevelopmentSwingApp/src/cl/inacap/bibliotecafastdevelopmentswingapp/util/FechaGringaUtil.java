package cl.inacap.bibliotecafastdevelopmentswingapp.util;

public class FechaGringaUtil {
	public boolean validarFechaGringa(String fecha) {
		int contador = 0;
		int diaInt=0;
		int mesInt=0;
		boolean fechaValidada = false;
		
		//Fecha (Año-Mes-Dia)
		if (fecha.length() == 10) {
			String primerGuion = Character.toString(fecha.charAt(4));
			String segundoGuion = Character.toString(fecha.charAt(7));
			
			if (primerGuion.equalsIgnoreCase("-")) {
				contador++;
			}
			if (segundoGuion.equalsIgnoreCase("-")) {
				contador++;
			}
			
			String year = Character.toString(fecha.charAt(0))
					+ Character.toString(fecha.charAt(1))
					+ Character.toString(fecha.charAt(2))
					+ Character.toString(fecha.charAt(3));
			
			try {
				int yearInt = Integer.parseInt(year);
				if (yearInt >= 1000 && yearInt < 10000) {
					contador++;
				}
			} catch (Exception ex) {
				
			}
			
			String mes = Character.toString(fecha.charAt(5))
					+ Character.toString(fecha.charAt(6));
			
			try {
				mesInt = Integer.parseInt(mes);
				if (mesInt >= 1 && mesInt <= 12) {
					contador++;
				}
			} catch (Exception ex) {
				
			}
			
			String dia = Character.toString(fecha.charAt(8))
					+ Character.toString(fecha.charAt(9));
			
			try {
				diaInt = Integer.parseInt(dia);
				if (diaInt >= 1 && diaInt <= 31) {
					contador++;
				}
			} catch (Exception ex) {
				
			}
			
			if (contador == 5) {
				fechaValidada = true;
			}
			
			if (mesInt == 2 && diaInt > 29) {
				fechaValidada = false;
			}

		}
		return fechaValidada;
	}
}
