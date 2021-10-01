package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.Book_BookDetail_CallCardDao;
import dao.Books_DetailDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;

public class ShowBookMostBorrowed extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel lblNewLabel;

	public ShowBookMostBorrowed(Frame owner, boolean modal) {
		super(owner, modal);
		Layout();
	}
	private void Layout() {
		setTitle("Book most borrowed");
		setBounds(100, 100, 639, 441);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		
		lblNewLabel = new JLabel("List book most borrowed", SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(139, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(112, Short.MAX_VALUE))
		);
		{
			table = new JTable();
			scrollPane.setViewportView(table);
		}
		contentPanel.setLayout(gl_contentPanel);

		LoadData(new Book_BookDetail_CallCardDao());
	}
	public void LoadData(Book_BookDetail_CallCardDao book) {
		DefaultTableModel model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				default:
					return String.class;
				}
			}
		};

		model.addColumn("Id Book");
		model.addColumn("Number of borrowed times");
		model.addColumn("Title");
		model.addColumn("Author");
		

		book.selectBookHightLight().forEach(item -> model.addRow(new Object[] {item.getId_book(), item.getNum(),
				item.getTitle(), item.getAuthor() }));
 
		table.setModel(model); 
		table.getTableHeader().setReorderingAllowed(false);
	}
}
