package com.stelligent.fusemetrics.plugin.histogram

import com.stelligent.fusemetrics.graph.GraphHints
import com.stelligent.fusemetrics.MetricConfig

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 1:54:17 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class HistogramBase {

//   protected int chartHeight = 200
//   protected int chartWidth = 375

   protected ArrayList _labels = null
   protected ArrayList _ranges = null


   protected Map labelBucket = [:]


   protected GraphHints _hints = new GraphHints( [ height: 200, width: 375, color: "109010" ])






   public void accept(ArrayList data) {

      data.each{ Number value ->

         assignToBucket( value, _ranges, _labels, labelBucket)

      }

   }

   public int getBucketCount(String label) {

      if (labelBucket.containsKey(label)) {
         return labelBucket[label].size()
      } else {
         return 0
      }


   }

   public int getHeight() {
      return _hints.height
   }

   public int getWidth() {
      return _hints.width
   }




   public ArrayList getLabels() {
      return _labels
   }

   public ArrayList getBuckets() {

      def buckets = []

      labels.each{ l ->

         buckets << getBucketCount(l)

      }

      return buckets

   }





   void setGraphRange( int lower, int upper ) {

      _hints.lowerRange = lower
      _hints.upperRange = upper
   }




   public GraphHints getGraphHints() {

      _hints.title = getTitle()
      _hints.xAxisLabel = getXAxisLabel()
      _hints.yAxisLabel = getYAxisLabel()

      return _hints

   }



   public void config(MetricConfig mc) {

      if (mc.getGraphColor() != null) {
         _hints.rgbColor = mc.getGraphColor()
      }
   }






   protected void assignToBucket( Number val, ArrayList ranges, ArrayList labels, Map labelMap) {

      if (labels == null) {
         println "WARN: HistogramBase - null label provided"
         return
      }

      String lastLabel = labels[-1]

      boolean found = false;

      for (int i = 0; i < ranges.size; i++ ) {
         Range r = ranges[i]
         String label = labels[i]

         if (r.contains(val)) {

            addToBucket(labelMap, label, val)
            found = true
         }

      }

      if (!found) {
         addToBucket(labelMap, lastLabel, val)
      }

   }



   private void addToBucket(Map map, String label, val) {

      if (!map.containsKey(label)) {
          map[label] = []
      }

      map[label] << val

   }



   protected void setLabels( pLabels ) {
      _labels = pLabels
   }

   protected void setRanges( pRanges ) {
      _ranges = pRanges
   }

}