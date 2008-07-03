package com.stelligent.fusemetrics
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 12:53:36 PM
 * To change this template use File | Settings | File Templates.
 */
class ConfigTest extends GroovyTestCase {

    public void testMetricConfig() {


        def c = new Config("test")


        assertTrue c.getSearchDirectory() == "test"
        assertEquals("test", c.getOutputDirectory())

        def mc = new MetricConfig([name: "metric1"])

        c.addMetricConfig("metric1", mc )

        mc = c.getMetricConfig("metric1")

        assertNotNull(mc)

        assertEquals("test", mc.getFilepath())
        assertNull(mc.getFileRegexp())
    }



   public void testConfigXml() {


      def c = Config.load("etc/testconfig1.xml")

      assertNotNull(c)

      assertEquals("test_1", c.getSearchDirectory())

      assertEquals("output_1", c.getOutputDirectory())

      assertNotNull( c.getMetricConfig("test_metric_1"))

      def mc = c.getMetricConfig("test_metric_2")

      assertEquals("metric2_plugin", mc.getAnalysisPluginName())
      

   }

}