package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.DistribuidorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Distribuidor;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.RutUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.TelefonoUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarDistribuidorFrame extends JInternalFrame {
	private JTextField textFieldYearServicio;
	private JTextField textFieldTelefono;
	private JTextField textFieldNumero;
	private JTextField textFieldCalle;
	private JTextField textFieldComuna;
	private JTextField textFieldPais;
	private JTextField textFieldNombre;
	private JTextField textFieldRut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarDistribuidorFrame frame = new RegistrarDistribuidorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrarDistribuidorFrame() {
		setTitle("Registrar Distribuidor");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setBounds(10, 33, 52, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 58, 52, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(10, 83, 46, 14);
		getContentPane().add(lblPais);
		
		JLabel lblComuna = new JLabel("Comuna");
		lblComuna.setBounds(10, 108, 66, 14);
		getContentPane().add(lblComuna);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 133, 103, 14);
		getContentPane().add(lblCalle);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(10, 158, 52, 14);
		getContentPane().add(lblNumero);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 183, 66, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblAosDeServicio = new JLabel("A\u00F1os de Servicio");
		lblAosDeServicio.setBounds(10, 208, 103, 14);
		getContentPane().add(lblAosDeServicio);
		
		textFieldYearServicio = new JTextField();
		textFieldYearServicio.setBounds(111, 205, 86, 20);
		getContentPane().add(textFieldYearServicio);
		textFieldYearServicio.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(111, 180, 86, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(111, 155, 86, 20);
		getContentPane().add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldCalle = new JTextField();
		textFieldCalle.setBounds(111, 130, 86, 20);
		getContentPane().add(textFieldCalle);
		textFieldCalle.setColumns(10);
		
		textFieldComuna = new JTextField();
		textFieldComuna.setBounds(111, 105, 86, 20);
		getContentPane().add(textFieldComuna);
		textFieldComuna.setColumns(10);
		
		textFieldPais = new JTextField();
		textFieldPais.setBounds(111, 80, 86, 20);
		getContentPane().add(textFieldPais);
		textFieldPais.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(111, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(111, 30, 86, 20);
		getContentPane().add(textFieldRut);
		textFieldRut.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnRegistrar.setIcon(new ImageIcon(RegistrarDistribuidorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnRegistrar.addActionListener(e -> registrarDistribuidor(e));
		btnRegistrar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnRegistrar);
		
		JLabel lblNewLabel_2 = new JLabel("(2010-05-22)");
		lblNewLabel_2.setBounds(207, 208, 103, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("(19.757.106-3 / 4.283.767-9)");
		lblNewLabel_3.setBounds(207, 33, 213, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel label = new JLabel("(123456789)");
		label.setBounds(207, 183, 103, 14);
		getContentPane().add(label);
		
		

	}
	private void registrarDistribuidor(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String rut = this.textFieldRut.getText().trim();
		RutUtil rutUtils = new RutUtil();
		if (rutUtils.validarRutChileno(rut) == false) {
			errores.add("El rut ingresado no es v�lido");
		}
		
		String nombre = this.textFieldNombre.getText().trim();
		if (nombre.isEmpty() == true) {
			errores.add("Nombre no valido");
		}
		String pais = this.textFieldPais.getText().trim();
		if (pais.isEmpty()) {
			errores.add("Pais no valido");
		}
		
		String comuna = this.textFieldComuna.getText().trim();
		if (comuna.isEmpty()) {
			errores.add("Comuna no valida");
		}
		
		String calle = this.textFieldCalle.getText().trim();
		if (calle.isEmpty()) {
			errores.add("Calle no valida");
		}
		
		String numero = this.textFieldNumero.getText().trim();
		boolean numeroValidado;
		try {
			int validaNumero = Integer.parseInt(numero);
			numeroValidado = true;
		} catch(Exception ex) {
			numeroValidado = false;
		}
		if (numero.isEmpty() || numeroValidado == false) {
			errores.add("N�mero de calle no valido");
		}
		
		
		String telefono = this.textFieldTelefono.getText().trim();
		if (new TelefonoUtil().validarTelefono(telefono)) {
			
		}
		else {
			errores.add("N�mero de telefono no valido");
		}
			
		String yearServicio = this.textFieldYearServicio.getText().trim();
		if (new FechaGringaUtil().validarFechaGringa(yearServicio) == false) {
			errores.add("Formato de fecha no valido");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
		
		if (errores.isEmpty() == true) {
			Distribuidor di = new Distribuidor();
			di.setRut(rut);
			di.setNombre(nombre);
			di.setPais(pais);
			di.setComuna(comuna);
			di.setCalle(calle);
			di.setNumero(numero);
			di.setTelefono(telefono);
			di.setYearServicio(yearServicio);
			
			DistribuidorDAO daoDistribuidor = new DistribuidorDAO();
			if (daoDistribuidor.save(di) == true) {
				JOptionPane.showMessageDialog(null, "Distribuidor registrado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El rut ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
