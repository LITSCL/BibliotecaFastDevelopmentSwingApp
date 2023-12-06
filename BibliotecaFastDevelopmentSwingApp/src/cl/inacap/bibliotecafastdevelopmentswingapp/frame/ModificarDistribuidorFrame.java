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
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.DistribuidorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Categoria;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Distribuidor;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.TelefonoUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarDistribuidorFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listDistribuidores;
	private JButton btnModificar;
	private JComboBox<String> comboBoxAtributos;
	private List<Distribuidor> distribuidores = new ArrayList<Distribuidor>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarDistribuidorFrame frame = new ModificarDistribuidorFrame();
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
	public ModificarDistribuidorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				distribuidores = new DistribuidorDAO().getAll();
				if(distribuidores.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay distribuidores registrados en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (Distribuidor di : distribuidores) {
						mo.addElement(di.toString());
					}
					
					listDistribuidores.setModel(mo);
					
					comboBoxAtributos.addItem("Nombre");
					comboBoxAtributos.addItem("Pa�s");
					comboBoxAtributos.addItem("Comuna");
					comboBoxAtributos.addItem("Calle");
					comboBoxAtributos.addItem("N�mero");
					comboBoxAtributos.addItem("Telefono");
					comboBoxAtributos.addItem("A�os de servicio");
					
				}
			}
		});
		setTitle("Modificar Distribuidor");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setBounds(10, 33, 127, 20);
		getContentPane().add(textFieldModificar);
		textFieldModificar.setColumns(10);
		
		comboBoxAtributos = new JComboBox<String>();
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
		btnModificar.setIcon(new ImageIcon(ModificarDistribuidorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarDistribuidor(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listDistribuidores = new JList();
		listDistribuidores.setBounds(10, 67, 664, 337);
		getContentPane().add(listDistribuidores);

	}

	private void modificarDistribuidor(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			String mensaje = "";
			Distribuidor distribuidorSeleccionado = distribuidores.get(listDistribuidores.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText();
			
			switch (atributoSeleccionado) {
			case "Nombre":
				distribuidorSeleccionado.setNombre(valorModificado);
				if (distribuidorSeleccionado.getNombre().trim().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Nombre no valido";
				}
				break;
			case "Pa�s":
				distribuidorSeleccionado.setPais(valorModificado);
				if (distribuidorSeleccionado.getPais().trim().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Pa�s no valido";
				}
				break;
			case "Comuna":
				distribuidorSeleccionado.setComuna(valorModificado);
				if(distribuidorSeleccionado.getComuna().trim().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Comuna no valida";
				}
				break;
			case "Calle":
				distribuidorSeleccionado.setCalle(valorModificado);
				if(distribuidorSeleccionado.getCalle().trim().isEmpty() == false) {
					validado = true;
				}
				else {
					mensaje = "- Calle no valida";
				}
				break;
			case "N�mero":
				distribuidorSeleccionado.setNumero(valorModificado);
				try {
					int numeroInt = Integer.parseInt(distribuidorSeleccionado.getNumero());
				} catch (Exception ex) {
					mensaje = "- N�mero no valido";
				}
				break;
			case "Telefono":
				distribuidorSeleccionado.setTelefono(valorModificado);
				if (new TelefonoUtil().validarTelefono(distribuidorSeleccionado.getTelefono())==true) {
					validado = true;
				}else {
					mensaje = "- Tel�fono no valido";
				}
				break;
			case "A�os de servicio":
				distribuidorSeleccionado.setYearServicio(valorModificado);
				if (new FechaGringaUtil().validarFechaGringa(distribuidorSeleccionado.getYearServicio()) == true) {
					validado = true;
				}else {
					mensaje = "- Fecha no valida";
				}
				break;
			}
			
			if (validado == true) {
				new DistribuidorDAO().update(distribuidorSeleccionado);
				JOptionPane.showMessageDialog(null, "Distribuidor modificado correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ning�n distribuidor", "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
	}

}
