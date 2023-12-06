package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.AutorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Autor;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarAutorFrame extends JInternalFrame {
	private JTextField textFieldFechaDeNacimiento;
	private JTextField textFieldApellidoMaterno;
	private JTextField textFieldApellidoPaterno;
	private JTextField textFieldNombre;
	private JTextField textFieldCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarAutorFrame frame = new RegistrarAutorFrame();
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
	public RegistrarAutorFrame() {
		setTitle("Registrar Autor");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 33, 59, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 58, 59, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido paterno");
		lblApellidoPaterno.setBounds(10, 83, 104, 14);
		getContentPane().add(lblApellidoPaterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido materno");
		lblApellidoMaterno.setBounds(10, 108, 104, 14);
		getContentPane().add(lblApellidoMaterno);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(10, 133, 123, 14);
		getContentPane().add(lblFechaDeNacimiento);
		
		textFieldFechaDeNacimiento = new JTextField();
		textFieldFechaDeNacimiento.setBounds(132, 130, 86, 20);
		getContentPane().add(textFieldFechaDeNacimiento);
		textFieldFechaDeNacimiento.setColumns(10);
		
		textFieldApellidoMaterno = new JTextField();
		textFieldApellidoMaterno.setBounds(132, 105, 86, 20);
		getContentPane().add(textFieldApellidoMaterno);
		textFieldApellidoMaterno.setColumns(10);
		
		textFieldApellidoPaterno = new JTextField();
		textFieldApellidoPaterno.setBounds(132, 80, 86, 20);
		getContentPane().add(textFieldApellidoPaterno);
		textFieldApellidoPaterno.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(132, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(132, 30, 86, 20);
		getContentPane().add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnRegistrar.setIcon(new ImageIcon(RegistrarAutorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnRegistrar.addActionListener(e -> registrarAutor(e));
		btnRegistrar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnRegistrar);
		
		JLabel lblNewLabel = new JLabel("(2010-05-22)");
		lblNewLabel.setBounds(228, 133, 104, 14);
		getContentPane().add(lblNewLabel);

	}
	
	
	public void registrarAutor(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String codigo = this.textFieldCodigo.getText().trim();
		if(codigo.isEmpty()) {
			errores.add("C�digo no valido");
		}
		
		String nombre = this.textFieldNombre.getText().trim();
		if (nombre.isEmpty()) {
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
		
		String fechaDeNacimiento = this.textFieldFechaDeNacimiento.getText().trim();
		if (new FechaGringaUtil().validarFechaGringa(fechaDeNacimiento) == false) {
			errores.add("Formato de fecha no valido");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i<errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
		
		if (errores.isEmpty()) {
			Autor au = new Autor();
			au.setCodigo(codigo);
			au.setNombre(nombre);
			au.setApellidoPaterno(apellidoPaterno);
			au.setApellidoMaterno(apellidoMaterno);
			au.setFechaDeNacimiento(fechaDeNacimiento);
			
			AutorDAO daoAutor = new AutorDAO();
			if (daoAutor.save(au) == true) {
				JOptionPane.showMessageDialog(null, "Autor registrado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El c�digo ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
