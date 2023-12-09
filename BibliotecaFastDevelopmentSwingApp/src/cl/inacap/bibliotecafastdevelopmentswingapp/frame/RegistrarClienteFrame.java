package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.ClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TelefonoClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.RutUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.TelefonoUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class RegistrarClienteFrame extends JInternalFrame {
	private JTextField textFieldFechaDeNacimiento;
	private JTextField textFieldApellidoMaterno;
	private JTextField textFieldApellidoPaterno;
	private JTextField textFieldNombre;
	private JTextField textFieldRut;
	private JTextField textFieldTelefono;
	private JList listTelefonos;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private DefaultListModel mo = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarClienteFrame frame = new RegistrarClienteFrame();
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
	public RegistrarClienteFrame() {
		setTitle("Registrar Cliente");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblRut = new JLabel("Rut");
		lblRut.setBounds(10, 33, 46, 14);
		getContentPane().add(lblRut);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 58, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido paterno");
		lblApellidoPaterno.setBounds(10, 83, 98, 14);
		getContentPane().add(lblApellidoPaterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido materno");
		lblApellidoMaterno.setBounds(10, 108, 98, 14);
		getContentPane().add(lblApellidoMaterno);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(10, 133, 119, 14);
		getContentPane().add(lblFechaDeNacimiento);
		
		textFieldFechaDeNacimiento = new JTextField();
		textFieldFechaDeNacimiento.setBounds(130, 130, 86, 20);
		getContentPane().add(textFieldFechaDeNacimiento);
		textFieldFechaDeNacimiento.setColumns(10);
		
		textFieldApellidoMaterno = new JTextField();
		textFieldApellidoMaterno.setBounds(130, 105, 86, 20);
		getContentPane().add(textFieldApellidoMaterno);
		textFieldApellidoMaterno.setColumns(10);
		
		textFieldApellidoPaterno = new JTextField();
		textFieldApellidoPaterno.setBounds(130, 80, 86, 20);
		getContentPane().add(textFieldApellidoPaterno);
		textFieldApellidoPaterno.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(130, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(130, 30, 86, 20);
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
		btnRegistrar.setIcon(new ImageIcon(RegistrarClienteFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnRegistrar.setBounds(559, 381, 115, 23);
		btnRegistrar.addActionListener(e -> registrarCliente(e));
		getContentPane().add(btnRegistrar);
		
		JLabel lblNewLabel = new JLabel("(2010-05-22)");
		lblNewLabel.setBounds(226, 133, 129, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("(19.757.106-3 / 4.283.767-9)");
		lblNewLabel_1.setBounds(226, 33, 211, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setBounds(10, 158, 98, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(130, 155, 86, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		listTelefonos = new JList();
		listTelefonos.setBounds(130, 186, 86, 117);
		getContentPane().add(listTelefonos);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(e -> agregarTelefono());
		btnAgregar.setBounds(226, 154, 89, 23);
		getContentPane().add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e -> eliminarTelefono());
		btnEliminar.setBounds(226, 186, 89, 23);
		getContentPane().add(btnEliminar);

	}
	
	private void agregarTelefono() {
		TelefonoUtil tu = new TelefonoUtil();
		String telefono = this.textFieldTelefono.getText();
		boolean telefonoValidado = tu.validarTelefono(telefono);
		
		if (telefonoValidado) {
			mo.addElement(telefono);
			listTelefonos.setModel(mo);
		}
		else {
			JOptionPane.showMessageDialog(null, "El número de telefono debe tener el formato: 123456789");
		}
		
	}
	
	private void eliminarTelefono() {
		try {
			mo.remove(listTelefonos.getSelectedIndex());
			listTelefonos.setModel(mo);
		} catch (Exception ex) {
			
		}

	}

	public void registrarCliente(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String rut = this.textFieldRut.getText().trim();
		RutUtil rutUtils = new RutUtil();
		if (rutUtils.validarRutChileno(rut) == false) {
			errores.add("El rut ingresado no es válido");
		}
		
		String nombre = this.textFieldNombre.getText().trim();
		if (nombre.isEmpty() == true) {
			errores.add("Nombre no valido");
		}
		
		String apellidoPaterno = this.textFieldApellidoPaterno.getText().trim();
		if (apellidoPaterno.isEmpty()) {
			errores.add("Apellido paterno no valido");
		}
		
		String apellidoMaterno = this.textFieldApellidoMaterno.getText().trim();
		if (apellidoMaterno.isEmpty()) {
			errores.add("Apellido materno no valido");
		}
		String fechaDeNacimiento = this.textFieldFechaDeNacimiento.getText();
		if (new FechaGringaUtil().validarFechaGringa(fechaDeNacimiento) == false) {
			errores.add("Formato de fecha no valido");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i<errores.size() ; i++) {
				mensaje+= "\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
		
		List<String> telefonos = new ArrayList<>();
		
		for (int i = 0; i < mo.getSize(); i++) {
			telefonos.add(mo.getElementAt(i).toString());
		}
		
		if (errores.isEmpty()) {
			Cliente cl = new Cliente();
			cl.setRut(rut);
			cl.setNombre(nombre);
			cl.setApellidoPaterno(apellidoPaterno);
			cl.setApellidoMaterno(apellidoMaterno);
			cl.setFechaDeNacimiento(fechaDeNacimiento);
			
			ClienteDAO daoCliente = new ClienteDAO();		
			
			if (daoCliente.save(cl)) {
				JOptionPane.showMessageDialog(null, "Cliente registrado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El rut ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}			
	
			
			TelefonoClienteDAO daoTelefonoClientes = new TelefonoClienteDAO();		
			daoTelefonoClientes.save(telefonos, cl.getRut());
		}
	}
	
}
