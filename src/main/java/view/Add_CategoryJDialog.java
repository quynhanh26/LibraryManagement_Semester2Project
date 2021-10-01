package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CategoryDao;
import entities.Category;
import helper.Regex;
import helper.Validate;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;

public class Add_CategoryJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCategoryName;
	private JTextField txtCategoryName;
	private JLabel lbl_Error_Id;
	private JLabel lbl_Error_Name;
	private JLabel lblTitleCreateCategory;
	private JTextField txtCategoryId;
	private JLabel lblCategoryId;
	private JButton btnAdditional;

	
	public Add_CategoryJDialog(Frame owner, boolean modal) {
		super(owner, modal);
		Layout();
	}


	private void Layout() {
		setBounds(100, 100, 592, 272);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblCategoryName = new JLabel("Category Name:");
		lblCategoryName.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		txtCategoryName = new JTextField();
		txtCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCategoryName.setColumns(10);
		
		lbl_Error_Id = new JLabel(" ");
		lbl_Error_Id.setForeground(Color.RED);
		
		lbl_Error_Name = new JLabel(" ");
		lbl_Error_Name.setForeground(Color.RED);
		
		lblTitleCreateCategory = new JLabel("Create Category");
		lblTitleCreateCategory.setForeground(new Color(0, 0, 255));
		lblTitleCreateCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleCreateCategory.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		
		txtCategoryId = new JTextField();
		txtCategoryId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCategoryId.setColumns(10);
		
		lblCategoryId = new JLabel("Category ID:");
		lblCategoryId.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		btnAdditional = new JButton("Additional");
		btnAdditional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdditionalActionPerformed(e);
			}
		});
		btnAdditional.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCategoryName)
								.addComponent(lblCategoryId, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(20)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtCategoryName, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_Error_Id, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
										.addComponent(lbl_Error_Name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAdditional, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(18)
									.addComponent(txtCategoryId, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(83)
							.addComponent(lblTitleCreateCategory, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblTitleCreateCategory, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCategoryId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoryId, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addComponent(lbl_Error_Id, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCategoryName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoryName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_Error_Name, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAdditional, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	protected void btnAdditionalActionPerformed(ActionEvent e) {
		CategoryDao catedao = new CategoryDao();
		Category cate = new Category();

		if (Validate.checkRegex(Regex.ID, txtCategoryId.getText().trim()) && !txtCategoryId.getText().isEmpty()) {
			if (catedao.check_IDCate(txtCategoryId.getText().trim()) == false) {
				cate.setId(txtCategoryId.getText().trim());
				lbl_Error_Id.setText(" ");
			} else {
				lbl_Error_Id.setText("ID already exists");
			}
		} else {
			lbl_Error_Id.setText("ID is invalid! ID length must be greater than 3 and less than 20");
		}

		if (Validate.checkRegex(Regex.TITLE, txtCategoryName.getText().trim())
				&& !txtCategoryName.getText().isEmpty()) {
			if (catedao.check_CateName(txtCategoryName.getText().trim()) == false) {
				cate.setName(txtCategoryName.getText().trim());
				lbl_Error_Name.setText(" ");
			} else {
				lbl_Error_Name.setText("Name already exists");
			}
		} else {
			lbl_Error_Name.setText("Name is invalid! Name length must be greater than 5 and less than 250");
		}
		catedao.addCategory(cate);
	}
}
