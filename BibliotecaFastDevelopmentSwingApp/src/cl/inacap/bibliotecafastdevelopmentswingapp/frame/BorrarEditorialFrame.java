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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.EditorialDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Editorial;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarEditorialFrame extends JInternalFrame {
	private JComboBox<String> comboBoxEditorial;
	private List<Editorial> editoriales = new ArrayList<Editorial>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarEditorialFrame frame = new BorrarEditorialFrame();
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
	public BorrarEditorialFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				EditorialDAO daoEditorial = new EditorialDAO();
				editoriales = daoEditorial.getAll();
				
				if (editoriales.isEmpty() != true) {
					for(Editorial ed : editoriales) {
						comboBoxEditorial.addItem(ed.toString());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay editoriales registradas en el sistema");
					dispose();
				}

				
			}
		});
		setTitle("Borrar Editorial");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione la editorial que desea borrar");
		lblNewLabel.setBounds(227, 33, 298, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxEditorial = new JComboBox<String>();
		comboBoxEditorial.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxEditorial);
		
		JButton btnBorrarEditorial = new JButton("Borrar");
		btnBorrarEditorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarEditorial.setIcon(new ImageIcon(BorrarEditorialFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarEditorial.setBounds(289, 89, 115, 23);
		btnBorrarEditorial.addActionListener(e -> borrarEditorial(e));
		getContentPane().add(btnBorrarEditorial);

	}

	private void borrarEditorial(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Editorial editorialSeleccionada = editoriales.get(this.comboBoxEditorial.getSelectedIndex());
		
		if (new EditorialDAO().delete(editorialSeleccionada) == true) {
			JOptionPane.showMessageDialog(null, "Editorial borrada exitosamente");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "La editorial no se pudo borrar, ya que esta siendo usada en un libro","Error DB", JOptionPane.ERROR_MESSAGE);
		}
	}

}
