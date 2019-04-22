import java.awt.*;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

public class TwoCountComparison extends JFrame {

        public TwoCountComparison(final String title, int count1, int count2,
                                  String Category1, String Category2, String cName) {

            super(title);

            final CategoryDataset dataset = createDataset(count1,count2, Category1, Category2, cName);
            final JFreeChart chart = createChart(dataset, title, cName);
            final ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            setContentPane(chartPanel);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        /**
         * Returns a sample dataset.
         *
         * @return The dataset.
         */
        private CategoryDataset createDataset(int count1, int count2, final String c1, final String c2, final String cName) {
            // row keys...

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

            String fontName = "Lucida Sans";
            StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();

            theme.setTitlePaint( Color.decode( "#4572a7" ) );
            theme.setExtraLargeFont( new Font(fontName,Font.PLAIN, 16) ); //title
            theme.setLargeFont( new Font(fontName,Font.BOLD, 15)); //axis-title
            theme.setRegularFont( new Font(fontName,Font.PLAIN, 11));
            theme.setRangeGridlinePaint( Color.decode("#C0C0C0"));
            theme.setPlotBackgroundPaint( Color.white );
            theme.setChartBackgroundPaint( Color.white );
            theme.setGridBandPaint( Color.red );
            theme.setAxisOffset( new RectangleInsets(0,0,0,0) );
            theme.setBarPainter(new StandardBarPainter());
            theme.setAxisLabelPaint( Color.decode("#666666")  );
            theme.apply( chart );
            chart.getCategoryPlot().setOutlineVisible( false );
            chart.getCategoryPlot().getRangeAxis().setAxisLineVisible( false );
            chart.getCategoryPlot().getRangeAxis().setTickMarksVisible( false );
            chart.getCategoryPlot().setRangeGridlineStroke( new BasicStroke() );
            chart.getCategoryPlot().getRangeAxis().setTickLabelPaint( Color.decode("#666666") );
            chart.getCategoryPlot().getDomainAxis().setTickLabelPaint( Color.decode("#666666") );
            chart.setTextAntiAlias( true );
            chart.setAntiAlias( true );
            chart.getCategoryPlot().getRenderer().setSeriesPaint( 0, Color.decode( "#4572a7" ));
            BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
            rend.setShadowVisible( true );
            rend.setShadowXOffset( 2 );
            rend.setShadowYOffset( 0 );
            rend.setShadowPaint( Color.decode( "#C0C0C0"));
            rend.setMaximumBarWidth( 0.1);

            return chart;

        }
}
