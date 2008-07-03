package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.util.FileFinder

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 2, 2008
 * Time: 10:31:39 AM
 * To change this template use File | Settings | File Templates.
 */
abstract class AnalysisBase {


   ArrayList findDocs( MetricConfig metric, String filePattern, Map data, cachedData = null, Closure docBuilder = null ) {

      if (cachedData != null) {
         data["info"] += "Reusing cached data for ${filePattern}\n"

         return cachedData
         
      }

      def ff = new FileFinder()

      def pattern = ~filePattern

      if (metric.getFileRegexp() != null) {
         pattern = ~metric.getFileRegexp()
      }

      def files = ff.findFiles(metric.getFilepath(), pattern)


      data["info"] += "Found ${files.size} metrics files for ${metric.name}\n"


      def docs = []

      files.each{ file ->

         def doc = null

         if (docBuilder != null) {
            doc = docBuilder.call(file)
         } else {
            doc = new XmlSlurper().parse(file)   //new XmlParser().parse(file)
         }

         if (doc != null) {
            docs << doc
         }
      }

      return docs



   }



}