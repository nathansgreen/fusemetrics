package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.util.FileFinder

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 11:35:58 AM
 * To change this template use File | Settings | File Templates.
 */
class JUnitAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {

   public void exec(MetricConfig config, Map data) {

      def docs = findDocs(config, "TEST-.+.\\.xml", data, data["junit_cache_docs"])

      //getTestDocuments(config, data)

      int tests_run = 0
      int tests_failed = 0

      def failed_population = []
      def count_population = []


      docs.each{ doc ->

         int tests = Integer.parseInt(doc.@tests.text())
         int failures = Integer.parseInt(doc.@failures.text())

         tests_run += tests
         tests_failed += failures

         failed_population << failures
         count_population << tests


      }


      data["junit_cache_docs"] = docs

      data["test_count"] = tests_run
      data["test_failures"] = tests_failed

      data["test_count_population"] = count_population
      data["test_failure_population"] = failed_population


   }


//   private getTestDocuments( MetricConfig metric, Map data) {
//
//      if (data["junit_cache_docs"] != null) {
//         return data["junit_cache_docs"]
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
//         def pattern = ~"TEST-.+\\.xml"
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