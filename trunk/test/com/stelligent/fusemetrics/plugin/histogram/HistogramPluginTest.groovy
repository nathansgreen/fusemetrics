package com.stelligent.fusemetrics.plugin.histogram

import com.stelligent.fusemetrics.plugin.histogram.TempHistogramPlugin

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 2:04:49 PM
 * To change this template use File | Settings | File Templates.
 */
class HistogramPluginTest extends GroovyTestCase {


   public void testHistogramBucketing() {

      TempHistogramPlugin tmp = new TempHistogramPlugin()

      tmp.setRanges( [ 0..1, 2..4, 5..9, 10..19, 20..20 ] )
      tmp.setLabels( [ "< 2", "< 5", "< 10", "< 20", "20+"])

      tmp.accept( [ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15, 25, 35, 45 ])

      assertEquals( 1, tmp.getBucketCount("< 2"))
      assertEquals( 3, tmp.getBucketCount("< 5"))
      assertEquals( 5, tmp.getBucketCount("< 10"))
      assertEquals( 6, tmp.getBucketCount("< 20"))
      assertEquals( 3, tmp.getBucketCount("20+"))


      def buckets = tmp.getBuckets()

      assertEquals(5, buckets.size())
      assertEquals(3, buckets[1])

   }


}