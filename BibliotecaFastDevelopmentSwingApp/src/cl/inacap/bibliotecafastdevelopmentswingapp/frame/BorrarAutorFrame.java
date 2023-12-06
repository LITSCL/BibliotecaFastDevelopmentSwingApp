package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.AutorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Autor;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarAutorFrame extends JInternalFrame {
	private JComboBox<String> comboBoxAutores;
	private List<Autor> autores = new ArrayList<Autor>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarAutorFrame frame = new BorrarAutorFrame();
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
	public BorrarAutorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				autores = new AutorDAO().getAll();
				
				if (autores.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay autores registrados en el sistema");
					dispose();
				}
				else {
					for (Autor au : autores) {
						comboBoxAutores.addItem(au.toString());
					}	
				}

				
			}
		});
		setTitle("Borrar Autor");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el autor que desea borrar");
		lblNewLabel.setBounds(241, 33, 249, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxAutores = new JComboBox<String>();
		comboBoxAutores.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxAutores);
		
		JButton btnBorrarAutor = new JButton("Borrar");
		btnBorrarAutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarAutor.setIcon(new ImageIcon(BorrarAutorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarAutor.addActionListener(e -> borrarAutor(e));
		btnBorrarAutor.setBounds(289, 89, 115, 23);
		getContentPane().add(btnBorrarAutor);

	}

	private void borrarAutor(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Autor autorSeleccionado = autores.get(comboBoxAutores.getSelectedIndex());
		
		AutorDAO daoAutor = new AutorDAO();
		daoAutor.delete(autorSeleccionado);
		
		dispose();
	}

}
