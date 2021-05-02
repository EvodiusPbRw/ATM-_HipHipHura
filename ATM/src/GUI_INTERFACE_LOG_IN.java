import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI_INTERFACE_LOG_IN {

	public JFrame frame;
	public JFrame frmLogin;
	private JTextField textFieldID;
	private JPasswordField passwordField;
	int attempts = 3;
	int xMouse;
	int yMouse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_INTERFACE_LOG_IN window = new GUI_INTERFACE_LOG_IN();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_INTERFACE_LOG_IN() {
		initialize();
	}
	
	/*
	 * Create Function of Data
	 */
	
	public boolean dataReturn(String id, String password) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			String j;
			int i =1;
			while(i<=list.size()) {
				j = Integer.toString(i);
				if(list.get(j).getNoRek() == null) {
					break;
				}
				if(list.get(j).getNoRek().equals(id) && list.get(j).getPassword().equals(password)) {
					return true;
				}
				i++;
			}
		}catch(Exception e) {
			System.out.println("Gagal");
		}
		return false;
	}
	
	/*
	 * Data Exist or not
	 */
	public boolean dataExist(String id) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			String j ;
			int i =1;
			while(i<=list.size()) {
				j = Integer.toString(i);
				if(list.get(j).getNoRek().equals(id)) {
					return true;
				}
				i++;
			}
		}catch(Exception e) {

		}
		return false;
	}
	
	/*
	 * Buat cek 
	 */
	
	public String dataReturn2(String id, String password) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			String j ;
			int i =1;
			while(i<list.size()) {
				j = Integer.toString(i);
				if(list.get(j).getNoRek().equals(id) && list.get(j).getPassword().equals(password)) {
					return list.get(j).getNama();
				}
				i++;
			}
		}catch(Exception e) {
			//System.out.println("Gagal");
		}
		return list.get("9").getNama();
	}
	
	/*
	 * Set blokir 
	 */
	
	public void setBlokir(String id) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			list.get(id).setStatusBlokir(true);
			FileOutputStream fos = new FileOutputStream("database2");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			fos.close();oos.close();fis.close();ois.close();
		}catch(Exception e) {
			
		}
	}
	
	/*
	 * Check Blokir
	 */
	
	public boolean cekBlokir(String id) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			return list.get(id).getStatusBlokir();
		}catch(Exception e) {
			
		}
		return false;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frmLogin = new JFrame();
		frmLogin.setFocusTraversalPolicyProvider(true);
		frmLogin.setResizable(false);
		frmLogin.setUndecorated(true);
		frmLogin.setBackground(Color.WHITE);
		frmLogin.setTitle("Login");
		Color setBackground = Color.decode("#0C3B97");
		frmLogin.getContentPane().setBackground(setBackground);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setBounds(100, 100, 423, 375);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblID = new JLabel("ID ");
		lblID.setFont(new Font("Calibri",Font.BOLD,15));
		lblID.setForeground(Color.white);
		lblID.setBounds(130, 123, 63, 22);
		frmLogin.getContentPane().add(lblID);
		
		JLabel lblPassword = new JLabel("Password  ");
		lblPassword.setFont(new Font("Calibri",Font.BOLD,15));
		lblPassword.setForeground(Color.white);
		lblPassword.setBounds(130, 188, 73, 26);
		frmLogin.getContentPane().add(lblPassword);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(130, 155, 166, 26);
		frmLogin.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 217, 166, 26);
		frmLogin.getContentPane().add(passwordField);
		
		JLabel textAlert = new JLabel("");
		textAlert.setFont(new Font("Consolas", Font.BOLD, 15));
		textAlert.setForeground(new Color(255, 0, 102));
		textAlert.setBounds(129, 262, 93, 22);
		frmLogin.getContentPane().add(textAlert);
		
		JButton btnMasuk = new JButton("Masuk");
		btnMasuk.setForeground(new Color(255, 255, 255));
		btnMasuk.setBackground(new Color(51, 204, 153));
		btnMasuk.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				String ID= textFieldID.getText();
				String Password = passwordField.getText();	
				if(ID.equals("") || Password.equals("")) {
					JOptionPane.showMessageDialog(frame, "ID dan Password tidak boleh kosong!!");
				}
				else {
					if(dataExist(ID) == false) {
						JOptionPane.showMessageDialog(frame, "ID dengan No yang anda inputkan tidak ada");
					}
					else {
						while(attempts != 0 ) {
							if(dataReturn(ID,Password) == true) {
								if(cekBlokir(ID) == true) {
									JOptionPane.showMessageDialog(frame, "Akun dengan nomor " + ID + " telah diblokir\n"
											+ "untuk info lebih lanjut hubungi Costumer Service kami");
									break;
								}
								else {
									frmLogin.dispose();
									GUI_INTERFACE_LOGGED_IN log = new GUI_INTERFACE_LOGGED_IN(ID);
									log.frame.setVisible(true);
									log.frame.setResizable(false);
									break;
								}
							}
							else {
								attempts--;
								textAlert.setText("attempts: " + attempts);
								JOptionPane.showMessageDialog(frame, "ID atau Password anda salah silakan ulangi lagi");
								break;
							}
						}
					}
				}
				
				if(attempts == 0) {
					setBlokir(ID);
					JOptionPane.showMessageDialog(frame, "Akun dengan nomor " + ID + " telah diblokir\n"
							+ "untuk info lebih lanjut hubungi Costumer Service kami");
					textAlert.setText("attempts: ");
					attempts = 3;
					
				}
				
			}
		});
		btnMasuk.setBounds(223, 263, 73, 21);
		frmLogin.getContentPane().add(btnMasuk);
		
		JLabel lblJudul = new JLabel("ATM");
		lblJudul.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblJudul.setForeground(new Color(255, 255, 255));
		lblJudul.setHorizontalAlignment(SwingConstants.CENTER);
		lblJudul.setBounds(169, 48, 63, 37);
		frmLogin.getContentPane().add(lblJudul);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#01B4F6"));
		panel.setBorder(BorderFactory.createSoftBevelBorder(1, Color.DARK_GRAY, Color.DARK_GRAY));
		panel.setBounds(86, 111, 247, 188);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(375, 0, 48, 37);
		frmLogin.getContentPane().add(btnNewButton);
		
		
		
		JPanel bardrag = new JPanel();
		bardrag.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		bardrag.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				frmLogin.setLocation(x - xMouse, y - yMouse);
			}
		});
		bardrag.setBackground(Color.decode("#0C3B97"));
		bardrag.setBounds(0, 0, 377, 37);
		frmLogin.getContentPane().add(bardrag);
		
	}
}
