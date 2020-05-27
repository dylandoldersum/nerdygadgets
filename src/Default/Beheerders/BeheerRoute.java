package Default.Beheerders;

import Default.Planners.ZienRoute;
import SQL.DatabaseReader;
import SQL.SQLFuncties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BeheerRoute extends JFrame{

    private SQLFuncties sql = new SQLFuncties();

    public BeheerRoute() throws SQLException {
        setTitle("Beheren route");
        setSize(1200, 800);
        setResizable(false);
        setMinimumSize(new Dimension(1200, 800));

        JList<String> list = new JList<>(sql.inzienRoutes());

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(50);
        list.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane1 = new JScrollPane(list);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane1);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    int id = index+1;
                    try {
                        ZienRoute z = new ZienRoute(id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
