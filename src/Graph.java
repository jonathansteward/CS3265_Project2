import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;

public class Graph extends JFrame { //extends ApplicationFrame
    //Select crimes_year from crimesdb
    //Find all results where 2001-2018 and put it into array of correct size
    //getAverage of that array

    //loop through and find number of crimes for each one and put into another array
    public static int[] getNumEachYear(int[] arr1, int numYears, int numVals){
        int[] arr2 = new int[numYears];
        int k=0;
        int sum=0;

        for(int i=2001; i<=2018; i++){
            for(int j=0; j<numVals; j++) {
                if (arr1[j] == i) {
                    sum++;
                }
            }
            arr2[k]=sum;
            k++;
            sum=0;
        }
        return arr2;
    }

    //Input this data into a graph swing dataset like demo in Java looping through each instance of the array
    private XYDataset createSampleDataset(int[] arr) {
        XYSeries series1 = new XYSeries("Series 1");
        for (int i = 0; i < 18; i++) {
            series1.add(i + 1, arr[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    public Graph(final String title, int[] arr2) {

        super(title);
        XYDataset dataset = createSampleDataset(arr2);
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

}