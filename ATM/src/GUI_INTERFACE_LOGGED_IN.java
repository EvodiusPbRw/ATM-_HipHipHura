import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextPane;

public class GUI_INTERFACE_LOGGED_IN {

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
					GUI_INTERFACE_LOGGED_IN window = new GUI_INTERFACE_LOGGED_IN();
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
	public GUI_INTERFACE_LOGGED_IN() {
		initialize();
	}
	
	public GUI_INTERFACE_LOGGED_IN(String id) {
		this.id = id;
		initialize();
	}
	
	/*
	 * Initial HashMap For getting the Name and Savings
	 */
	public String getDataName() {
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
					return list.get(j).getNama();
				}
				i++;
			}
		}catch(Exception e) {
			//System.out.println("Gagal");
		}
		return null;
	}
	
	public String getDataMoney() {
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
					return "Rp." + Float.toString(list.get(j).getSaldo());
				}
				i++;
			}
		}catch(Exception e) {
			//System.out.println("Gagal");
		}
		return null;
	}
	
	/*
	 * Cek class data
	 */
	
	public String cekClass(String id) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			String temp = list.get(id).getClass().getSimpleName().substring(0, 8) 
					+ ' ' + list.get(id).getClass().getSimpleName().substring(8, list.get(id).getClass().getSimpleName().length());
			return temp;
		}catch(Exception e) {
			
		}
		return null;
	}
	
	/*
	 * Check if value didnt exist
	 */
	public void checkValue() {
		
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		Color setBackground = Color.decode("#0C3B97");
		frame.getContentPane().setBackground(setBackground);
		frame.setBounds(100, 100, 748, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel namaATM = new JLabel("");
		namaATM.setText(cekClass(id));
		namaATM.setForeground(new Color(255, 255, 255));
		namaATM.setBounds(36, 45, 337, 40);
		namaATM.setFont(new Font("Calibri", Font.BOLD, 38));
		frame.getContentPane().add(namaATM);
		
		JLabel lblwelcome = new JLabel("Selamat Datang,");
		Color setwelcome = Color.decode("#1FA5E9");
		lblwelcome.setForeground(setwelcome);
		lblwelcome.setFont(new Font("Calibri", Font.ITALIC, 20));
		lblwelcome.setBounds(36, 141, 151, 30);
		frame.getContentPane().add(lblwelcome);
		Color setAccount = Color.decode("#1FA5E9");
		
		JLabel lblSaving = new JLabel("Simpanan,");
		Color setSaving = Color.decode("#1FA5E9");
		lblSaving.setForeground(setSaving);
		lblSaving.setFont(new Font("Calibri", Font.ITALIC, 20));
		lblSaving.setBounds(36, 220, 92, 30);
		frame.getContentPane().add(lblSaving);
		
		JButton btnInfoRek = new JButton("Info Rekening");
		btnInfoRek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUI_INTERFACE_INFO_REK log = new GUI_INTERFACE_INFO_REK(id);
				log.frame.setVisible(true);
			}
		});
		btnInfoRek.setFont(new Font("Arial", Font.PLAIN, 20));
		btnInfoRek.setForeground(Color.WHITE);
		Border setBorderInfo = BorderFactory.createLineBorder(Color.decode("#01B4F6"));
		btnInfoRek.setBorder(setBorderInfo);
		btnInfoRek.setBackground(Color.decode("#01B4F6"));
		btnInfoRek.setOpaque(true);
		btnInfoRek.setBounds(291, 141, 204, 66);
		frame.getContentPane().add(btnInfoRek);
		
		JButton btnTransfer = new JButton("Transfer Tunai");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUI_INTERFACE_TRANSFER log = new GUI_INTERFACE_TRANSFER(id);
				log.frame.setVisible(true);
			}
		});
		btnTransfer.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTransfer.setBounds(505, 141, 204, 67);
		btnTransfer.setForeground(Color.WHITE);
		btnTransfer.setBorder(setBorderInfo);
		btnTransfer.setBackground(Color.decode("#01B4F6"));
		btnTransfer.setOpaque(true);
		frame.getContentPane().add(btnTransfer);
		
		JButton btnTariki = new JButton("Tarik Tunai");
		btnTariki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUI_INTERFACE_TARIK log = new GUI_INTERFACE_TARIK(id);
				log.frame.setVisible(true);
			}
		});
		btnTariki.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTariki.setForeground(Color.WHITE);
		btnTariki.setBorder(setBorderInfo);
		btnTariki.setBackground(Color.decode("#01B4F6"));
		btnTariki.setOpaque(true);
		btnTariki.setBounds(291, 220, 204, 66);
		frame.getContentPane().add(btnTariki);
		
		JButton btnSetor = new JButton("Setor Tunai");
		btnSetor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUI_INTERFACE_SETOR log = new GUI_INTERFACE_SETOR(id);
				log.frame.setVisible(true);
			}
		});
		btnSetor.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSetor.setForeground(Color.WHITE);
		btnSetor.setBorder(setBorderInfo);
		btnSetor.setBackground(Color.decode("#01B4F6"));
		btnSetor.setOpaque(true);
		btnSetor.setBounds(505, 220, 204, 66);
		frame.getContentPane().add(btnSetor);
		
		JButton btnGantiPw = new JButton("Ganti Password");
		btnGantiPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUI_INTERFACE_GANTIPW log =new GUI_INTERFACE_GANTIPW(id);
				log.frame.setVisible(true);
			}
		});
		btnGantiPw.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGantiPw.setForeground(Color.WHITE);
		btnGantiPw.setBorder(setBorderInfo);
		btnGantiPw.setBackground(Color.decode("#01B4F6"));
		btnGantiPw.setOpaque(true);
		btnGantiPw.setBounds(291, 296, 204, 66);
		frame.getContentPane().add(btnGantiPw);
		
		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_INTERFACE_LOG_IN log = new GUI_INTERFACE_LOG_IN();
				log.frmLogin.setVisible(true);;
			}
		});
		btnKeluar.setFont(new Font("Arial",Font.PLAIN, 15));
		btnKeluar.setForeground(Color.white);
		Border borderKeluar = BorderFactory.createLineBorder(Color.red);
		btnKeluar.setBorder(borderKeluar);
		btnKeluar.setBackground(Color.red);
		btnKeluar.setBounds(607, 384, 102, 40);
		frame.getContentPane().add(btnKeluar);
		
		JButton btnLain = new JButton("Pulsa");
		btnLain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUI_INTERFACE_OTHER log = new GUI_INTERFACE_OTHER(id);
				log.frame.setVisible(true);
			}
		});
		btnLain.setOpaque(true);
		btnLain.setForeground(Color.WHITE);
		btnLain.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLain.setBackground(Color.decode("#2257BF"));
		btnLain.setBounds(505, 296, 204, 66);
		frame.getContentPane().add(btnLain);
		
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
		panel.setBounds(0, 0, 709, 35);
		panel.setBackground(Color.decode("#0C3B97"));
		frame.getContentPane().add(panel);
		
		JLabel usernameRek = new JLabel("");
		usernameRek.setForeground(Color.WHITE);
		usernameRek.setText(getDataName());
		usernameRek.setFont(new Font("Ink Free", Font.BOLD, 20));
		usernameRek.setBounds(58, 179, 175, 28);
		frame.getContentPane().add(usernameRek);
		
		JLabel simpananRek_1 = new JLabel("");
		simpananRek_1.setForeground(Color.WHITE);
		simpananRek_1.setText(getDataMoney());
		simpananRek_1.setFont(new Font("Ink Free", Font.BOLD, 20));
		simpananRek_1.setBounds(36, 260, 197, 28);
		frame.getContentPane().add(simpananRek_1);
	}
}
