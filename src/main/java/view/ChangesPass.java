package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import Connect.ConnectDatabase;
import dao.AccountDao;
import entities.Account;
import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class ChangesPass extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblPassword;
	private JLabel lblNewPassword;
	private JButton btnchangesPass;
	private JButton btnReset;
	private JLabel lblNewPassword_1;
	private String passw;
	private String user;
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;
	private JPasswordField passwordConfirm;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ChangesPass dialog = new ChangesPass();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public ChangesPass(String pass, String username, Frame owner, boolean modal) {
		super(owner, modal);
		passw = pass;
		user = username;
		Layout();
	}

	private void Layout() {
		setBounds(100, 100, 421, 244);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblPassword = new JLabel("Old PassWord :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblNewPassword = new JLabel("New PassWord:");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnchangesPass = new JButton("Change PassWord");
		btnchangesPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnchangesPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnchangesPassActionPerformed(e);
			}
		});

		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnResetActionPerformed(e);
			}
		});

		lblNewPassword_1 = new JLabel("Confirm password:");
		lblNewPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		passwordOld = new JPasswordField();
		passwordOld.setFont(new Font("Tahoma", Font.PLAIN, 14));

		passwordNew = new JPasswordField();
		passwordNew.setFont(new Font("Tahoma", Font.PLAIN, 14));

		passwordConfirm = new JPasswordField();
		passwordConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnchangesPass)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewPassword, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addComponent(lblNewPassword_1, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordConfirm, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordNew, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordOld, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
					.addGap(31))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordOld, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewPassword, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordNew, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewPassword_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(78)
							.addComponent(passwordConfirm, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnchangesPass, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReset))
					.addGap(23))
		);
		contentPanel.setLayout(gl_contentPanel);
		System.out.println(passw);
	}

	protected void btnResetActionPerformed(ActionEvent e) {
		passwordOld.setText("");
		passwordNew.setText("");
		passwordConfirm.setText("");
	}

	protected void btnchangesPassActionPerformed(ActionEvent e) {
		AccountDao dao = new AccountDao();
		String password = passwordNew.getText();
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
		boolean match = BCrypt.checkpw(passwordOld.getText(), passw);
		if (match && !passwordOld.getText().isEmpty() && !passwordNew.getText().isEmpty()
				&& !passwordConfirm.getText().isEmpty() && passwordNew.getText().equals(passwordConfirm.getText())) {
			dao.changePass(user, hash);
			
			setVisible(false);
		} else if (!passwordOld.getText().isEmpty() && !match
				&& passwordNew.getText().equals(passwordConfirm.getText())) {
			JOptionPane.showMessageDialog(null, "Current password is incorrect");
		} else if (match && passwordNew.getText().isEmpty() && passwordConfirm.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter New Password");
		} else if (match && !passwordNew.getText().equals(passwordConfirm.getText())) {
			JOptionPane.showMessageDialog(null, "The password does not match");
		} else {
			JOptionPane.showMessageDialog(null, "Please Enter");
		}
	}
}
