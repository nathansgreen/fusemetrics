package com.stelligent.fusemetrics.graph

import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.category.CategoryDataset
import org.jfree.chart.axis.CategoryAxis
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.renderer.category.BarRenderer
import org.jfree.chart.JFreeChart
import java.awt.Color
import org.jfree.chart.ChartUtilities

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 2:32:41 PM
 * To change this template use File | Settings | File Templates.
 */
class GHistogram {



   void build(String imagePath, GraphHints hints, ArrayList labels, ArrayList buckets) {

      CategoryDataset ds = new DefaultCategoryDataset()

      for (int i = 0; i < labels.size; i++) {
         ds.addValue( buckets[i], "0", labels[i])
      }


      

      def categoryAxis = new CategoryAxis()
      def valueAxis = new NumberAxis()

      valueAxis.label = hints.yAxisLabel
      categoryAxis.label = hints.xAxisLabel

      if (hints.upperRange != hints.lowerRange) {
         valueAxis.range = new org.jfree.data.Range( hints.lowerRange, hints.upperRange)
      }


      def plot = new CategoryPlot()
      plot.dataset = ds
      plot.domainAxis =  categoryAxis
      plot.rangeAxis =  valueAxis
      plot.outlinePaint = null

      plot.renderer = new BarRenderer()
      plot.renderer.setDrawBarOutline(true)
      plot.renderer.setSeriesPaint(0, new Color( Integer.parseInt(hints.rgbColor, 16)))



      def chart = new JFreeChart(hints.title, JFreeChart.DEFAULT_TITLE_FONT, plot, false)

      chart.borderVisible = true


      ChartUtilities.saveChartAsPNG(   new File(imagePath), chart, hints.width, hints.height)




   }
}