package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EstadoLibroDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.EstadoLibro;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarEstadoLibroFrame extends JInternalFrame {
	private JComboBox<String> comboBoxEstadoLibro;
	private List<EstadoLibro> estadoLibros = new ArrayList<EstadoLibro>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarEstadoLibroFrame frame = new BorrarEstadoLibroFrame();
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
	public BorrarEstadoLibroFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				EstadoLibroDAO daoEstadoLibro = new EstadoLibroDAO();
				estadoLibros = daoEstadoLibro.getAll();
				
				if (estadoLibros.isEmpty() != true) {
					for (EstadoLibro el : estadoLibros) {
						comboBoxEstadoLibro.addItem(el.toString());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay estados registrados en el sistema");
					dispose();
				}

				
			}
		});
		setTitle("Borrar Estado");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione la estado que desea borrar");
		lblNewLabel.setBounds(236, 33, 298, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxEstadoLibro = new JComboBox<String>();
		comboBoxEstadoLibro.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxEstadoLibro);
		
		JButton btnBorrarEstadoLibro = new JButton("Borrar");
		btnBorrarEstadoLibro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarEstadoLibro.setIcon(new ImageIcon(BorrarEstadoLibroFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarEstadoLibro.setBounds(289, 89, 115, 23);
		btnBorrarEstadoLibro.addActionListener(e -> borrarEstadoLibro(e));
		getContentPane().add(btnBorrarEstadoLibro);

	}

	private void borrarEstadoLibro(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		EstadoLibro estadoLibroSeleccionado = estadoLibros.get(this.comboBoxEstadoLibro.getSelectedIndex());
		
		if (new EstadoLibroDAO().delete(estadoLibroSeleccionado) == true) {
			JOptionPane.showMessageDialog(null, "Estado borrado exitosamente");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "El estado no se pudo borrar, ya que esta siendo usado en un libro", "Error DB", JOptionPane.ERROR_MESSAGE);
		}
	}

}
