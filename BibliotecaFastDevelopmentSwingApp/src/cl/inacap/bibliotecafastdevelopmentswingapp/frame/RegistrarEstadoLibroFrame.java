package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarEstadoLibroFrame extends JInternalFrame {
	private JTextField textFieldDescripcion;
	private JTextField textFieldCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarEstadoLibroFrame frame = new RegistrarEstadoLibroFrame();
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
	public RegistrarEstadoLibroFrame() {
		setTitle("Registrar Estado");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(10, 58, 106, 14);
		getContentPane().add(lblDescripcin);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(82, 55, 86, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(82, 30, 86, 20);
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
		btnRegistrar.setIcon(new ImageIcon(RegistrarEstadoLibroFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnRegistrar.addActionListener(e -> registrarEstadoLibro(e));
		btnRegistrar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnRegistrar);
		
		JLabel lblNewLabel_1 = new JLabel("(No disponible; Disponible; Vendido; Arrendado)");
		lblNewLabel_1.setBounds(178, 58, 299, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Importante: Si usted desea registrar un libro, debe existir el estado con descripci\u00F3n \"No disponible\".");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(10, 83, 597, 14);
		getContentPane().add(lblNewLabel_2);

	}
	
	public void registrarEstadoLibro(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String codigo = this.textFieldCodigo.getText().trim();
		if (codigo.isEmpty()) {
			errores.add("C�digo no valido");
		}
		
		String descripcion = this.textFieldDescripcion.getText().trim();
		if (descripcion.isEmpty()) {
			errores.add("Descripci�n no valida");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
		
		if (errores.isEmpty()) {
			EstadoLibro es = new EstadoLibro();
			es.setCodigo(codigo);
			es.setDescripcion(descripcion);
			
			EstadoLibroDAO daoEstadoLibro = new EstadoLibroDAO();
			if (daoEstadoLibro.save(es)) {
				JOptionPane.showMessageDialog(null, "Estado registrado correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El c�digo (Y/O) la descripci�n ya fueron ingresados anteriormente, ingrese datos diferentes", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
