package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CategoriaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarCategoriaFrame extends JInternalFrame {
	private JComboBox<String> comboBoxCategoria;
	private JButton btnBorrarCategoria;
	private List<Categoria> categorias = new ArrayList<Categoria>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarCategoriaFrame frame = new BorrarCategoriaFrame();
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
	public BorrarCategoriaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				categorias = new CategoriaDAO().getAll();
				
				if (categorias.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay categorias registradas en el sistema");
					dispose();
				}
				else {
					for (Categoria ca : categorias) {
						comboBoxCategoria.addItem(ca.toString());
					}
				}

				
				
			}
		});
		setTitle("Borrar Categor�a");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar la categoria que desea borrar");
		lblNewLabel.setBounds(221, 33, 293, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxCategoria = new JComboBox<String>();
		comboBoxCategoria.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxCategoria);
		
		btnBorrarCategoria = new JButton("Borrar");
		btnBorrarCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarCategoria.setIcon(new ImageIcon(BorrarCategoriaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarCategoria.setBounds(289, 89, 115, 23);
		btnBorrarCategoria.addActionListener(e -> borrarCategoria(e));
		getContentPane().add(btnBorrarCategoria);

	}
	
	private void borrarCategoria(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Categoria categoriaSeleccionada = categorias.get(this.comboBoxCategoria.getSelectedIndex());
		
		new CategoriaDAO().delete(categoriaSeleccionada);
		
		JOptionPane.showMessageDialog(null, "Categor�a borrada exitosamente");
		dispose();
	}

}
