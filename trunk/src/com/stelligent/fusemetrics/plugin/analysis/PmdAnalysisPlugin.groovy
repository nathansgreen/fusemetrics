package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.Config
import com.stelligent.fusemetrics.util.FileFinder

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 4:24:13 PM
 * To change this template use File | Settings | File Templates.
 */
class PmdAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {

   public void exec(MetricConfig config, Map data) {

      def docs = findDocs(config, "PMD-results.xml", data, data["pmd_cache_docs"])

      //getPmdDocuments(config, data)

      def file_pmd_array = []

      int pmd_violations = 0

      docs.each{ pmdDoc ->


         pmdDoc.file.each{ doc ->
            int violations = doc.violation.size()

            pmd_violations += violations

            file_pmd_array << violations
         }
      }



      data["pmd_violations"] = pmd_violations
      data["pmd_violation_population"] = file_pmd_array

      data["pmd_cache_docs"] = docs

   }


//   private getPmdDocuments( MetricConfig metric, Map data) {
//
//      if (data["pmd_cache_docs"] != null) {
//         return data["pmd_cache_docs"]
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
//         def pattern = ~"PMD-results.xml"
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
//         def doc = new XmlSlurper().parse(file)
//
//         if (doc != null) {
//            docs << doc
//         }
//      }
//
//      return docs
//
//   }



}