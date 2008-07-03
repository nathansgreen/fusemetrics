package com.stelligent.fusemetrics.engine

import com.stelligent.fusemetrics.Config
import com.stelligent.fusemetrics.PluginRegistry
import com.stelligent.fusemetrics.engine.analysis.SimpleAnalysisEngine
import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.PluginRegistry
import com.stelligent.fusemetrics.PluginRegistry

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 1:53:27 PM
 * To change this template use File | Settings | File Templates.
 */
class SimpleAnalysisEngineTest extends GroovyTestCase {


   public void testEngineFindsData()  {


      def config = new Config([search: "etc/sample1"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss"]))
      config.setRegistry(new PluginRegistry())



      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)

      assertEquals(193, data["loc"])
      assertEquals(5, data["classes"])
      assertEquals(6, data["max_complexity"])

      


   }


   public void testEngineFindsDataInMultipleFiles() {


      def config = new Config([search: "etc/sample2"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)

      assertEquals(386, data["loc"])
      assertEquals(10, data["classes"])
      assertEquals(6, data["max_complexity"])

   }



   public void testEngineFindsDataInMultipleFilesWithRegexp() {


      def config = new Config([search: "etc/sample3"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss", regexp: "javancss_.*\\.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)

      assertEquals(386, data["loc"])
      assertEquals(10, data["classes"])
      assertEquals(6, data["max_complexity"])

   }



   public void testPmdPlugin() {

      def config = new Config([search: "etc/pmd_sample"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss", regexp: "javancss_.*\\.xml"]))
      config.addMetricConfig( "pmd", new MetricConfig([name: "pmd", analysis: "pmd", regexp: "pmd_.*\\.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"

      assertEquals(21, data["pmd_violations"])

      def arry = data["pmd_violation_population"]

      println "Array: ${arry}"

   }

   public void testCpdPlugin() {

      def config = new Config([search: "etc/pmd_sample"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss", regexp: "javancss_.*\\.xml"]))
      config.addMetricConfig( "duplication", new MetricConfig([name: "duplication", analysis: "cpd", regexp: "cpd_.*\\.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"

      assertEquals("80.83%", data["duplication_fmt"])


   }



   public void testJDependPlugin() {

      def config = new Config([search: "etc/jdepend_sample"])
      config.addMetricConfig("coupling", new MetricConfig( [name: "coupling", analysis: "jdepend"]))
      config.setRegistry(new PluginRegistry())

      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"

      assertEquals(3, data["coupling_afferent_max"])
      assertEquals(7, data["coupling_efferent_max"])



   }



   public void testSimianPlugin() {

      def config = new Config([search: "etc/sample1"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss", regexp: "javancss_.*\\.xml"]))
      config.addMetricConfig( "duplication", new MetricConfig([name: "duplication", analysis: "simian", regexp: "simian.*\\.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"

      assertEquals("2.04%", data["duplication"])


   }



   public void testFindBugsPlugin() {
      def config = new Config([search: "etc/sample1"])
      config.addMetricConfig( "bugs", new MetricConfig([name: "bugs", analysis: "findbugs", regexp: "findbugs.xml"]))
      config.addMetricConfig( "duplication", new MetricConfig([name: "duplication", analysis: "simian", regexp: "simian.*\\.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"

      assertEquals(13, data["bugs"])

   }

   public void testCoberturaPlugin() {


      def config = new Config([search: "etc/cob_sample"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss", regexp: "javancss_.*\\.xml"]))
      config.addMetricConfig( "pmd", new MetricConfig([name: "pmd", analysis: "pmd", regexp: "pmd_.*\\.xml"]))
      config.addMetricConfig( "coverage", new MetricConfig([name: "coverage", analysis: "cobertura", filepath: "etc/cob_sample/coverage.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"


      assertEquals("100%", data["coverage_branch_fmt"])
      assertEquals("88.15%", data["coverage_line_fmt"])
      

   }


   public void testCloverPlugin() {


      def config = new Config([search: "etc/clover_sample"])
      config.addMetricConfig( "loc", new MetricConfig([name: "loc", analysis: "javancss", regexp: "javancss_.*\\.xml"]))
      config.addMetricConfig( "pmd", new MetricConfig([name: "pmd", analysis: "pmd", regexp: "pmd_.*\\.xml"]))
      config.addMetricConfig( "coverage", new MetricConfig([name: "coverage", analysis: "clover", filepath: "etc/clover_sample/clover.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"


      assertEquals("0.69%", data["coverage_branch_fmt"])
      assertEquals("0.71%", data["coverage_line_fmt"])
      assertEquals("0.7%", data["coverage_fmt"])


   }


   public void testJUnitPlugin() {


      def config = new Config([search: "etc/sample1"])
      config.addMetricConfig( "unittest", new MetricConfig([name: "unittest", analysis: "junit", regexp: "TEST-.+\\.xml"]))
      config.setRegistry(new PluginRegistry())


      def data = [:]

      def sae = new SimpleAnalysisEngine()

      sae.run( config, data)


      println "errors: ${data['errors']}"
      println "info: ${data['info']}"


      assertEquals( 15, data["test_count"])
      assertEquals( 1, data["test_failures"])

      
   }


}