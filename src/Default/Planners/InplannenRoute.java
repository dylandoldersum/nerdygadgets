package Default.Planners;

import Default.Entiteit.AdressenLijst;
import Default.Entiteit.BezorgerLijst;
import SQL.DatabaseReader;
import SQL.SQLFuncties;
import TSP.AlgoritmeNB;
import TSP.BezorgerSteden;
import TSP.Stad;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InplannenRoute extends JFrame implements ActionListener {
    private static String apiKey = "MLr6vmcH7IgZsaAqaSebZ42kxfRuY1SJyGdJL2GVhVk";
    private InplannenRoute inplannenRoute;
    public static int bezorgerID;
    private JLabel gekozenBezorger;
    private JList<String> bezorglijst;
    private JList<String> adresLijst;
    private DefaultListModel model;
    private DefaultListModel lijstItem;
    private BezorgerLijst bezorgerLijst = new BezorgerLijst();
    private AdressenLijst adressenLijst = new AdressenLijst();
    private JTextField straatnaamJtf, huisnummerJtf, stadJtf;
    private String straatnaamStr;
    private int huisnummerint;
    private String stadStr;
    private JButton toevoegenAdres;
    private JButton startenRoute;
    private BezorgerSteden bezorgerSteden = new BezorgerSteden();
    private String url;
    private double longitude;
    private double latitude;
    private String provincie;
    private static SQLFuncties f = new SQLFuncties();
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> Route = new ArrayList<>();
    private String selectedProvincie;


    public InplannenRoute() throws SQLException {
        super("Inplannen route");
        list.add("Noord-holland");
        list.add("Zuid-holland");
        list.add("Utrecht");
        list.add("Drenthe");
        list.add("Overijssel");
        list.add("Gelderland");
        list.add("Groningen");
        list.add("Noord-Brabant");
        list.add("Limburg");
        list.add("Friesland");
        list.add("Flevoland");
        list.add("Zeeland");
        JLabel keuze1 = new JLabel("Kies een adres om toe te voegen aan uw route:");
        JLabel keuze2 = new JLabel("Kies uw bezorger:");
        JLabel gemaakteRoute = new JLabel();
        setLayout(new FlowLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        setSize(1200, 800);

        /** Ophalen openstaande bezorg adressen **/
        adresLijst = new JList<>(new DefaultListModel<>());
        lijstItem = (DefaultListModel) adresLijst.getModel();

        /** Ophalen provincies beschikbaar **/
        JComboBox provincies = new JComboBox();
        provincies.setModel(new DefaultComboBoxModel<String>(list.toArray(new String[0])));
        selectedProvincie = String.valueOf(provincies.getSelectedItem());
        f.getAdressenBijProvincie("Noord-Holland");

        provincies.addActionListener(e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            Object selected = comboBox.getSelectedItem();

            if (selected.toString().equalsIgnoreCase("Noord-holland")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Noord-holland");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Zuid-Holland")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Zuid-holland");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Gelderland")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Gelderland");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Utrecht")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Utrecht");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Noord-brabant")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Noord-brabant");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Drenthe")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Drenthe");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Overijssel")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Overijssel");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Limburg")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Limburg");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Friesland")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Friesland");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Groningen")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Groningen");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Zeeland")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Zeeland");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (selected.toString().equalsIgnoreCase("Flevoland")) {
                try {
                    lijstItem.clear();
                    f.getAdressenBijProvincie("Flevoland");
                    for (int i = 0; i < adressenLijst.getAdressen().size(); i++) {
                        lijstItem.addElement(adressenLijst.getAdressen().get(i).toString());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JScrollPane scrollijst = new JScrollPane(adresLijst);
        scrollijst.setPreferredSize(new Dimension(250, 125));

        adresLijst.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !adresLijst.isSelectionEmpty()) {
                    String selectedItem = adresLijst.getSelectedValue();
                    if (!Route.contains(selectedItem)) {
                        Route.add(selectedItem);

                        String[] temp;
                        String delimiter = " ";

                        temp = selectedItem.split(delimiter);
                        straatnaamStr = temp[0];
                        huisnummerint = Integer.parseInt(temp[1]);


                        DatabaseReader acc = new DatabaseReader();
                        Connection dbc = acc.getConnection();
                        String query = "SELECT * FROM `address` WHERE `Street_Name` = '" + straatnaamStr + "' AND `House_Number` = " + huisnummerint + "";
                        Statement st = null;
                        try {
                            st = dbc.createStatement();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        ResultSet r = null;
                        try {
                            r = st.executeQuery(query);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            r.first();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        try {
                            straatnaamStr = r.getString("Street_Name");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            huisnummerint = r.getInt("House_Number");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            stadStr = r.getString("City");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println(straatnaamStr + " " + huisnummerint + " " + stadStr);

                        try {
                            getLongitudeLangitude(straatnaamStr, huisnummerint, stadStr);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("Het adres is al toegevoegd aan de lijst!");
                    }
                    gemaakteRoute.setText("\n Opgestelde route: " + Route.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel3.add(provincies);


        /** Ophalen bezorgers en in een Scrollable list zetten **/
        f.getBezorgers();
        bezorglijst = new JList<>(new DefaultListModel<>());
        model = (DefaultListModel) bezorglijst.getModel();

        for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
            model.addElement(bezorgerLijst.getBezorgers().get(i).toString());
        }
        JScrollPane scrollableList = new JScrollPane(bezorglijst);
        scrollableList.setPreferredSize(new Dimension(250, 125));

        keuze1.setLabelFor(scrollijst);
        panel.add(keuze1, BorderLayout.NORTH);
        panel.add(gemaakteRoute, BorderLayout.SOUTH);
        panel.add(scrollijst);

        keuze2.setLabelFor(scrollableList);
        panel2.add(keuze2, BorderLayout.NORTH);
        panel2.add(scrollableList);

        startenRoute = new JButton("Start");
        straatnaamJtf = new JTextField(30);
        huisnummerJtf = new JTextField(8);
        stadJtf = new JTextField(10);

        gekozenBezorger = new JLabel("Let op, je hebt geen bezorger gekozen!");

        startenRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Stad> steden = new ArrayList<Stad>();
                steden.addAll(bezorgerSteden.initialSteden);
                if (bezorgerID == 0) {
                    JOptionPane.showMessageDialog(inplannenRoute, "U moet een bezorger kiezen");
                }
//                if (steden.size() < 3) {
//                    JOptionPane.showMessageDialog(hereApp, "U moet meer dan 2 adressen opgeven.");
//                }
                else {
                    try {
                        bezorgerSteden.printKortsteRoute(new AlgoritmeNB().vindKortsteRoute(steden));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        bezorglijst.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !bezorglijst.isSelectionEmpty()) {
                    String selectedItem = bezorglijst.getSelectedValue();
                    gekozenBezorger.setText("Gekozen bezorger: " + selectedItem);
                    for (int i = 0; i < bezorgerLijst.getBezorgers().size(); i++) {
                        if (selectedItem.substring(1, 5).equals(bezorgerLijst.getBezorgers().get(i).toString().substring(1, 5))) {
                            bezorgerID = bezorgerLijst.getBezorgers().get(i).getWerknemerID();
                        }
                    }
//                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

//        toevoegenAdres = new JButton("Toevoegen adres");
//        toevoegenAdres.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String straatnaamSpaces = straatnaamJtf.getText();
//                straatnaamStr = adressenLijst.getStad();
//                huisnummerint = adressenLijst.getHuisnummer();
//                String stadStr = adressenLijst.getStraat();
//                try {
//                    getLongitudeLangitude(straatnaamStr, huisnummerint, stadStr);
//                } catch (IOException | JSONException ex) {
//                    ex.printStackTrace();
//                }
//
//                System.out.println(bezorgerSteden.getInitialSteden());
//            }
//        });
        //add(toevoegenAdres);
        add(startenRoute);
        add(panel3);
        add(panel);
        add(panel2);
        //add(gekozenBezorger);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    public int getBezorgerID() {
        return this.bezorgerID;
    }

    public static void main(String[] args) throws IOException, JSONException, SQLException {
        InplannenRoute h = new InplannenRoute();
        f.getAdressenBijProvincie("Gelderland");
    }

    public void getLongitudeLangitude(String straatnaam, int huisnummer, String stad) throws IOException, JSONException {
        URL apiurl = new URL("https://geocode.search.hereapi.com/v1/geocode?q=" + straatnaam + "+" + huisnummer + "%2C+" + stad + "&apiKey=" + apiKey);
        HttpURLConnection hr = (HttpURLConnection) apiurl.openConnection();

        if (hr.getResponseCode() == 200) {
            InputStream im = hr.getInputStream();
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(im));
            String line = br.readLine();

            JSONObject response = new JSONObject(line);
            System.out.println(response);

            JSONArray array = response.getJSONArray("items");

            JSONObject arrayItems = array.getJSONObject(0);

            JSONObject position = arrayItems.getJSONObject("position");

            JSONObject adressinfo = arrayItems.getJSONObject("address");

            latitude = position.getDouble("lat");
            longitude = position.getDouble("lng");
            provincie = adressinfo.getString("state");

            Stad nieuweStad = new Stad(stad, latitude, longitude, this.straatnaamStr, this.huisnummerint, provincie);
            bezorgerSteden.addStad(nieuweStad);

            System.out.println(position.getDouble("lat") + " : " + position.getDouble("lng") + " : " + adressinfo.getString("state"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}