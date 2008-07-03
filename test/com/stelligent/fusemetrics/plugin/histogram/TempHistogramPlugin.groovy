package com.stelligent.fusemetrics.plugin.histogram

import com.stelligent.fusemetrics.plugin.histogram.HistogramPlugin
import com.stelligent.fusemetrics.plugin.histogram.HistogramBase

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 2:08:08 PM
 * To change this template use File | Settings | File Templates.
 */
class TempHistogramPlugin extends HistogramBase implements HistogramPlugin {

   public String getTitle() {
      "title"
   }



   void setRanges( ArrayList pRanges ) {
      this._ranges = pRanges
   }


   void setLabels( ArrayList pLabels ) {
      this._labels = pLabels
   }

   public String getXAxisLabel() {
      "Buckets"
   }

   public String getYAxisLabel() {
      "Number of points"
   }


}