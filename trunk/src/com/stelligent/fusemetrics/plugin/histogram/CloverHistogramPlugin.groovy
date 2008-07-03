package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 11:51:13 AM
 * To change this template use File | Settings | File Templates.
 */
class CloverHistogramPlugin extends HistogramBase implements HistogramPlugin {


   public CloverHistogramPlugin() {

      _ranges = [0..5, 10..20, 20..50, 50..90, 90..100]
      _labels = ['< 5%', '< 20%', '< 50%', '< 90%', '90% +']

   }


   public String getTitle() {
      "Clover Coverage Histogram"
   }

   public String getXAxisLabel() {
      "Coverage Ranges"
   }

   public String getYAxisLabel() {
      "Classes in Range"
   }





}