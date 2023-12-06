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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoBoletaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoFacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoBoleta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoFactura;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarMetodoPagoBoletaFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listMetodoPagoBoleta;
	private JButton btnModificar;
	private JComboBox<String> comboBoxAtributos;
	private List<MetodoPagoBoleta> metodoPagoBoletas = new ArrayList<MetodoPagoBoleta>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarMetodoPagoBoletaFrame frame = new ModificarMetodoPagoBoletaFrame();
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
	public ModificarMetodoPagoBoletaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				
				metodoPagoBoletas = new MetodoPagoBoletaDAO().getAll();
				if (metodoPagoBoletas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay metodos de pago registrados en el sistema");
					dispose();
				}
				else {
					DefaultListModel mo = new DefaultListModel();
					
					for (MetodoPagoBoleta mpb : metodoPagoBoletas) {
						mo.addElement(mpb.toString());
					}
					
					listMetodoPagoBoleta.setModel(mo);
						
					comboBoxAtributos.addItem("Descripci�n");
				}
			}
		});
		setTitle("Modificar M\u00E9todo de Pago");
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
		btnModificar.setIcon(new ImageIcon(ModificarMetodoPagoBoletaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/set.png")));
		btnModificar.addActionListener(e -> modificarMetodo(e));
		btnModificar.setBounds(284, 32, 115, 23);
		getContentPane().add(btnModificar);
		
		listMetodoPagoBoleta = new JList();
		listMetodoPagoBoleta.setBounds(10, 67, 664, 337);
		getContentPane().add(listMetodoPagoBoleta);

	}

	private void modificarMetodo(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		try {
			boolean validado = false;
			String mensaje = "";
			MetodoPagoBoleta metodoPagoBoletaSeleccionado = metodoPagoBoletas.get(this.listMetodoPagoBoleta.getSelectedIndex());
			String atributoSeleccionado = (String)comboBoxAtributos.getSelectedItem();
			String valorModificado = textFieldModificar.getText();
			
			switch(atributoSeleccionado) {
			
			case "Descripci�n":
				metodoPagoBoletaSeleccionado.setDescripcion(valorModificado);
				if (metodoPagoBoletaSeleccionado.getDescripcion().isEmpty() != false) {
					mensaje = "- Descripci�n no valida";
				}
				else {
					validado = true;
				}
				break;
			}
			
			if (validado == true) {
				MetodoPagoBoletaDAO daoMetodoPagoBoleta = new MetodoPagoBoletaDAO();
				daoMetodoPagoBoleta.update(metodoPagoBoletaSeleccionado);
				JOptionPane.showMessageDialog(null, "M�todo de pago modificado correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "- No a seleccionado ning�n m�todo de pago", "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}		
	}
	
}
