package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 12:06:49 PM
 * To change this template use File | Settings | File Templates.
 */
class JUnitHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public JUnitHistogramPlugin() {

      _ranges = [ 1..2, 2..4, 5..9, 10..19, 20..49, 50..50]
      _labels = [ '< 3', '< 5', '< 10', '< 20', '< 50', '50 +']

   }


   public String getTitle() {
      "JUnit Test Count Histogram"
   }

   public String getXAxisLabel() {
      "Ranges"
   }

   public String getYAxisLabel() {
      "# of Tests In Suite"
   }
   

}