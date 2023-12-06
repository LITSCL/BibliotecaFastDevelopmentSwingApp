package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.IdiomaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Idioma;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarIdiomaFrame extends JInternalFrame {
	private JButton btnBorrarIdioma;
	private JComboBox<String> comboBoxIdioma;
	private List<Idioma> idiomas = new ArrayList<Idioma>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarIdiomaFrame frame = new BorrarIdiomaFrame();
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
	public BorrarIdiomaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				idiomas = new IdiomaDAO().getAll();
				
				if (idiomas.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "No hay idiomas registrados en el sistema");
					dispose();
				}
				else {
					for(Idioma id : idiomas) {
						comboBoxIdioma.addItem(id.toString());
					}
				}
				
			}
		});
		setTitle("Borrar Idioma");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el idioma que desea borrar");
		lblNewLabel.setBounds(229, 33, 272, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxIdioma = new JComboBox<String>();
		comboBoxIdioma.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxIdioma);
		
		btnBorrarIdioma = new JButton("Borrar");
		btnBorrarIdioma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarIdioma.setIcon(new ImageIcon(BorrarIdiomaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarIdioma.addActionListener(e -> borrarIdioma(e));
		btnBorrarIdioma.setBounds(289, 89, 115, 23);
		getContentPane().add(btnBorrarIdioma);

	}

	private void borrarIdioma(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Idioma idiomaSeleccionado = idiomas.get(this.comboBoxIdioma.getSelectedIndex());
		
		IdiomaDAO daoIdioma = new IdiomaDAO();
		daoIdioma.delete(idiomaSeleccionado);
	
		JOptionPane.showMessageDialog(null, "Idioma borrado exitosamente");
		dispose();
	}

}
