package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TelefonoTrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.RutUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.TelefonoUtil;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class RegistrarTrabajadorFrame extends JInternalFrame {
	private JTextField textFieldFechaDeContrato;
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
					RegistrarTrabajadorFrame frame = new RegistrarTrabajadorFrame();
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
	public RegistrarTrabajadorFrame() {
		setTitle("Registrar Trabajador");
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
		lblApellidoPaterno.setBounds(10, 83, 103, 14);
		getContentPane().add(lblApellidoPaterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido materno");
		lblApellidoMaterno.setBounds(10, 108, 103, 14);
		getContentPane().add(lblApellidoMaterno);
		
		JLabel lblFechaDeContrato = new JLabel("Fecha de contrato");
		lblFechaDeContrato.setBounds(10, 133, 103, 14);
		getContentPane().add(lblFechaDeContrato);
		
		textFieldFechaDeContrato = new JTextField();
		textFieldFechaDeContrato.setBounds(116, 130, 86, 20);
		getContentPane().add(textFieldFechaDeContrato);
		textFieldFechaDeContrato.setColumns(10);
		
		textFieldApellidoMaterno = new JTextField();
		textFieldApellidoMaterno.setBounds(116, 105, 86, 20);
		getContentPane().add(textFieldApellidoMaterno);
		textFieldApellidoMaterno.setColumns(10);
		
		textFieldApellidoPaterno = new JTextField();
		textFieldApellidoPaterno.setBounds(116, 80, 86, 20);
		getContentPane().add(textFieldApellidoPaterno);
		textFieldApellidoPaterno.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(116, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(116, 30, 86, 20);
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
		btnRegistrar.setIcon(new ImageIcon(RegistrarTrabajadorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnRegistrar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnRegistrar);
		
		JLabel lblNewLabel = new JLabel("(2010-05-22)");
		lblNewLabel.setBounds(212, 133, 103, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("(19.757.106-3 / 4.283.767-9)");
		lblNewLabel_1.setBounds(212, 33, 193, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setBounds(10, 158, 86, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(116, 155, 86, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		listTelefonos = new JList();
		listTelefonos.setBounds(116, 186, 86, 117);
		getContentPane().add(listTelefonos);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(e -> agregarTelefono());
		btnAgregar.setBounds(212, 154, 89, 23);
		getContentPane().add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e -> eliminarTelefono());
		btnEliminar.setBounds(212, 186, 89, 23);
		getContentPane().add(btnEliminar);
		btnRegistrar.addActionListener(e -> registrarTrabajador(e));

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
		} catch (Exception e) {
			
		}
	}
	
	public void registrarTrabajador(ActionEvent e) {
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
		
		String fechaDeContrato = this.textFieldFechaDeContrato.getText().trim();
		if (new FechaGringaUtil().validarFechaGringa(fechaDeContrato) == false) {
			errores.add("Formato de fecha no valido");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
		
		List<String> telefonos = new ArrayList<>();
		
		for (int i = 0; i < mo.getSize(); i++) {
			telefonos.add(mo.getElementAt(i).toString());
		}
		
		if (errores.isEmpty() == true) {
			Trabajador tr = new Trabajador();
			tr.setRut(rut);
			tr.setNombre(nombre);
			tr.setApellidoPaterno(apellidoPaterno);
			tr.setApellidoMaterno(apellidoMaterno);
			tr.setFechaDeContrato(fechaDeContrato);
			
			TrabajadorDAO daoTrabajador = new TrabajadorDAO();
			if (daoTrabajador.save(tr) == true) {
				JOptionPane.showMessageDialog(null, "Trabajador registrado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El rut ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
			
			TelefonoTrabajadorDAO daoTelefonoCliente = new TelefonoTrabajadorDAO();		
			daoTelefonoCliente.save(telefonos, tr.getRut());
		}
	}
	
}
