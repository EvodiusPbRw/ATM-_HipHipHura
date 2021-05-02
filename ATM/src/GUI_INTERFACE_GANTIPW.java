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

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class GUI_INTERFACE_GANTIPW {

	public JFrame frame;
	int xMouse;
	int yMouse;
	String id;
	private JPasswordField passwordFieldPast;
	private JPasswordField passwordFieldNew;
	private JPasswordField passwordFieldCek;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_INTERFACE_GANTIPW window = new GUI_INTERFACE_GANTIPW();
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
	public GUI_INTERFACE_GANTIPW() {
		initialize();
	}
	
	public GUI_INTERFACE_GANTIPW(String id) {
		this.id = id;
		initialize();
	}
	/*
	 * Get data password same or not
	 */
	public boolean dataReturn2(String password) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			if(list.get(id).getPassword().equals(password)) {
				return true;
			}
		}catch(Exception e) {
			//System.out.println("Gagal");
		}
		return false;
	}
	
	/*
	 * Set value password baru
	 */
	public String setNewPassword(String password) {
		Map<String,Rekening> list = new HashMap<String,Rekening>();
		try {
			FileInputStream fis = new FileInputStream("database2");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (HashMap) ois.readObject();
			list.get(id).setPassword(password);
			FileOutputStream fos = new FileOutputStream("database2");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			fos.close();oos.close();fis.close();ois.close();
			return "Berhasil mengubah Password";
		}catch(Exception e) {
			
		}
		return "Gagal mengubah password";
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
		
		JLabel lblJudul = new JLabel("Ganti Password");
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
		
		JLabel lblCurrPw = new JLabel("Password sebelum-nya: ");
		lblCurrPw.setForeground(Color.WHITE);
		lblCurrPw.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblCurrPw.setBounds(32, 34, 233, 28);
		panel_1.add(lblCurrPw);
		
		JLabel lblNewPw = new JLabel("Password Baru: ");
		lblNewPw.setForeground(Color.WHITE);
		lblNewPw.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblNewPw.setBounds(32, 98, 233, 28);
		panel_1.add(lblNewPw);
		
		JLabel lblNewCheckPw = new JLabel("Validasi Password Baru: ");
		lblNewCheckPw.setForeground(Color.WHITE);
		lblNewCheckPw.setFont(new Font("Candara Light", Font.PLAIN, 22));
		lblNewCheckPw.setBounds(32, 158, 233, 28);
		panel_1.add(lblNewCheckPw);
		
		passwordFieldPast = new JPasswordField(6);
		PlainDocument document = (PlainDocument) passwordFieldPast.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 6) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
		passwordFieldPast.setFont(new Font("Calibri", Font.PLAIN, 19));
		passwordFieldPast.setBounds(275, 30, 283, 36);
		panel_1.add(passwordFieldPast);
		
		passwordFieldNew = new JPasswordField(6);
		PlainDocument document1 = (PlainDocument) passwordFieldNew.getDocument();
        document1.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 6) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
		passwordFieldNew.setFont(new Font("Calibri", Font.PLAIN, 19));
		passwordFieldNew.setBounds(275, 94, 283, 36);
		panel_1.add(passwordFieldNew);
		
		passwordFieldCek = new JPasswordField(6);
		PlainDocument document2 = (PlainDocument) passwordFieldCek.getDocument();
        document2.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 6) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
		passwordFieldCek.setFont(new Font("Calibri", Font.PLAIN, 19));
		passwordFieldCek.setBounds(275, 154, 283, 36);
		panel_1.add(passwordFieldCek);
		
		JLabel lblAlertPast = new JLabel("");
		lblAlertPast.setFont(new Font("Candara Light", Font.BOLD, 17));
		lblAlertPast.setForeground(new Color(255, 0, 51));
		lblAlertPast.setBounds(32, 64, 217, 22);
		panel_1.add(lblAlertPast);
		
		JLabel lblAlertNew = new JLabel("");
		lblAlertNew.setForeground(new Color(255, 0, 51));
		lblAlertNew.setFont(new Font("Candara Light", Font.BOLD, 17));
		lblAlertNew.setBounds(32, 126, 217, 22);
		panel_1.add(lblAlertNew);
		
		JLabel lblAlertCek = new JLabel("");
		lblAlertCek.setForeground(new Color(255, 0, 51));
		lblAlertCek.setFont(new Font("Candara Light", Font.BOLD, 17));
		lblAlertCek.setBounds(32, 185, 217, 22);
		panel_1.add(lblAlertCek);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PastPw = passwordFieldPast.getText();
				String NewPw = passwordFieldNew.getText();
				String CekPw = passwordFieldCek.getText();
				boolean flagPast = false, flagNew=false, flagCek=false;
				if(PastPw.equals("") || NewPw.equals("") || CekPw.contentEquals("")) {
					JOptionPane.showMessageDialog(frame, "Jika ingin mengubah, Semua password tidak boleh kosong");
				}
				else{
					if(dataReturn2(PastPw) == false) {
						lblAlertPast.setText("Password salah");
						flagPast=false;
					}
					else {
						lblAlertPast.setText("");
						flagPast = true;
					}
					if(!NewPw.matches("[0-9]*$")) {
						lblAlertNew.setText("Password harus angka");
						flagNew = false;
					}
					else {
						lblAlertNew.setText("");
						flagNew = true;
					}
					if(!CekPw.equals(NewPw)) {
						lblAlertCek.setText("Password tidak sama");
						flagCek = false;
					}
					else {
						lblAlertCek.setText("");
						flagCek = true;
					}
					
					if(flagPast == true && flagNew == true && flagCek == true) {
						setNewPassword(NewPw);
						JOptionPane.showMessageDialog(frame,setNewPassword(NewPw));
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
		btnSubmit.setBounds(451, 244, 107, 47);
		panel_1.add(btnSubmit);
		
		JCheckBox toggleVisible = new JCheckBox("    Toggle Visible");
		toggleVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(toggleVisible.isSelected()) {
					passwordFieldPast.setEchoChar((char) 0);
					passwordFieldNew.setEchoChar((char) 0);
					passwordFieldCek.setEchoChar((char) 0);
				}
				else {
					passwordFieldPast.setEchoChar('*');
					passwordFieldNew.setEchoChar('*');
					passwordFieldCek.setEchoChar('*');
				}
			}
		});
		toggleVisible.setBackground(new Color(51, 204, 204));
		toggleVisible.setHorizontalAlignment(SwingConstants.CENTER);
		toggleVisible.setFont(new Font("Candara Light", Font.PLAIN, 15));
		toggleVisible.setBounds(275, 196, 136, 36);
		panel_1.add(toggleVisible);
		
		
	}
}
