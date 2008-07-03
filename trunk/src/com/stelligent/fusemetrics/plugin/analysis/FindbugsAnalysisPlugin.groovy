package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.util.FileFinder

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 6:50:31 PM
 * To change this template use File | Settings | File Templates.
 */
class FindbugsAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {

   public void exec(MetricConfig config, Map data) {


      def docs = findDocs(config, "findbugs.xml", data, data["findbug_cache_docs"])


      calculateTopLevelMetric(docs, data)

      calculatePopulationMetrics(docs, data)

      data["findbug_cache_docs"] = docs

   }



   private void calculatePopulationMetrics( ArrayList docs, Map data ) {

      def classHash = [:]

      def bug_population = []


      docs.each{ doc ->

         doc.BugInstance.each{ bug ->

            def key = bug.Class.@classname

            classHash[key] = (classHash.containsKey(key) ? classHash[key]++ : 1)

         }
      }


      classHash.keySet().each { key ->

         bug_population << classHash[key]
      }

      data["bug_population"] = bug_population
      data["findbug_bug_population"] = bug_population


   }


   private void calculateTopLevelMetric( ArrayList docs, Map data ) {


      int total_bugs = 0

      docs.each{ doc ->
         int bugs = Integer.parseInt(doc.FindBugsSummary.@total_bugs.text())

         total_bugs += bugs


      }


      data["bugs"] = total_bugs
      data["findbug_bugs"] = total_bugs

   }


//   private getFindbugsDocuments( MetricConfig metric, Map data) {
//
//      if (data["findbug_cache_docs"] != null) {
//         return data["findbug_cache_docs"]
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
//         def pattern = ~"findbugs.xml"
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