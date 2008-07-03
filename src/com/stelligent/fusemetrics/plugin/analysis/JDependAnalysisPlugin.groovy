package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.util.FileFinder

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 7:02:20 PM
 * To change this template use File | Settings | File Templates.
 */
class JDependAnalysisPlugin extends AnalysisBase implements AnalysisPlugin {

   public void exec(MetricConfig config, Map data) {


      def docs = findDocs(config, "jdepend-report.xml", data, data["jdepend_cache_docs"])

      //getJDependDocuments(config, data)

      def package_aff_array = []
      def package_eff_array = []

      int max_afferent = 0
      int max_efferent = 0

      docs.each{ doc ->


         doc.Packages.Package.each{ pckg ->

            int aff = 0
            int eff = 0

            aff = getValue(pckg.Stats.Ca.text())
            eff = getValue(pckg.Stats.Ce.text())


            if (aff > -1) {

               if (aff > max_afferent) {
                  max_afferent = aff
               }
               package_aff_array << aff
            }


            if (eff > -1) {

               if (eff > max_efferent) {
                  max_efferent = eff
               }
               package_eff_array << eff
            }
         }

      }


      data["coupling_afferent_max"] = max_afferent
      data["coupling_efferent_max"] = max_efferent

      data["coupling_afferent_population"] = package_aff_array
      data["coupling_efferent_population"] = package_eff_array


      data["jdepend_cache_docs"] = docs

   }


//   private getJDependDocuments( MetricConfig metric, Map data) {
//
//      if (data["jdepend_cache_docs"] != null) {
//         return data["jdepend_cache_docs"]
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
//         def pattern = ~"jdepend-report.xml"
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



   int getValue( val ) {

      if (val == null) {
         return -1
      }

      if (val == "") {
         return -1
      }

      return Integer.parseInt(val)
   }




}