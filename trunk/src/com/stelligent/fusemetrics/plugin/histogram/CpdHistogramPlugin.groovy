package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 11:58:02 AM
 * To change this template use File | Settings | File Templates.
 */
class CpdHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public CpdHistogramPlugin() {

      _ranges = [0..0, 1..2, 2..4, 5..9, 10..19, 20..49, 50..50]
      _labels = [' 0 ', '< 3', '< 5', '< 10', '< 20', '< 50', '50 +']

   }


   public String getTitle() {
      "Cpd Duplication Histogram"
   }

   public String getXAxisLabel() {
      "Ranges"
   }

   public String getYAxisLabel() {
      "Files in Range"
   }

   

}