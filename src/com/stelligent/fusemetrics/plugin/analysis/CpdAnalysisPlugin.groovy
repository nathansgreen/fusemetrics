package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.util.FileFinder
import java.text.DecimalFormat

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 6:17:20 PM
 * To change this template use File | Settings | File Templates.
 */
class CpdAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {

   static private DecimalFormat formatter = new DecimalFormat("##.##%")


   public void exec(MetricConfig config, Map data) {

      def docs = findDocs(config, "cpd-results.xml", data, data["cpd_cache_docs"])

      calculateTopLevelMetric(docs, data)

      calculatePopulationMetrics(docs, data)


      data["cpd_cache_docs"] = docs

   }



   void calculatePopulationMetrics( ArrayList docs, Map data) {


      def dup_population = []

      def pathHash = [:]

      docs.each { doc ->

         doc.duplication.each { dup ->

            dup.file.each { file ->

               pathHash[file.@path] = ( pathHash.containsKey(file.@path) ? pathHash[file.@path]++ : 1 )

            }
         }
         

      }


      pathHash.keySet().each { key ->
         dup_population << pathHash[key]
      }


      data["duplication_population"] = dup_population
      data["cpd_duplication_population"] = dup_population

   }

   void calculateTopLevelMetric( ArrayList docs, Map data) {
      def file_cpd_array = []

      int cpd_violations = 0

      docs.each{ cpdDoc ->


         cpdDoc.duplication.each {  elem ->


            int violations = Integer.parseInt(elem.@lines.text())

            cpd_violations += violations

            file_cpd_array << violations

         }

      }


      int loc = 1

      if (data.get("loc") != null) {
         loc = data["loc"]
      }

      float avg_cpd_violations = (double) cpd_violations / (double) loc



      data["duplication"] = avg_cpd_violations
      data["cpd_duplication"] = avg_cpd_violations
      data["duplication_fmt"] = formatter.format( avg_cpd_violations )
      data["cpd_duplication_fmt"] = formatter.format( avg_cpd_violations )


   }




//   private getCpdDocuments( MetricConfig metric, Map data) {
//
//      if (data["cpd_cache_docs"] != null) {
//         return data["cpd_cache_docs"]
//      }
//
//      def files = []
//
//      def docs = []
//
//
//      if (metric == null) {
//         data["error"] += "Unable to load metric configuration for: ${metric.name}\n"
//      } else {
//
//
//         def ff = new FileFinder()
//
//
//         def pattern = ~"cpd-results.xml"
//
//         if (metric.getFileRegexp() != null) {
//            pattern = ~metric.getFileRegexp()
//         }
//
//         files = ff.findFiles(metric.getFilepath(), pattern)
//
//
//      }
//
//      data["info"] += "Found ${files.size} metrics files for ${metric.name}\n"
//
//
//      files.each{ file ->
//
//         def doc =
//         if (doc != null) {
//            docs << doc
//         }
//      }
//
//      return docs
//
//   }

}