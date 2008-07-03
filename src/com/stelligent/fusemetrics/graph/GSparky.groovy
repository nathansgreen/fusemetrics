
package com.stelligent.fusemetrics.graph

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.StandardXYItemRenderer
import org.jfree.data.general.Dataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.chart.ChartUtilities
import org.jfree.ui.RectangleInsets
import java.awt.Color

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: May 21, 2008
 * Time: 4:55:07 PM
 * To change this template use File | Settings | File Templates.
 */
class GSparky {

    def DEFAULT_HEIGHT = 30
    def DEFAULT_WIDTH  = 150


    boolean build( def data, def imgPath, def height = DEFAULT_HEIGHT, def width = DEFAULT_WIDTH, def color = 0x888888 ) {

        def chart = buildChartFromData(data, color)
        return buildImageFromChart( chart: chart, height: height, width: width, path: imgPath)


    }





    private JFreeChart buildChartFromData( data, color ) {

        def dataset = generateDataset(data)

//        def xAxis = minimalAxis()
//        def yAxis = minimalAxis()

        def plot = new XYPlot()
        plot.dataset = dataset
        plot.domainAxis =  minimalAxis()
        plot.domainGridlinesVisible = false
        plot.domainCrosshairVisible = false
        plot.rangeGridlinesVisible = false
        plot.rangeCrosshairVisible = false
        plot.rangeAxis = minimalAxis()
        plot.outlinePaint = null
        plot.renderer = new StandardXYItemRenderer(StandardXYItemRenderer.LINES)
        plot.insets = new RectangleInsets(0, 0, 0, 0)
        plot.getRenderer().setSeriesPaint(0, new Color(color))



        // we do this to center the sparkline on the image, otherwise it can overrun the top or bottom on a
        // large swing.
        def avg = 0.0
        def max = 0.0
        def min = 0.0


        data.each{ it->
           avg += (it/data.size)

           if (it > max) { max = it}
           if (it < min) { min = it}
        }

        if (min == 0) {
           min = 0 - (max*0.1)
        }

        plot.rangeAxis.centerRange ( avg )
        plot.rangeAxis.setLowerBound(min*0.9)
        plot.rangeAxis.setUpperBound(max*1.1)

        def chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, plot, false)
        chart.borderVisible = false

        
        return chart


    }




    private boolean buildImageFromChart( args ) {

        ChartUtilities.saveChartAsPNG( new File(args.path), args.chart, args.width, args.height )

        return true
        
    }

    private Object minimalAxis() {

      def a = new NumberAxis()
      a.tickLabelsVisible = false
      a.tickMarksVisible = false
      a.axisLineVisible = false
      a.negativeArrowVisible = false
      a.positiveArrowVisible = false
      a.visible = false;

      return a
    }


    
    private Dataset generateDataset(def data) {

        def series = new XYSeries("Sparkline")

        def i = 0
        data.each { y -> series.add(i++, y)  }

        def dataset = new XYSeriesCollection()
        dataset.addSeries(series)

        return dataset

    }
}