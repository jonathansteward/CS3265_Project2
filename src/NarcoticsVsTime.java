import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class NarcoticsVsTime extends JFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public NarcoticsVsTime (final String title, int count1, int count2,
                            int count3, int count4, int count5) {
        super(title);
        final CategoryDataset dataset = createDataset(count1,count2,count3,count4,count5);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private CategoryDataset createDataset(int count1, int count2, int count3, int count4, int count5) {

        final String series1 = "Narcotic Crimes";

        final String year3 = "2012";
        final String year4 = "2013";
        final String year5 = "2014";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(count3, series1, year3);
        dataset.addValue(count4, series1, year4);
        dataset.addValue(count5, series1, year5);

        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  a dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
                "Narcotic Crimes Over Time",       // chart title
                "Year",                 // domain axis label
                "Number of Narcotic Crimes",       // range axis label
                dataset,                   // data
                PlotOrientation.VERTICAL,  // orientation
                false,                     // include legend
                true,                      // tooltips
                false                      // urls
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.black);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        return chart;
    }

}
