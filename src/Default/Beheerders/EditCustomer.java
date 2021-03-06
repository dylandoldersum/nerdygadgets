package Default.Beheerders;

import SQL.DatabaseConnector;
import SQL.SQLFuncties;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditCustomer{

    private DatabaseConnector db = new DatabaseConnector();
    private Connection dbc = db.getConnection();
    private SQLFuncties sql = new SQLFuncties();

    public EditCustomer() throws SQLException {


        Statement stmt;
        String query;
        ResultSet rs;


        // Temporary data
        Object[][] rowData = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6", "Row1-Column7"}};
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
            rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
            mTableModel.addRow(rows);
        }


        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    String resul = table.getValueAt(row, column).toString();
                    String id = table.getValueAt(row, 0).toString();
                }
            }
        });

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                Object data = model.getValueAt(row, column);
                int id = row+1;
                try {
                    sql.veranderenKlantgegevens(id, column, data);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}


