import org.jfree.ui.RefineryUtilities;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        int numVals = 999999;
        int numYears = 18;

        int[] arr1 = new int[numVals]; //for initial data move
        int[] arr2 = new int[numYears];

        Connection conn = null;
        Statement st;
        ResultSet rs;
        String sql;
        int year = 0, count1 = 0, count2 = 0;
        Scanner scanner = new Scanner(System.in);

        String databaseURL = "jdbc:mysql://localhost:3306/chicago_crimes_db?user=root&password=password&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CST"; //change user and password

        try {
            conn = DriverManager.getConnection(databaseURL);
            if (conn != null) {
                System.out.println("Connected to the database");
                st = conn.createStatement();
                System.out.print("Enter type of graph: ");
                int type = scanner.nextInt();

                if(type==1) {
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
                            "XYLineAndShapeRenderer Demo",
                            arr2
                    );

                    graph.pack();
                    RefineryUtilities.centerFrameOnScreen(graph);
                    graph.setVisible(true);
                }else if(type==2){
                    sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'BURGLARY' AND location_desc = 'RESIDENCE';"); //how many entries do we need to select
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        count1 = rs.getInt(1);
                    }

                    sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'BURGLARY' AND location_desc = 'APARTMENT';");
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        count2 = rs.getInt(1);
                    }

                    final TwoCountComparison twoCountComparison = new TwoCountComparison("Bar Chart Demo", count1, count2);
                    twoCountComparison.pack();
                    RefineryUtilities.centerFrameOnScreen(twoCountComparison);
                    twoCountComparison.setVisible(true);

                }else if(type==3){
                    sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'THEFT' AND location_desc = 'SIDEWALK';"); //how many entries do we need to select
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        count1 = rs.getInt(1);
                    }

                    sql = ("SELECT COUNT(*) FROM chicago_crimes WHERE primary_type = 'THEFT' AND location_desc = 'BAR OR TAVERN';");
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        count2 = rs.getInt(1);
                    }

                    final TwoCountComparison twoCountComparison = new TwoCountComparison("Bar Chart Demo", count1, count2);
                    twoCountComparison.pack();
                    RefineryUtilities.centerFrameOnScreen(twoCountComparison);
                    twoCountComparison.setVisible(true);
                }else{
                    System.out.print("4");
                }

                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }
}
