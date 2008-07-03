package com.stelligent.fusemetrics.plugin.histogram
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 11:57:01 AM
 * To change this template use File | Settings | File Templates.
 */
class CoberturaHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public CoberturaHistogramPlugin() {

//      _ranges = [0..1, 1..2, 2..5, 5..10, 10..25, 25..50, 50..75, 75..99, 99..100]
//      _labels = ['< 1%', '< 2%', '< 5%', '< 10%', '< 25%', '< 50%', '< 75%', '< 99%', '99% +']


      _ranges = [0..5, 10..20, 20..50, 50..90, 90..100]
      _labels = ['< 5%', '< 20%', '< 50%', '< 90%', '90% +']
      
   }


   public String getTitle() {
      "Cobertura Coverage Histogram"
   }

   public String getXAxisLabel() {
      "Coverage Ranges"
   }

   public String getYAxisLabel() {
      "Classes in Range"
   }



}