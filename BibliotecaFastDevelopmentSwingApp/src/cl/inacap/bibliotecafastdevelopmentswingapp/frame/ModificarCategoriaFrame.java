package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CategoriaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarCategoriaFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listCategorias;
	private JButton btnModificar;
	private JComboBox comboBoxAtributos;
	private List<Categoria> categorias = new ArrayList<Categoria>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarCategoriaFrame frame = new ModificarCategoriaFrame();
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
	public ModificarCategoriaFrame() {	
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				categorias = new CategoriaDAO().getAll();
				if (categorias.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay categorias registradas en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (Categoria ca : categorias) {
						mo.addElement(ca.toStringV2());
					}
					
					listCategorias.setModel(mo);
					
					comboBoxAtributos.addItem("Nombre");	
				}
			}
		});
		setTitle("Modificar Categor�a");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setBounds(10, 33, 127, 20);
		getContentPane().add(textFieldModificar);
		textFieldModificar.setColumns(10);
		
		comboBoxAtributos = new JComboBox();
		comboBoxAtributos.setBounds(147, 33, 127, 20);
		getContentPane().add(comboBoxAtributos);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnModificar.setIcon(new ImageIcon(ModificarCategoriaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarCategoria(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listCategorias = new JList();
		listCategorias.setBounds(10, 67, 664, 337);
		getContentPane().add(listCategorias);

	}

	private void modificarCategoria(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			Categoria categoriaSeleccionada = categorias.get(listCategorias.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText();
			
			switch (atributoSeleccionado) {
			case "Nombre":
				categoriaSeleccionada.setNombre(valorModificado);
				if (categoriaSeleccionada.getNombre().trim().isEmpty() == false) {
					validado = true;
				}
				break;
			}
			
			if (validado == true) {
				new CategoriaDAO().update(categoriaSeleccionada);
				JOptionPane.showMessageDialog(null, "Categor�a modificada correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "- Nombre no valido", "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ninguna categor�a", "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
			
		}
	}
	
}

