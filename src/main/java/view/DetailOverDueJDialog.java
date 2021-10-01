package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import dao.Book_BookDetail_CallCardDao;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class DetailOverDueJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable table;
	private int idCallCard;
	private JLabel lblTotal;
	private JLabel lbl_txtTotal;

	public DetailOverDueJDialog(int id) {
		idCallCard = id;
		Layout();
	}

	private void Layout() {
		setBounds(100, 100, 904, 343);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		
		lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lbl_txtTotal = new JLabel(" ");
		lbl_txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(14)
							.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbl_txtTotal, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(24)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_txtTotal, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);

		table = new JTable();
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = table.getColumnModel();


		detailOverDue(new Book_BookDetail_CallCardDao());
		
		

	}
	

	public void detailOverDue(Book_BookDetail_CallCardDao dao) {
		DefaultTableModel model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return Integer.class;
				case 6:
					return Integer.class;
				case 7:
					return LocalDate.class;
				case 8:
					return LocalDate.class;
				case 9:
					return Integer.class;
				case 10:
					return ImageIcon.class;
				default:
					return String.class;
				}
			}
		};

		model.addColumn("NO.");
		model.addColumn("Id Book");
		model.addColumn("Title");
		model.addColumn("Author");
		model.addColumn("Id Category");
		model.addColumn("Quantity");
		model.addColumn("Quantity Due");
		model.addColumn("Date Borrowed");
		model.addColumn("Date Due");
		model.addColumn("Money");
		model.addColumn("images");

		dao.getDetailOverDue(idCallCard)
				.forEach(item -> model.addRow(new Object[] { item.getNo(), item.getBookId(), item.getTitle(),
						item.getAuthor(), item.getCategoryId(), item.getQuantity(), item.getQuantityDue(),
						item.getDateBorrowed(), item.getDateDue(), item.getTotal(),
						new ImageIcon(new ImageIcon("images/" + item.getImg()).getImage().getScaledInstance(100, 100,
								Image.SCALE_SMOOTH)) }));
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(120);
		
		lbl_txtTotal.setText(dao.SumTotal_DetailOverDue(idCallCard) + "");
	}
}
