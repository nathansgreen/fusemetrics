package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 12:02:05 PM
 * To change this template use File | Settings | File Templates.
 */
class JavaNcssHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public JavaNcssHistogramPlugin() {

      _ranges = [0..24, 25..49, 50..99, 100..199, 200..499, 500..500]
      _labels = [ '< 25', '< 50', '< 100', '< 200', '< 500', '500+']

   }


   public String getTitle() {
      "JavaNcss Class LOC Histogram"
   }

   public String getXAxisLabel() {
      "Ranges"
   }

   public String getYAxisLabel() {
      "Classes in Range"
   }


}