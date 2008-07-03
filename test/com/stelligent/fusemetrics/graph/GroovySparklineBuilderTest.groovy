package com.stelligent.fusemetrics.graph
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 2:56:23 PM
 * To change this template use File | Settings | File Templates.
 */
class GroovySparklineBuilderTest extends GroovyTestCase {


   public void testSparklineBuilder() {

      File f = new File("etc/graph/test1.png")

      if (f.exists()) {
         f.delete()
      }

      def filename = GraphBuilder.buildSparkline("test1", [1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1], "etc/graph")

      assertEquals( "test1.png", filename)

      File f2 = new File("etc/graph/test1.png")

      assertTrue(f2.exists())

   }

}