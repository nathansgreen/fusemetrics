package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.util.FileFinder
import java.text.DecimalFormat

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 6:35:54 PM
 * To change this template use File | Settings | File Templates.
 */
class SimianAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {


   static private DecimalFormat formatter = new DecimalFormat("##.##%")


   public void exec(MetricConfig config, Map data) {


      def docs = findDocs(config, "simian.xml", data, data["simian_cache_docs"])

      //findSimianDocs(config, data)

      int total_dup = 0
      int total_loc = 0

      docs.each{ doc ->

         int dup = Integer.parseInt(doc.check.summary.@duplicateLineCount.text())
         int loc = Integer.parseInt(doc.check.summary.@totalSignificantLineCount.text())

         total_dup += dup
         total_loc += loc

      }


      double avg_dup = (float) total_dup / (float) total_loc

      def dup_str = formatter.format(avg_dup)

      data["loc"] = total_loc
      data["simian_loc"] = total_loc

      data["duplication"] = dup_str
      data["simian_duplication"] = dup_str

      

      data["simian_cache_docs"] = docs


   }

//   private findSimianDocs( MetricConfig metric, Map data ) {
//
//      if (data["simian_cache_docs"] != null) {
//         return data["simian_cache_docs"]
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
//         def pattern = ~"simian.xml"
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
//
//
//   }

}