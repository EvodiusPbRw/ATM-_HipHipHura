import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI_INTERFACE_TARIK {

	public JFrame frame;
	int xMouse;
	int yMouse;
	String id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_INTERFACE_TARIK window = new GUI_INTERFACE_TARIK();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_INTERFACE_TARIK() {
		initialize();
	}
	
	public GUI_INTERFACE_TARIK(String id) {
		this.id = id;
		initialize();
	}
	/*
	 * Get Data of Saldo
	 */
	
	public String getDataMoney() {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			if(list.get(id).getNoRek().equals(id)) {
				return "Rp." + Float.toString(list.get(id).getSaldo());
			}
		}catch(Exception e) {
			
		}
		return null;
	}
	
	
	/*
	 * Set data of Saldo
	 */
	public String setTarikSaldo(String saldo) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			float temp = Float.parseFloat(saldo);
			if(temp>0 && (list.get(id).getSaldo()-temp) > 0.0 ) {
				list.get(id).tarik(temp);
				FileOutputStream fos = new FileOutputStream("database2");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(list);
				fos.close();oos.close();fis.close();ois.close();
				return "Saldo berhasil diambil";
			}
			
			
		}catch(Exception e) {
			
		}
		return "Saldo tidak dapat diambil, karena jumlah yang anda masukkan terlalu besar";
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.decode("#0C3B97"));
		frame.setSize(748,525);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(700, 0, 48, 37);
		frame.getContentPane().add(btnNewButton);
		
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				
				frame.setLocation(x  - xMouse,y - yMouse);
			}
		});
		panel.setBounds(0, 0, 709, 37);
		panel.setBackground(Color.decode("#0C3B97"));
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(60, 117, 611, 301);
		panel_1.setBackground(Color.decode("#01B4F6"));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblJudul = new JLabel("Tarik Duid");
		lblJudul.setHorizontalAlignment(SwingConstants.CENTER);
		lblJudul.setFont(new Font("Arial", Font.BOLD, 30));
		lblJudul.setForeground(Color.WHITE);
		lblJudul.setBounds(200, 61, 340, 46);
		frame.getContentPane().add(lblJudul);
		
		JButton btnKeluar = new JButton("Kembali");
		btnKeluar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_INTERFACE_LOGGED_IN log = new GUI_INTERFACE_LOGGED_IN(id);
				log.frame.setVisible(true);
			}
		});
		btnKeluar.setFont(new Font("Arial",Font.PLAIN, 15));
		btnKeluar.setForeground(Color.white);
		btnKeluar.setBackground(new Color(153, 153, 204));
		btnKeluar.setBounds(61, 70, 97, 37);
		frame.getContentPane().add(btnKeluar);
		
		JLabel lblSaldoNow = new JLabel("Saldo anda sekarang:");
		lblSaldoNow.setForeground(Color.WHITE);
		lblSaldoNow.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblSaldoNow.setBounds(32, 34, 233, 28);
		panel_1.add(lblSaldoNow);
		
		JLabel textSaldo = new JLabel("");
		textSaldo.setForeground(Color.WHITE);
		textSaldo.setText(getDataMoney());
		textSaldo.setFont(new Font("Ink Free", Font.PLAIN, 21));
		textSaldo.setBounds(251, 30, 326, 28);
		panel_1.add(textSaldo);
		
		JLabel lblTarikSaldo = new JLabel("Masukkan uang yang akan ditarik:");
		lblTarikSaldo.setForeground(Color.WHITE);
		lblTarikSaldo.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblTarikSaldo.setBounds(32, 116, 326, 28);
		panel_1.add(lblTarikSaldo);
		
		JTextField textTmbhSaldo = new JTextField();
		textTmbhSaldo.setFont(new Font("Calibri", Font.PLAIN, 21));
		textTmbhSaldo.setBounds(74, 154, 482, 34);
		panel_1.add(textTmbhSaldo);
		textTmbhSaldo.setColumns(10);
		
		JLabel lblAlertSaldo = new JLabel("");
		lblAlertSaldo.setFont(new Font("Candara Light", Font.BOLD, 17));
		lblAlertSaldo.setForeground(new Color(255, 0, 51));
		lblAlertSaldo.setBounds(368, 116, 194, 28);
		panel_1.add(lblAlertSaldo);
		
		JButton btnSubmit= new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Tsaldo = textTmbhSaldo.getText();
				if(Tsaldo.equals("") == true) {
					lblAlertSaldo.setText("Tidak boleh kosong");
				}
				else {
					if(Tsaldo.matches("[0-9]*$") == false) {
						lblAlertSaldo.setText("Invalid Input");
					}
					else {
						JOptionPane.showMessageDialog(frame,setTarikSaldo(Tsaldo));
						int result = JOptionPane.showConfirmDialog(frame, "Ingin melanjutkan transaksi?","Dialog Box", 
								JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(result == JOptionPane.YES_OPTION) {
							frame.dispose();
							GUI_INTERFACE_LOGGED_IN log = new GUI_INTERFACE_LOGGED_IN(id);
							log.frame.setVisible(true);
						}
						else {
							frame.dispose();
							GUI_INTERFACE_LOG_IN log = new GUI_INTERFACE_LOG_IN();
							log.frmLogin.setVisible(true);
						}
					}
				}
			}
		});
		btnSubmit.setFont(new Font("Candara Light", Font.PLAIN, 18));
		btnSubmit.setBackground(new Color(0, 204, 153));
		btnSubmit.setBounds(449, 228, 107, 47);
		panel_1.add(btnSubmit);
	}

}
