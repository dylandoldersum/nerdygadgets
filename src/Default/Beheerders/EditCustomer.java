package Default.Beheerders;

import SQL.DatabaseReader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditCustomer{

    private DatabaseReader db = new DatabaseReader();
    private Connection dbc = db.getConnection();

    public EditCustomer() throws SQLException {


        Statement stmt;
        String query;
        ResultSet rs;


        // Temporary data
        Object[][] rowData = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6"}};
        // Array for columnNames
        Object[] columnNames = {"Stad", "Postcode", "Straatnaam", "Huisnummer", "Voornaam", "Achternaam"};

        // Creating table
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(mTableModel);

        // Query from customers and cities
        query = "SELECT City, Zip_Code, Street_Name, House_Number, First_Name, Last_Name FROM address join customer on Address_ID = Address_1";
        stmt = dbc.createStatement();
        // Execute query and return results
        rs = stmt.executeQuery(query);

        // Creating JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(600, 500);
        frame.setVisible(true);

        // remove the temp row previously created
        mTableModel.removeRow(0);

        Object[] rows;
        // For each row
        while (rs.next()) {
            // adding values to temporary rows
            rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)};
            mTableModel.addRow(rows);
        }

        table.addMouseListener(new MouseAdapter() {
            String query;
            Statement stmt;
            ResultSet rs;
            @Override
            public void mouseClicked(MouseEvent e) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    String valueStad = (String) target.getValueAt(row, 0);
                    System.out.print(valueStad);
                    if(valueStad == target.getValueAt(row, 0)){
                        String query = "update address set City = ? where Address_ID = ?";
                        PreparedStatement preparedStmt = null;
                        try {
                            preparedStmt = dbc.prepareStatement(query);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            preparedStmt.setInt   (1, 3);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            preparedStmt.setString(2, "Test");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        try {
                            preparedStmt.executeUpdate();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        System.out.print("Geen wijziging gevonden!");
                    }
            }
        });
}
}

