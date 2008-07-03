package com.stelligent.fusemetrics.graph

import com.stelligent.fusemetrics.plugin.histogram.HistogramPlugin

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 2:21:15 PM
 * To change this template use File | Settings | File Templates.
 */
class GraphBuilder {




   static String  buildHistogram(String filename, HistogramPlugin hp, String directory) {

      GHistogram gh = new GHistogram()



      String imagePath = "${filename}.png"

      gh.build( "${directory}/${imagePath}", hp.getGraphHints(), hp.labels, hp.buckets)

      return imagePath



   }

   static String buildSparkline(String filename, ArrayList data, String directory) {

      GSparky gs = new GSparky()

      String imagePath = "${filename}.png"

      gs.build( data, "${directory}/${imagePath}")

      return imagePath
      
   }
}