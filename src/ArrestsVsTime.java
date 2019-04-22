import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class ArrestsVsTime extends JFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public ArrestsVsTime(final String title, int count1, int count2, int count3, int count4) {
        super(title);
        final CategoryDataset dataset = createDataset(count1, count2, count3, count4);
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
    private CategoryDataset createDataset(int count1, int count2, int count3, int count4) {

        // row keys...
        final String series1 = "Arrest Crimes";

        // column keys...
        final String year1 = "2012";
        final String year2 = "2013";
        final String year3 = "2014";
        final String year4 = "2015";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(count1, series1, year1);
        dataset.addValue(count2, series1, year2);
        dataset.addValue(count3, series1, year3);
        dataset.addValue(count4, series1, year4);

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
                "Arrests Over Time",       // chart title
                "Year",                 // domain axis label
                "Number of Arrests",       // range axis label
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
