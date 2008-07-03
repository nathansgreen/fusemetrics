package com.stelligent.fusemetrics.plugin.histogram

import com.stelligent.fusemetrics.graph.GraphHints
import com.stelligent.fusemetrics.MetricConfig

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 1:41:41 PM
 * To change this template use File | Settings | File Templates.
 */
interface HistogramPlugin {


   void accept( ArrayList data )

   ArrayList getLabels()

   int getBucketCount( String label )

   void setGraphRange( int lower, int upper )

//   void setGraphColorHex( String rgbHex )

   ArrayList getBuckets();

   GraphHints getGraphHints();


   void config( MetricConfig mc)


   String getTitle()
   int getHeight()
   int getWidth()

   String getXAxisLabel()
   String getYAxisLabel()
   
   /*
   int getGraphRangeLower()
   int getGraphRangeUpper()
   String getGraphColor( String rgb )
   */
}