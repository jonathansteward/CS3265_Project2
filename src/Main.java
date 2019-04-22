import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch ( Exception e ) {
            System.out.println ("Couldn't load Mac L&F" + e);
        }

        int numVals = 999999;
        int numYears = 18;

        int[] arr1 = new int[numVals]; //for initial data move
        int[] arr2 = new int[numYears];

        Connection conn = null;
        Statement st;
        ResultSet rs;
        String sql;
        int year = 0, count1 = 0, count2 = 0, count3=0, count4=0, count5 =0;
        Scanner scanner = new Scanner(System.in);

        String databaseURL = "jdbc:mysql://localhost:3306/chicago_crimes_db?user=root&password=password&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CST"; //change user and password

        try {
            conn = DriverManager.getConnection(databaseURL);
            if (conn != null) {
                System.out.println("Connected to the database!!!!!");
                st = conn.createStatement();
                String type;
                //String text1;

                do {
                    System.out.print("Enter type of graph: ");
                    type = scanner.next();
                    //text1 = JOptionPane.showInputDialog("Enter type of graph: ");
                    //type = Integer.parseInt(text1);
                    if (type.equals("allyears")) { //change type numbers to names
                        sql = ("Select crime_year from chicago_crimes where crime_year between 2001 and 2018;"); //how many entries do we need to select
                        rs = st.executeQuery(sql);

                        for (int i = 0; i < numVals; i++) {
                            if (rs.next()) {
                                year = Integer.parseInt(rs.getString("crime_year"));
                                arr1[i] = year;
                            }
                        }

                        arr2 = Graph.getNumEachYear(arr1, numYears, numVals);

                        //chart implementation
                        final Graph graph = new Graph(
                                "Number of Crimes from 2001-2018",
                                arr2
                        );

                        graph.pack();
                        RefineryUtilities.centerFrameOnScreen(graph);
                        graph.setVisible(true);
                    } else if (type.equals("residencevsapartment")) {
                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'BURGLARY' AND location_desc = 'RESIDENCE';"); //how many entries do we need to select
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count1 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'BURGLARY' AND location_desc = 'APARTMENT';");
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count2 = rs.getInt(1);
                        }

                        final TwoCountComparison twoCountComparison = new TwoCountComparison("Residence Burglary vs. Apartment Burglary",
                                count1, count2, "Residence", "Apartment", "Burglaries");
                        twoCountComparison.pack();
                        RefineryUtilities.centerFrameOnScreen(twoCountComparison);
                        twoCountComparison.setVisible(true);

                    } else if (type.equals("sidewalkvsbar")) {
                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'THEFT' AND location_desc = 'SIDEWALK';"); //how many entries do we need to select
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count1 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'THEFT' AND location_desc = 'BAR OR TAVERN';");
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count2 = rs.getInt(1);
                        }

                        final TwoCountComparison twoCountComparison = new TwoCountComparison("Sidewalk Theft vs. Bar or Tavern Theft",
                                count1, count2, "Sidewalk", "Bar or Tavern", "Thefts");
                        twoCountComparison.pack();
                        RefineryUtilities.centerFrameOnScreen(twoCountComparison);
                        twoCountComparison.setVisible(true);
                    } else if (type.equals("arrestsovertime")){ //ArrestsVsTime
                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE arrest = 'TRUE' and crime_year = '2012';"); //how many entries do we need to select
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count1 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE arrest = 'TRUE' and crime_year = '2013';");
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count2 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE arrest = 'TRUE' and crime_year = '2014';"); //how many entries do we need to select
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count3 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE arrest = 'TRUE' and crime_year = '2015';");
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count4 = rs.getInt(1);
                        }

                        final ArrestsVsTime arrests_over_time = new ArrestsVsTime("Arrests Over Time", count1, count2, count3, count4);
                        arrests_over_time.pack();
                        RefineryUtilities.centerFrameOnScreen(arrests_over_time);
                        arrests_over_time.setVisible(true);
                    } else if (type.equals("narcoticsarrests")) {
                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'NARCOTICS' and crime_year = '2010';"); //how many entries do we need to select
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count1 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'NARCOTICS' and crime_year = '2011';");
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count2 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'NARCOTICS' and crime_year = '2012';"); //how many entries do we need to select
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count3 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'NARCOTICS' and crime_year = '2013';");
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count4 = rs.getInt(1);
                        }

                        sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'NARCOTICS' and crime_year = '2014';");
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            count5 = rs.getInt(1);
                        }

                        final NarcoticsVsTime narcotic_crimes_over_time = new NarcoticsVsTime("Narcotics Crimes Over Time", count1,
                                count2, count3, count4, count5);
                        narcotic_crimes_over_time.pack();
                        RefineryUtilities.centerFrameOnScreen(narcotic_crimes_over_time);
                        narcotic_crimes_over_time.setVisible(true);
                    } else{
                        JOptionPane.showMessageDialog(null,"Thank you for using our application");
                    }
                } while(!type.equals("end"));
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }
}
