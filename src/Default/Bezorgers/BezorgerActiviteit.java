package Default.Bezorgers;

import SQL.SQLFuncties;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BezorgerActiviteit extends JFrame {
    private SQLFuncties sql = new SQLFuncties();

    public BezorgerActiviteit(int werknemer_ID) throws SQLException {

        setTitle("Bezorgeractiviteit");
        setSize(600, 500);
        setResizable(true);
        setMinimumSize(new Dimension(600, 500));

        // Creating table
        JTable table = new JTable(sql.bezorgerActiviteit(werknemer_ID));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
