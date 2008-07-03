package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 12:08:27 PM
 * To change this template use File | Settings | File Templates.
 */
class PmdHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public PmdHistogramPlugin() {

      _ranges = [ 1..2, 2..4, 5..9, 10..19, 20..49, 50..50]
      _labels = [ '< 3', '< 5', '< 10', '< 20', '< 50', '50 +']

   }


   public String getTitle() {
      "PMD Violation Histogram"
   }

   public String getXAxisLabel() {
      "Violation Counts"
   }

   public String getYAxisLabel() {
      "# of Classes"
   }


}