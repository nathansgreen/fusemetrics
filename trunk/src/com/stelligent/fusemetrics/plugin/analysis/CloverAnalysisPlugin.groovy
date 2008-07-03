package com.stelligent.fusemetrics.plugin.analysis

import java.text.DecimalFormat
import com.stelligent.fusemetrics.MetricConfig
import nu.xom.Builder
import nu.xom.Element
import com.stelligent.fusemetrics.util.ElementsUtil
import com.stelligent.fusemetrics.util.FileFinder

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 6:06:34 PM
 * To change this template use File | Settings | File Templates.
 */
class CloverAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {


   static private DecimalFormat formatter = new DecimalFormat("##.##%")


   public void exec(MetricConfig config, Map data) {


      println "directory: ${config.getFilepath()}\n"

      def docs = findDocs(config, "clover.xml", data, data["clover_cache_docs"], { file -> return new Builder().build(new File(file)).rootElement })

      // only going to deal with one file for now.

      if (docs.size() > 0) {
         def doc = docs[0]
         calculateTopLevelMetric(doc, data)

         calculatePopulationMetrics(doc, data)
      } else {
         data["error"] += "No Clover Metrics data found.\n"
      }


   }







   void calculateTopLevelMetric(Element doc, Map data) {


      def branch_str = "0%"
      def line_str = "0%"
      def coverage_str = "0%"




      def metrics = doc.getFirstChildElement("project").getFirstChildElement("metrics")

      double branch_coverage = getCloverCoverage(metrics, "conditionals")
      double line_coverage = getCloverCoverage(metrics, "statements")

      double avg_coverage = (line_coverage + branch_coverage) / 2.0


      branch_str = formatter.format(branch_coverage)
      line_str = formatter.format(line_coverage)
      coverage_str = formatter.format(avg_coverage)


      data["coverage"] = avg_coverage
      data["clover_coverage"] = avg_coverage

      data["coverage_branch"] = branch_coverage
      data["clover_coverage_branch"] = branch_coverage

      data["coverage_line"] = line_coverage
      data["clover_coverage_line"] = line_coverage


      data["coverage_fmt"] = coverage_str
      data["clover_coverage_fmt"] = coverage_str

      data["coverage_branch_fmt"] = branch_str
      data["clover_coverage_branch_fmt"] = branch_str

      data["coverage_line_fmt"] = line_str
      data["clover_coverage_line_fmt"] = line_str
   }





   void calculatePopulationMetrics(Element doc, Map data) {


      def coveragePopulation = []

      ElementsUtil.toArray(doc.getFirstChildElement('project').getChildElements('package')).each {   pkg ->

         ElementsUtil.toArray(pkg.getChildElements('file')).each {  file ->

            coveragePopulation << calculateCoverage(file)

         }
      }



      data["coverage_population"] = coveragePopulation

   }

   double calculateCoverage(Element el) {

      def branch_coverage = getCloverCoverage(el.getFirstChildElement('metrics'), "conditionals")
      def line_coverage = getCloverCoverage(el.getFirstChildElement('metrics'), "statements")

      def avg_coverage = (line_coverage + branch_coverage) / 2.0

      return avg_coverage

   }



   private Float getCloverCoverage(  el, String attribute ) {

      def covered = el.getAttribute("covered"+attribute).value as float
      def total = el.getAttribute(attribute).value as float

      return (covered / total)
   }



   

}