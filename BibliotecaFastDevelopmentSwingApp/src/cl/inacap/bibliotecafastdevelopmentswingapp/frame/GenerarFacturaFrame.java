package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.CompraDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.DistribuidorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.FacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoFacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Compra;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Distribuidor;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Factura;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoFactura;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GenerarFacturaFrame extends JInternalFrame {
	private JTextField textFieldFechaDeCompra;
	private JTextField textFieldHoraDeCompra;
	private JTextField textFieldFolio;
	private JComboBox<String> comboBoxMetodoDePago;
	private JComboBox<String> comboBoxDistribuidor;
	private List<Compra> compras = new ArrayList<Compra>();
	private List<Factura> facturas = new ArrayList<Factura>();
	private JComboBox<Compra> comboBoxCompra;
	private JLabel lblPrecioNeto;
	private JLabel lblPrecioConIVA;
	private double precioNeto;
	private Compra compraComboBoxSeleccionada = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarFacturaFrame frame = new GenerarFacturaFrame();
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
	public GenerarFacturaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Distribuidor> distribuidores = new DistribuidorDAO().getAll();
				List<MetodoPagoFactura> metodoPagoFacturas = new MetodoPagoFacturaDAO().getAll();
				facturas = new FacturaDAO().getAll();
				compras = new CompraDAO().getAll();
				
				if (distribuidores.isEmpty() == true || metodoPagoFacturas.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Debe haber un distribuidor y un m�todo de pago minimo registrados en el sistema para poder generar una factura");
					dispose();
				}
				
				for (Distribuidor di : distribuidores) {
					comboBoxDistribuidor.addItem(di.getRut());
				}
				
				for (MetodoPagoFactura mpf : metodoPagoFacturas) {
					comboBoxMetodoDePago.addItem(mpf.getCodigo());
				}
				
				int trueFalse = 0;
				int contadorIteraciones = 0;
				
				for (Compra co : compras) {
					if (facturas.isEmpty() == true) {
						comboBoxCompra.addItem(co);
					}
					else {
						trueFalse = 0;
						contadorIteraciones = 0;
						for (Factura fa : facturas) {
							contadorIteraciones++;
							if (co.getId( )== fa.getCompraFK()) {
								trueFalse = 1;
								
							}
							if (trueFalse == 0 && contadorIteraciones == facturas.size()) {
								comboBoxCompra.addItem(co);
							}
						}	
					}		
				}	
			}
		});
		setTitle("Generar Factura");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Folio");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio neto");
		lblNewLabel_1.setBounds(10, 58, 80, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio con IVA");
		lblNewLabel_2.setBounds(10, 83, 95, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Costo del IVA");
		lblNewLabel_3.setBounds(10, 108, 80, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha de compra");
		lblNewLabel_4.setBounds(10, 133, 111, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Hora de compra");
		lblNewLabel_5.setBounds(10, 158, 111, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("M\u00E9todo de pago");
		lblNewLabel_6.setBounds(10, 183, 95, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Distribuidor");
		lblNewLabel_7.setBounds(10, 208, 80, 14);
		getContentPane().add(lblNewLabel_7);
		
		textFieldFechaDeCompra = new JTextField();
		textFieldFechaDeCompra.setBounds(110, 130, 86, 20);
		getContentPane().add(textFieldFechaDeCompra);
		textFieldFechaDeCompra.setColumns(10);
		
		textFieldHoraDeCompra = new JTextField();
		textFieldHoraDeCompra.setBounds(110, 155, 86, 20);
		getContentPane().add(textFieldHoraDeCompra);
		textFieldHoraDeCompra.setColumns(10);
		
		textFieldFolio = new JTextField();
		textFieldFolio.setBounds(110, 30, 86, 20);
		getContentPane().add(textFieldFolio);
		textFieldFolio.setColumns(10);
		
		comboBoxMetodoDePago = new JComboBox<String>();
		comboBoxMetodoDePago.setBounds(110, 180, 86, 20);
		getContentPane().add(comboBoxMetodoDePago);
		
		comboBoxDistribuidor = new JComboBox<String>();
		comboBoxDistribuidor.setBounds(110, 205, 172, 20);
		getContentPane().add(comboBoxDistribuidor);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnGenerar.setIcon(new ImageIcon(GenerarFacturaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnGenerar.addActionListener(e -> generarFactura(e));
		btnGenerar.setBounds(559, 381, 115, 23);
		getContentPane().add(btnGenerar);
		
		JLabel lblNewLabel_8 = new JLabel("(2010-05-22)");
		lblNewLabel_8.setBounds(206, 133, 76, 14);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("(23:44:48)");
		lblNewLabel_9.setBounds(206, 158, 76, 14);
		getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Compra");
		lblNewLabel_10.setBounds(10, 233, 62, 14);
		getContentPane().add(lblNewLabel_10);
		
		comboBoxCompra = new JComboBox<Compra>();
		comboBoxCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(compraComboBoxSeleccionada != null) {
					lblPrecioNeto.setText("Oculto");
					lblPrecioConIVA.setText("Oculto");		
				}
			}
		});
		comboBoxCompra.setBounds(110, 230, 86, 20);
		getContentPane().add(comboBoxCompra);
		
		JLabel lblNewLabel_11 = new JLabel("19%");
		lblNewLabel_11.setBounds(110, 108, 46, 14);
		getContentPane().add(lblNewLabel_11);
		
		lblPrecioNeto = new JLabel("Oculto");
		lblPrecioNeto.setBounds(110, 58, 239, 14);
		getContentPane().add(lblPrecioNeto);
		
		lblPrecioConIVA = new JLabel("Oculto");
		lblPrecioConIVA.setBounds(110, 83, 239, 14);
		getContentPane().add(lblPrecioConIVA);
		
		JLabel lblNewLabel_12 = new JLabel("Datos ocultos");
		lblNewLabel_12.setForeground(Color.RED);
		lblNewLabel_12.setBounds(10, 258, 80, 14);
		getContentPane().add(lblNewLabel_12);
		
		JButton btnMostrarDatosOcultos = new JButton("Mostrar");
		btnMostrarDatosOcultos.addActionListener(e -> mostrarDatosOcultos(e));
		btnMostrarDatosOcultos.setBounds(110, 254, 89, 23);
		getContentPane().add(btnMostrarDatosOcultos);

	}

	private void mostrarDatosOcultos(ActionEvent e) {	
		precioNeto = ((Compra)this.comboBoxCompra.getSelectedItem()).getPrecio();
		compraComboBoxSeleccionada = (Compra)this.comboBoxCompra.getSelectedItem();
		lblPrecioNeto.setText(Double.toString(precioNeto));
		lblPrecioConIVA.setText(Double.toString(precioNeto * 1.19));
	}

	private void generarFactura(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String folio = this.textFieldFolio.getText().trim();
		if (folio.isEmpty()) {
			errores.add("Folio no valido");
		}
		
		double costoIVA = 19;
		
		String fechaDeCompra = this.textFieldFechaDeCompra.getText().trim();
		if (new FechaGringaUtil().validarFechaGringa(fechaDeCompra) == false) {
			errores.add("Formato de fecha no valido");
		}
		
		String horaDeCompra = this.textFieldHoraDeCompra.getText().trim();
		String metodoDePagoFactura = this.comboBoxMetodoDePago.getSelectedItem().toString();
		String distribuidor = this.comboBoxDistribuidor.getSelectedItem().toString();
		
		int compra = 0;
		try {
			compra = Integer.parseInt(this.comboBoxCompra.getSelectedItem().toString());
		} catch (Exception ex) {
			errores.add("Ninguna compra seleccionada");
		}
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i=0 ; i<errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
			
		if (errores.isEmpty() == true) {
			Factura fa = new Factura();
			fa.setFolio(folio);
			fa.setPrecioNeto(precioNeto);
			fa.setPrecioConIVA(precioNeto * 1.19);
			fa.setCostoIVA(costoIVA);
			fa.setFechaDeCompra(fechaDeCompra);
			fa.setHoraDeCompra(horaDeCompra);
			fa.setMetodoDePagoFK(metodoDePagoFactura);
			fa.setDistribuidorFK(distribuidor);
			fa.setCompraFK(compra);
			
			FacturaDAO daoFactura = new FacturaDAO();
			if (daoFactura.save(fa)) {
				JOptionPane.showMessageDialog(null, "Factura generada correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El folio ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	
}
