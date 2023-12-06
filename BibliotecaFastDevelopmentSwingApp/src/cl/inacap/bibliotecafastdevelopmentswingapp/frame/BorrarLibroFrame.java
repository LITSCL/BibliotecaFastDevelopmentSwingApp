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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.LibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Libro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarLibroFrame extends JInternalFrame {
	private JComboBox<String> comboBoxBorrarLibro;
	private List<Libro> libros = new ArrayList<Libro>();
	private JButton btnBorrarLibro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarLibroFrame frame = new BorrarLibroFrame();
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
	public BorrarLibroFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				libros = new LibroDAO().getAll();
				
				if (libros.size() == 0) {
					JOptionPane.showMessageDialog(null, "No hay libros registrados en el sistema");
					dispose();
				}
				else {
					for (Libro li : libros) {
						comboBoxBorrarLibro.addItem(li.toString());
					}
				}
				

			}
		});
		setTitle("Borrar Libro");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el libro que desea borrar");
		lblNewLabel.setBounds(232, 33, 251, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxBorrarLibro = new JComboBox<String>();
		comboBoxBorrarLibro.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxBorrarLibro);
		
		btnBorrarLibro = new JButton("Borrar");
		btnBorrarLibro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarLibro.setIcon(new ImageIcon(BorrarLibroFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarLibro.addActionListener(e -> borrarLibro(e));
		btnBorrarLibro.setBounds(289, 89, 115, 23);
		getContentPane().add(btnBorrarLibro);

	}

	private void borrarLibro(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Libro libroSeleccionado = libros.get(this.comboBoxBorrarLibro.getSelectedIndex());
		
		LibroDAO daoLibro = new LibroDAO();
		daoLibro.delete(libroSeleccionado);
		
		dispose();
	}

}
