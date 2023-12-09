package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TelefonoTrabajadorDAO;
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

public class AgregarTelefonoTrabajadorPopUpFrame extends JInternalFrame {
	private JLabel lblRutTrabajador;
	private JTextField textFieldTelefono;
	private JButton btnAgregar;
	private TelefonoUtil tu = new TelefonoUtil();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarTelefonoTrabajadorPopUpFrame frame = new AgregarTelefonoTrabajadorPopUpFrame();
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
	public AgregarTelefonoTrabajadorPopUpFrame() {
		setTitle("Agregar Telefono");
		setClosable(true);
		setBounds(100, 100, 250, 300);
		getContentPane().setLayout(null);
		
		lblRutTrabajador = new JLabel("Rut: " + MostrarTrabajadorFrame.rutSeleccionado);
		lblRutTrabajador.setBounds(10, 11, 214, 14);
		getContentPane().add(lblRutTrabajador);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(77, 36, 86, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnAgregar.setIcon(new ImageIcon(AgregarTelefonoTrabajadorPopUpFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoBoton", "wav");
				
				String telefono = textFieldTelefono.getText().trim();
				
				if (tu.validarTelefono(telefono) == true) {
					TelefonoTrabajadorDAO daoTelefonoTrabajador = new TelefonoTrabajadorDAO();
					List<String> telefonos = new ArrayList<>();
					telefonos.add(telefono);
					daoTelefonoTrabajador.save(telefonos, MostrarTrabajadorFrame.rutSeleccionado);
					MostrarTrabajadorFrame.moList.addElement(telefono);
					MostrarTrabajadorFrame.listTelefono.setModel(MostrarTrabajadorFrame.moList);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "El número de telefono debe tener el formato: 123456789");
				}	
			}
		});
		btnAgregar.setBounds(109, 236, 115, 23);
		getContentPane().add(btnAgregar);

	}
}
