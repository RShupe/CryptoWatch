package cryptowatch;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;


public class MainGui {

	private JFrame frmCryptowatch;
	private JTable curTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frmCryptowatch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
	}
	
	private void initialize() {
		frmCryptowatch = new JFrame();
		frmCryptowatch.setResizable(false);
		frmCryptowatch.setTitle("CryptoWatch");
		frmCryptowatch.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGui.class.getResource("/cryptowatch/cw.png")));
		frmCryptowatch.setBounds(100, 100, 555, 350);
		frmCryptowatch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JLabel lblNewLabel = new JLabel("New label");
		
		lblNewLabel.setIcon(new ImageIcon(MainGui.class.getResource("/cryptowatch/logoresize.png")));
		frmCryptowatch.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		
	    String data[][]={ {"", "" ,"Bitcoin", "BTC"},    
	    				  {"boolean", "" ,"Dogecoin", "DOGE"},    
                };    
	    String column[]={"Track", "Icon","Currency","ABV"}; 
	    
		curTable = new JTable(data, column);
		
		curTable.setShowHorizontalLines(false);
		curTable.setShowVerticalLines(false);
		curTable.setShowGrid(false);
		curTable.setCellSelectionEnabled(true);
		curTable.setColumnSelectionAllowed(true);
		
		frmCryptowatch.getContentPane().add(curTable, BorderLayout.CENTER);
	}

}
