import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GUI_INTERFACE_INFO_REK {

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
					GUI_INTERFACE_INFO_REK window = new GUI_INTERFACE_INFO_REK();
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
	public GUI_INTERFACE_INFO_REK() {
		initialize();
	}
	
	public GUI_INTERFACE_INFO_REK(String id) {
		this.id = id;
		initialize();
	}
	
	/*
	 * Initial Get Data's
	 */
	public String getDataName() {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			if(list.get(id).getNoRek().equals(id)) {
				return list.get(id).getNama();
			}
		}catch(Exception e) {
			
		}
		return null;
	}
	
	public String getNoRekening() {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			if(list.get(id).getNoRek().equals(id)) {
				return list.get(id).getNoRek();
			}
		}catch(Exception e) {
			
		}
		return null;
	}
	
	
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
	
	public String getDataStatus() {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			if(list.get(id).getStatusBlokir() == false) {
				return "Tidak Terblokir";
			}
		}catch(Exception e) {
			
		}
		return null;
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
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setForeground(Color.WHITE);
		lblNama.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblNama.setBounds(32, 34, 119, 28);
		panel_1.add(lblNama);
		
		JLabel lblNoRek = new JLabel("No Rekening");
		lblNoRek.setForeground(Color.WHITE);
		lblNoRek.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblNoRek.setBounds(32, 96, 119, 28);
		panel_1.add(lblNoRek);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblSaldo.setBounds(32, 161, 119, 28);
		panel_1.add(lblSaldo);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblStatus.setBounds(32, 228, 119, 28);
		panel_1.add(lblStatus);
		
		JLabel textName = new JLabel("");
		textName.setForeground(Color.WHITE);
		textName.setText(getDataName());
		textName.setFont(new Font("Ink Free", Font.PLAIN, 21));
		textName.setBounds(92, 54, 483, 28);
		panel_1.add(textName);
		
		JLabel textNoRek = new JLabel("");
		textNoRek.setForeground(Color.WHITE);
		textNoRek.setText(getNoRekening());
		textNoRek.setFont(new Font("Ink Free", Font.PLAIN, 21));
		textNoRek.setBounds(92, 123, 483, 28);
		panel_1.add(textNoRek);
		
		JLabel textSaldo = new JLabel("");
		textSaldo.setForeground(Color.WHITE);
		textSaldo.setText(getDataMoney());
		textSaldo.setFont(new Font("Ink Free", Font.PLAIN, 21));
		textSaldo.setBounds(92, 180, 483, 28);
		panel_1.add(textSaldo);
		
		JLabel textStatus = new JLabel("");
		textStatus.setForeground(Color.WHITE);
		textStatus.setText(getDataStatus());
		textStatus.setFont(new Font("Ink Free", Font.PLAIN, 21));
		textStatus.setBounds(92, 251, 483, 28);
		panel_1.add(textStatus);
		
		JLabel lblJudul = new JLabel("Informasi Rekening");
		lblJudul.setHorizontalAlignment(SwingConstants.CENTER);
		lblJudul.setFont(new Font("Arial", Font.BOLD, 30));
		lblJudul.setForeground(Color.WHITE);
		lblJudul.setBounds(200, 61, 340, 46);
		frame.getContentPane().add(lblJudul);
		
		JButton btnKeluar = new JButton("Kembali");
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKeluar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_INTERFACE_LOGGED_IN log = new GUI_INTERFACE_LOGGED_IN(id);
				log.frame.setVisible(true);;
			}
		});
		btnKeluar.setFont(new Font("Arial",Font.PLAIN, 15));
		btnKeluar.setForeground(Color.white);
		btnKeluar.setBackground(new Color(153, 153, 204));
		btnKeluar.setBounds(61, 70, 97, 37);
		frame.getContentPane().add(btnKeluar);
		
	}

}
