import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.opencsv.CSVWriter;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Product {

	private JFrame frame;
	private JTextField txtplace;
	private JTextField txtroad;
	private JTextField txtrest;
	private JTextField txtamount;
	private JTextField txtnorm;
	private JComboBox txtnpumber;
	private JTextField txtnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product window = new Product();
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
	public Product() {
		initialize();
		Connect();
		LoadProductNo();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public void Connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Charging", "sa",
					"passw0rd");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void LoadProductNo() {

		try {
			pst = con.prepareStatement("select number from Demo1");
			rs = pst.executeQuery();
			txtnpumber.removeAllItems();
			while (rs.next()) {
				txtnpumber.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setTitle("國道充電樁查詢系統");
		frame.setBounds(100, 100, 924, 663);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
		          "幼圆", Font.BOLD, 18)));  

		JLabel lblNewLabel = new JLabel("國道充電站查詢系統");
		lblNewLabel.setFont(new Font("標楷體", Font.BOLD, 30));
		lblNewLabel.setBounds(32, 22, 287, 75);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(32, 90, 821, 367);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("國道");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 69, 76, 26);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("服務區");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(10, 119, 97, 26);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("數量");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		lblNewLabel_1_1_1.setBounds(10, 219, 76, 26);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("設置位置");
		lblNewLabel_1_1_1_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		lblNewLabel_1_1_1_1.setBounds(10, 169, 184, 26);
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("規格");
		lblNewLabel_1_1_1_2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		lblNewLabel_1_1_1_2.setBounds(10, 269, 76, 26);
		panel.add(lblNewLabel_1_1_1_2);

		txtplace = new JTextField();
		txtplace.setBackground(UIManager.getColor("Button.light"));
		txtplace.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txtplace.setBounds(142, 164, 260, 31);
		panel.add(txtplace);
		txtplace.setColumns(10);

		txtroad = new JTextField();
		txtroad.setForeground(SystemColor.desktop);
		txtroad.setBackground(UIManager.getColor("Button.light"));
		txtroad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txtroad.setColumns(10);
		txtroad.setBounds(142, 69, 260, 31);
		panel.add(txtroad);

		txtrest = new JTextField();
		txtrest.setBackground(UIManager.getColor("Button.light"));
		txtrest.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txtrest.setColumns(10);
		txtrest.setBounds(142, 114, 260, 31);
		panel.add(txtrest);

		txtamount = new JTextField();
		txtamount.setBackground(UIManager.getColor("Button.light"));
		txtamount.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txtamount.setColumns(10);
		txtamount.setBounds(142, 214, 260, 31);
		panel.add(txtamount);

		txtnorm = new JTextField();
		txtnorm.setBackground(UIManager.getColor("Button.light"));
		txtnorm.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txtnorm.setColumns(10);
		txtnorm.setBounds(142, 264, 500, 31);
		panel.add(txtnorm);

		JLabel lblNewLabel_1_2 = new JLabel("編號");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 35));
		lblNewLabel_1_2.setBounds(621, 39, 76, 37);
		panel.add(lblNewLabel_1_2);

		txtnpumber = new JComboBox();
		txtnpumber.setBackground(SystemColor.menu);
		txtnpumber.setFont(new Font("幼圆", Font.BOLD, 20));
		txtnpumber.setBounds(582, 94, 140, 42);
		panel.add(txtnpumber);

		JButton btnNewButton = new JButton("查詢");
		btnNewButton.setBackground(new Color(255, 250, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String number = txtnpumber.getSelectedItem().toString();

				try {
					pst = con.prepareStatement("select * from Demo1 where number =?");
					pst.setString(1, number);
					rs = pst.executeQuery();

					if (rs.next() == true) {
						txtnumber.setText(rs.getString(1));
						txtroad.setText(rs.getString(2));
						txtrest.setText(rs.getString(3));
						txtplace.setText(rs.getString(4));
						txtamount.setText(rs.getString(5));
						txtnorm.setText(rs.getString(6));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		btnNewButton.setBounds(584, 162, 138, 57);
		panel.add(btnNewButton);

		JLabel lblNewLabel_1_3 = new JLabel("編號");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		lblNewLabel_1_3.setBounds(10, 20, 76, 26);
		panel.add(lblNewLabel_1_3);

		txtnumber = new JTextField();
		txtnumber.setForeground(SystemColor.desktop);
		txtnumber.setBackground(UIManager.getColor("Button.light"));
		txtnumber.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txtnumber.setColumns(10);
		txtnumber.setBounds(142, 20, 260, 31);
		panel.add(txtnumber);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(UIManager.getBorder("Button.border"));
		panel_1.setBounds(27, 467, 826, 138);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton_1 = new JButton("新增");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(new Color(255, 250, 240));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String number = txtnumber.getText();
					String road = txtroad.getText();
					String rest = txtrest.getText();
					String place = txtplace.getText();
					String amount = txtamount.getText();
					String norm = txtnorm.getText();

					pst = con.prepareStatement(
							"insert into Demo1(number,road,rest,place,amount,norm)values(?,?,?,?,?,?)");
					pst.setString(1, number);
					pst.setString(2, road);
					pst.setString(3, rest);
					pst.setString(4, place);
					pst.setString(5, amount);
					pst.setString(6, norm);

					int k = pst.executeUpdate();

					if (k > 0) {
						JOptionPane.showMessageDialog(null, "新增成功!!!");
						txtnumber.setText("");
						txtroad.setText("");
						txtrest.setText("");
						txtplace.setText("");
						txtamount.setText("");
						txtnorm.setText("");
						txtnorm.requestFocus();
						LoadProductNo();
					} else {
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "新增失敗!!!，請檢查資料否重複",null, JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		btnNewButton_1.setBounds(0, 10, 138, 57);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.setBackground(new Color(255, 250, 240));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String number = txtnumber.getText();
					String road = txtroad.getText();
					String rest = txtrest.getText();
					String place = txtplace.getText();
					String amount = txtamount.getText();
					String norm = txtnorm.getText();
					String pnumber = txtnpumber.getSelectedItem().toString();

					pst = con.prepareStatement(
							"update Demo1 set number=?, road=?, rest=?, place=?, amount=?, norm=? where number=?");
					pst.setString(1, number);
					pst.setString(2, road);
					pst.setString(3, rest);
					pst.setString(4, place);
					pst.setString(5, amount);
					pst.setString(6, norm);
					pst.setString(7, pnumber);

					int k = pst.executeUpdate();

					if (k > 0) {
						JOptionPane.showMessageDialog(null, "修改成功!!!");
						txtnumber.setText("");
						txtroad.setText("");
						txtrest.setText("");
						txtplace.setText("");
						txtamount.setText("");
						txtnorm.setText("");
						txtnorm.requestFocus();
						LoadProductNo();
					} else {
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "修改失敗!!!");
				}

			}
		});
		btnNewButton_2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		btnNewButton_2.setBounds(146, 10, 138, 57);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("刪除");
		btnNewButton_3.setBackground(new Color(255, 250, 240));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String pnumber = txtnpumber.getSelectedItem().toString();

					pst = con.prepareStatement("delete from Demo1 where number=?");
					pst.setString(1, pnumber);
					int k = pst.executeUpdate();

					if (k > 0) {
						JOptionPane.showMessageDialog(null, "刪除成功!!!");
						txtnumber.setText("");
						txtroad.setText("");
						txtrest.setText("");
						txtplace.setText("");
						txtamount.setText("");
						txtnorm.setText("");
						txtnorm.requestFocus();
						LoadProductNo();
					} else {
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "刪除失敗!!!",null,JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_3.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		btnNewButton_3.setBounds(293, 10, 138, 57);
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("輸出");
		btnNewButton_4.setBackground(new Color(255, 250, 240));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pst = con.prepareStatement("select * from Demo1");
					rs = pst.executeQuery();
					while (rs.next()) {
						CSVWriter writer = new CSVWriter(new FileWriter("Chargin.csv"));
						Boolean includeHeaders = true;

						writer.writeAll(rs, includeHeaders);
						writer.close();
						JOptionPane.showMessageDialog(null, "輸出成功!!!,格式為CSV");
					}
					rs.close();
					pst.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "輸出失敗!!!",null,JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_4.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		btnNewButton_4.setBounds(441, 10, 138, 57);
		panel_1.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("New One");
		btnNewButton_5.setBackground(new Color(255, 250, 240));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtnumber.setText("");
				txtroad.setText("");
				txtrest.setText("");
				txtplace.setText("");
				txtamount.setText("");
				txtnorm.setText("");
				txtnorm.requestFocus();

			}
		});
		btnNewButton_5.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		btnNewButton_5.setBounds(589, 10, 193, 57);
		panel_1.add(btnNewButton_5);

		JButton btnNewButton_1_1 = new JButton("政府資料來源");
		btnNewButton_1_1.setBackground(new Color(255, 250, 240));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String virus_data_url = "https://quality.data.gov.tw/dq_download_csv.php?nid=151729&md5_url=843449967996fe514109f17d07b3faa0";
					URL url = new URL(virus_data_url);
					HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));


					Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
					int sum = 0;
					for (CSVRecord csvRecord : records) {

						GetSet gRC = new GetSet();
						gRC.setRoad(csvRecord.get("國道別"));
						gRC.setRest(csvRecord.get("服務區"));
						gRC.setPlace(csvRecord.get("服務區"));
						gRC.setAmount(csvRecord.get("車位數量"));
						gRC.setNorm(csvRecord.get("充電設備規格"));
						sum++;
						txtnumber.setText("");
						txtroad.setText(gRC.road);
						txtrest.setText(gRC.rest);
						txtplace.setText(gRC.place);
						txtamount.setText(gRC.amount);
						txtnorm.setText(gRC.norm);
						txtnorm.requestFocus();

						JOptionPane.showMessageDialog(null, "第" + sum + "筆");
					}
					JOptionPane.showMessageDialog(null, "共抓取" + sum + "筆資料");

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		btnNewButton_1_1.setBounds(0, 72, 251, 57);
		panel_1.add(btnNewButton_1_1);
	}
}
