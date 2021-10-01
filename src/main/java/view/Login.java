package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import dao.AccountDao;
import entities.Account;
import entities.CallCard;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private JButton btnOk;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 206);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(43, 26, 116, 30);
		lblUsername.setFont(new Font("MingLiU_HKSCS-ExtB", Font.BOLD | Font.ITALIC, 22));

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(43, 78, 116, 30);
		lblPassword.setFont(new Font("MingLiU_HKSCS-ExtB", Font.BOLD | Font.ITALIC, 22));

		txtPassword = new JPasswordField();
		txtPassword.setBounds(169, 77, 309, 30);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setBounds(169, 25, 309, 30);
		txtUsername.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		txtUsername.setColumns(10);

		btnOk = new JButton("OK");
		btnOk.setBounds(169, 117, 129, 34);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOkActionPerformed(e);
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelActionPerformed(e);
			}
		});
		btnCancel.setBounds(349, 117, 129, 34);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.setLayout(null);
		contentPane.add(lblUsername);
		contentPane.add(txtUsername);
		contentPane.add(lblPassword);
		contentPane.add(txtPassword);
		contentPane.add(btnOk);
		contentPane.add(btnCancel);

		this.setResizable(false);
		new CallCard().setCallCardId(8);
	}

	protected void btnOkActionPerformed(ActionEvent e) {
		AccountDao accdao = new AccountDao();
		Account acc = accdao.selectAdmin(txtUsername.getText());
		String pas = txtPassword.getText().trim();
		if (txtUsername.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "username not empty");
		} else if (txtPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "password not empty");
		} else if (!txtUsername.getText().isEmpty() && !pas.isEmpty()) {
			boolean match = BCrypt.checkpw(pas, acc.getPass());
			if (match) {
				Library_Management library_Management = new Library_Management(acc.getPass(), acc.getUsername());
				library_Management.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "password or username don't exists");
			}
		} else {
			JOptionPane.showMessageDialog(null, "password or username don't exists");

		}
	}

	protected void btnCancelActionPerformed(ActionEvent e) {
		JFrame frame = new JFrame("Exit");
		if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Exit Form Login",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			System.exit(0);
		}
	}
}

