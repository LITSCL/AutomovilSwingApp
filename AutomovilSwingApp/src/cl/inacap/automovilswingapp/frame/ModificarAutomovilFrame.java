package cl.inacap.automovilswingapp.frame;


import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.automovilswingappmodelo.dao.AutomovilDAO;
import cl.inacap.automovilswingappmodelo.dto.Automovil;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ModificarAutomovilFrame extends JInternalFrame {
	private JTextField textFieldModificar;
	private JList listAutomoviles;
	static AutomovilDAO daoAutomovil = new AutomovilDAO();
	private JComboBox<String> comboBoxModificar;

	//Proceso de creaci�n del frame.
	public ModificarAutomovilFrame() {
		setTitle("Modificar Automoviles"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			
			//Todo este c�digo se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JList y el JComboBox).
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Automovil> automovilesIngresados = new AutomovilDAO().getAll(); //En esta lista se almacenan todos los automoviles ingresados.

				if (automovilesIngresados.isEmpty() == true) { //Aca se revisa si hay automoviles en la lista, de no ser as� se ejecuta el c�digo.
					JOptionPane.showMessageDialog(null, "No hay automoviles registrados en el sistema"); //El primer par�metro corresponde a la ubicac�n del mensaje y el segundo par�metro corresponde al texto del mensaje.
					dispose(); //Esta instrucci�n cierra la ventana (JInternal Frame llamado (Eliminar Automovil)).
				}
				
				DefaultListModel listAutomovilesSeleccionar = new DefaultListModel(); //Se crea un objeto de tipo DefaultListModel.
				
				for (int i = 0; i < automovilesIngresados.size(); i++) { //Aca se recorre la lista de automoviles.
					listAutomovilesSeleccionar.addElement(automovilesIngresados.get(i).toString()); //Aca se a�aden los automoviles al JList.
				}

				listAutomoviles.setModel(listAutomovilesSeleccionar); //Aca se a�aden todos los textos al componente JList.
				
				comboBoxModificar.addItem("Patente");
				comboBoxModificar.addItem("Nombre de contacto");
				comboBoxModificar.addItem("Kilometraje");
			}
		});
		
		setBounds(100, 100, 700, 445);
		setClosable(true);
		getContentPane().setLayout(null);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this::ModificarAutomovil); //Aca se a�ade el Listener del JButton llamado (Modificar) el cual llama al m�todo llamado ModificarAutomovil().
		btnModificar.setBounds(363, 9, 89, 23);
		getContentPane().add(btnModificar);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setBounds(10, 10, 135, 20);
		getContentPane().add(textFieldModificar);
		textFieldModificar.setColumns(10);
		
		comboBoxModificar = new JComboBox<>();
		comboBoxModificar.setBounds(155, 10, 198, 20);
		getContentPane().add(comboBoxModificar);
		
		listAutomoviles = new JList();
		listAutomoviles.setBounds(10, 43, 664, 361);
		getContentPane().add(listAutomoviles);
	}
		
		//C�digo a ejecutar del Listener del componente JButton llamado (Modificar).
		private void ModificarAutomovil(ActionEvent e) {
			List<Automovil> automovilesIngresados = new AutomovilDAO().getAll(); //En esta lista se almacenan todos los automoviles ingresados.
			try {
				
				Automovil automovilSeleccionado = automovilesIngresados.get(listAutomoviles.getSelectedIndex()); //Se obtiene el objeto de la lista utilizando el indice de lo que el usuario selecciono en el JList y se almacena en una variable.
				if (comboBoxModificar.getSelectedItem().equals("Patente")) { //Si el valor del JComboBox es igual a Patente, entonces ejecuta dicho c�digo.
					if (textFieldModificar.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField", "Error de validaci�n", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
					}
					else {
						String modificar = textFieldModificar.getText(); //Se almacena el valor del JTextField en una variable.
						automovilSeleccionado.setPatente(modificar); //Aca se modifica su atributo.
						daoAutomovil.update(automovilSeleccionado); //Aca se selecciona el objeto y se manda a actualizar.
					
					}
					
				}
				else if (comboBoxModificar.getSelectedItem().equals("Nombre de contacto")) { //Si el valor del JComboBox es igual a Patente, entonces ejecuta dicho c�digo.
					if (textFieldModificar.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField", "Error de validaci�n", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
					}
					else {
						String modificar = textFieldModificar.getText(); //Se almacena el valor del JTextField en una variable.
						automovilSeleccionado.setNombreDeContacto(modificar); //Aca se modifica su atributo.
						daoAutomovil.update(automovilSeleccionado); //Aca se selecciona el objeto y se manda a actualizar.
					}
					
				}
				else if (comboBoxModificar.getSelectedItem().equals("Kilometraje")) { //Si el valor del JComboBox es igual a Patente, entonces ejecuta dicho c�digo.
					if (textFieldModificar.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField", "Error de validaci�n", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
					}
					else {
						try {
							int modificar = Integer.parseInt(textFieldModificar.getText()); //Se almacena el valor del JTextField en una variable (Se hace una refundici�n de dato).
							if (modificar > 30) {
								automovilSeleccionado.setKilometraje(modificar); //Aca se modifica su atributo.
								daoAutomovil.update(automovilSeleccionado); //Aca se selecciona el objeto y se manda a actualizar.
							}
							else {
								JOptionPane.showMessageDialog(null, "- El kilometraje es 30 o menor","Error de validaci�n", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
							}
							
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "- Ingres� un valor no valido","Error de validaci�n", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
						}
						
					}
					
				}
				
				//Proceso de refrescado del JList.
				DefaultListModel listAutomovilesSeleccionar = new DefaultListModel(); //Se crea un objeto de tipo DefaultListModel.
				
				for (int i = 0; i < automovilesIngresados.size(); i++) { //Aca se recorre la lista de automoviles.
					listAutomovilesSeleccionar.addElement(automovilesIngresados.get(i).toString()); //Aca se a�aden los automoviles al JList.
				}
				listAutomoviles.setModel(listAutomovilesSeleccionar); //Aca se a�aden todos los textos al componente JList.
			
			} catch (Exception ex) {
				if (textFieldModificar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "- No hay nada escrito en el JTextField \n- No seleccion� ning�n autom�vil ", "Error de validaci�n", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
				}
				else {
					JOptionPane.showMessageDialog(null, "- No seleccion� ning�n autom�vil", "Error de validaci�n", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
				}
			}
		}
		
}

