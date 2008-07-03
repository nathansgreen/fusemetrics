package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 12:00:56 PM
 * To change this template use File | Settings | File Templates.
 */
class FindbugsHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public FindbugsHistogramPlugin() {

      _ranges = [0..0, 1..2, 2..4, 5..9, 10..19, 20..49, 50..50]
      _labels = [' 0 ', '< 3', '< 5', '< 10', '< 20', '< 50', '50 +']

   }


   public String getTitle() {
      "FindBugs Analysis Histogram"
   }

   public String getXAxisLabel() {
      "Ranges"
   }

   public String getYAxisLabel() {
      "Classes in Range"
   }



}