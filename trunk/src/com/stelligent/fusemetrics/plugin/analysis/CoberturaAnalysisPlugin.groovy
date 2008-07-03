package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import java.text.DecimalFormat
import nu.xom.Builder
import nu.xom.Element
import com.stelligent.fusemetrics.util.ElementsUtil

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 5:38:54 PM
 * To change this template use File | Settings | File Templates.
 */
class CoberturaAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {


   static private DecimalFormat formatter = new DecimalFormat("##.##%")


   public void exec(MetricConfig config, Map data) {


//      println "directory: ${config.getFilepath()}\n"


      def docs = findDocs( config, "coverage.xml", data, data["cobertura_cache_docs"],
            { file -> return new Builder().build(new File(file)).rootElement })

      if (docs.size() > 0) {

         def doc = docs[0]
         calculateTopLevelMetric(doc, data)
         calculatePopulationMetrics(doc, data)
      } else {
         data["error"] += "Unable to find Cobertura coverage.xml for analysis.\n"
      }

   }




   void calculateTopLevelMetric(Element doc, Map data) {


      def branch_str = "0%"
      def line_str = "0%"
      def coverage_str = "0%"

      double branch_coverage = 0.0
      double line_coverage = 0.0
      double avg_coverage = 0.0

      if(doc != null){
         branch_coverage = Float.parseFloat(doc.getAttribute("branch-rate").value)
         line_coverage = Float.parseFloat(doc.getAttribute("line-rate").value)
         avg_coverage = (line_coverage + branch_coverage) / 2.0

         branch_str = formatter.format(branch_coverage)
         line_str = formatter.format(line_coverage)

         coverage_str = formatter.format(avg_coverage)
      }


      data["coverage"] = avg_coverage
      data["cobertura_coverage"] = avg_coverage

      data["coverage_branch"] = branch_coverage
      data["cobertura_coverage_branch"] = branch_coverage

      data["coverage_line"] = line_coverage
      data["cobertura_coverage_line"] = line_coverage


      data["coverage_fmt"] = coverage_str
      data["cobertura_coverage_fmt"] = coverage_str

      data["coverage_branch_fmt"] = branch_str
      data["cobertura_coverage_branch_fmt"] = branch_str

      data["coverage_line_fmt"] = line_str
      data["cobertura_coverage_line_fmt"] = line_str


   }

   void calculatePopulationMetrics(Element doc, Map data) {

      def coveragePopulation = []


      ElementsUtil.toArray(doc.getFirstChildElement('packages').getChildElements('package')).each {  pkg ->

         ElementsUtil.toArray(pkg.getFirstChildElement('classes').getChildElements('class')).each {  clazz ->

            double branch_coverage = Float.parseFloat(clazz.getAttribute("branch-rate").value)
            double line_coverage = Float.parseFloat(clazz.getAttribute("line-rate").value)
            double avg_coverage = (line_coverage + branch_coverage) / 2.0

            coveragePopulation << avg_coverage

         }

      }

      data["coverage_population"] = coveragePopulation
      data["cobertura_coverage_population"] = coveragePopulation
      
   }
}