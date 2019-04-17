//Bar Chart
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class TwoCountComparison extends JFrame {

        public TwoCountComparison(final String title, int count1, int count2,
                                  String Category1, String Category2, String cName) {

            super(title);

            final CategoryDataset dataset = createDataset(count1,count2, Category1, Category2, cName);
            final JFreeChart chart = createChart(dataset, title, cName);
            final ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            setContentPane(chartPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        /**
         * Returns a sample dataset.
         *
         * @return The dataset.
         */
        private CategoryDataset createDataset(int count1, int count2, final String c1, final String c2, final String cName) {
            // row keys...

            // column keys are c1 and c2

            // create the dataset...
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(count1, cName, c1);
            dataset.addValue(count2, cName, c2);

            return dataset;

        }

        /**
         * Creates a sample chart.
         *
         * @param dataset  the dataset.
         *
         * @return The chart.
         */
        private JFreeChart createChart(final CategoryDataset dataset, String title, String cName) {

            // create the chart...
            final JFreeChart chart = ChartFactory.createBarChart(
                    title,         // chart title
                    "Types of " + cName,               // domain axis label
                    "Number of " + cName,                  // range axis label
                    dataset,                  // data
                    PlotOrientation.VERTICAL, // orientation
                    true,                     // include legend
                    true,                     // tooltips?
                    false                     // URLs?
            );

            // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

            // set the background color for the chart...
            chart.setBackgroundPaint(Color.white);

            // get a reference to the plot for further customisation...
            final CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);

            // set the range axis to display integers only...
            final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            // disable bar outlines...
            final BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);

            // set up gradient paints for series...
            final GradientPaint gp0 = new GradientPaint(
                    0.0f, 0.0f, Color.blue,
                    0.0f, 0.0f, Color.lightGray
            );
            final GradientPaint gp1 = new GradientPaint(
                    0.0f, 0.0f, Color.green,
                    0.0f, 0.0f, Color.lightGray
            );

            renderer.setSeriesPaint(0, gp0);

            final CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
            );
            // OPTIONAL CUSTOMISATION COMPLETED.

            return chart;

        }
}
