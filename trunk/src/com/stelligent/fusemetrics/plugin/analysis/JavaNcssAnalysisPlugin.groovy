package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.Config
import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.util.FileFinder

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 2:39:10 PM
 * To change this template use File | Settings | File Templates.
 */
class JavaNcssAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {

   public void exec( MetricConfig metric, Map data ) {

      def docs = findDocs( metric, "javancss_metrics.xml", data, data["javancss_cache_docs"] )


      def class_loc_array = []

      int totalLOC = 0;

      int totalClasses = 0;

      int maxComplexity = 0

      def function_complexity_array = []


      docs.each{ doc ->


         int loc = Integer.parseInt(doc.packages.total.ncss.text())

         totalLOC += loc


         int classCount = Integer.parseInt(doc.packages.total.classes.text())

         totalClasses += classCount


         doc.functions.function.each{ method ->

            int complexityScore = Integer.parseInt(method.ccn.text())

            function_complexity_array << complexityScore

            if (complexityScore > maxComplexity) {
               maxComplexity = complexityScore
            }

         }


         doc.objects.object.each{ object ->

            int classLoc = Integer.parseInt(object.ncss.text())

            class_loc_array << classLoc
         }

      }


      data["max_complexity"] = maxComplexity
      data["javancss_max_complexity"] = maxComplexity

      data["classes"] = totalClasses
      data["javancss_total_classes"] = totalClasses

      data["loc"] = totalLOC
      data["javancss_total_loc"] = totalLOC


      data["complexity_population"] = function_complexity_array
      data["javancss_complexity_population"] = function_complexity_array

      data["loc_population"] = class_loc_array
      data["javancss_loc_population"] = class_loc_array



      data["javancss_cache_docs"] = docs

   }




//    private getJavaNcssMetricDocuments( metric, data ) {
//
//       // if we've already parsed the javancss files, we can store the XML documents here for reuse.
//
//       if (data["javancss_cache_docs"] != null) {
//          return data["javancss_cache_docs"]
//       }
//
//       def files = []
//
//       def docs = []
//
//
//       if (metric == null) {
//          data["error"] += "Unable to load metric configuration for: ${metricName}\n"
//       } else {
//
//
//          def ff = new FileFinder()
//
//
//          def pattern = ~"javancss_metrics.xml"
//
//          if (metric.getFileRegexp() != null) {
//             pattern = ~metric.getFileRegexp()
//          }
//
//          files = ff.findFiles(metric.getFilepath(), pattern)
//
//
//       }
//
//
//       files.each{ file ->
//
//          def doc = new XmlSlurper().parse(file)
//
//          if (doc != null) {
//             docs << doc
//          }
//       }
//
//       return docs
//
//    }
}