package com.stelligent.fusemetrics.graph
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 3:17:46 PM
 * To change this template use File | Settings | File Templates.
 */
class GraphHints {

   String title
   String rgbColor = "109010"

   String xAxisLabel
   String yAxisLabel
      
   int lowerRange
   int upperRange

   int height
   int width


   public GraphHints( Map props ) {

      height = props["height"]
      width = props["width"]
      rgbColor = props["color"]



   }


}