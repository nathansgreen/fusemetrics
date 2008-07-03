package com.stelligent.fusemetrics.graph

import com.stelligent.fusemetrics.plugin.histogram.TempHistogramPlugin

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 3:01:30 PM
 * To change this template use File | Settings | File Templates.
 */
class HistogramGraphBuilderTest extends GroovyTestCase {

      public void testBuildingSampleHistogram() {

         File f = new File("etc/graph/histo1.png")

         if (f.exists()) {
            f.delete()
         }


         TempHistogramPlugin tmp = new TempHistogramPlugin()

         tmp.setRanges( [ 0..1, 2..4, 5..9, 10..19, 20..20 ] )
         tmp.setLabels( [ "< 2", "< 5", "< 10", "< 20", "20+"])

         tmp.accept( [ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15, 25, 35, 45 ])


         String filename = GraphBuilder.buildHistogram("histo1", tmp, "etc/graph")

         assertEquals( "histo1.png", filename)

         File f2 = new File("etc/graph/histo1.png");

         assertTrue(f2.exists())




      }

}