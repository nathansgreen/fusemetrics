package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 12:09:51 PM
 * To change this template use File | Settings | File Templates.
 */
class SimianHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public SimianHistogramPlugin() {

      _ranges = [0..0, 1..2, 2..4, 5..9, 10..19, 20..49, 50..50]
      _labels = [' 0 ', '< 3', '< 5', '< 10', '< 20', '< 50', '50 +']

   }


   public String getTitle() {
      "Simian Duplication Histogram"
   }

   public String getXAxisLabel() {
      "Ranges"
   }

   public String getYAxisLabel() {
      "Files in Range"
   }



}