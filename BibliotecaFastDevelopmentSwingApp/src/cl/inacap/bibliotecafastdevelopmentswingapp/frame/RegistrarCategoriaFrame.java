package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CategoriaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RegistrarCategoriaFrame extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarCategoriaFrame frame = new RegistrarCategoriaFrame();
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
	public RegistrarCategoriaFrame() {
		setTitle("Registrar Categor�a");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 58, 52, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(58, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(58, 30, 86, 20);
		getContentPane().add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnIngresar.setIcon(new ImageIcon(RegistrarCategoriaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnIngresar.addActionListener(e -> ingresarCategoria(e));
		btnIngresar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnIngresar);
	}
	
	public void ingresarCategoria(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String codigo = this.textFieldCodigo.getText().trim();
		if (codigo.isEmpty()) {
			errores.add("C�digo no valido");
		}
		
		String nombre = this.textFieldNombre.getText().trim();
		if (nombre.isEmpty()) {
			errores.add("Nombre no valido");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i = 0 ; i<errores.size() ; i++) {
				mensaje+= "\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
		
		if (errores.isEmpty()) {
			Categoria ca = new Categoria();
			ca.setCodigo(codigo);
			ca.setNombre(nombre);
			
			CategoriaDAO daoCategoria = new CategoriaDAO();
			if (daoCategoria.save(ca)) {
				JOptionPane.showMessageDialog(null, "Categor�a registrada correctamente");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El c�digo (Y/O) el nombre ya fueron ingresados anteriormente, ingrese datos diferentes", "Error DB", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
	
}
