package com.stelligent.fusemetrics
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 12:23:43 PM
 * To change this template use File | Settings | File Templates.
 */
class MetricConfig {

    def name
    def analysisPluginName
    def histogramPluginName
    def filepath
    def fileRegexp
    def hashRanges
    def hashLabels

    def parent


    public MetricConfig( configHash ) {

       name =       configHash["name"]
       analysisPluginName =  configHash["analysis"]
       histogramPluginName = configHash["histogram"]


       filepath = configHash["filepath"]
       fileRegexp = configHash["regexp"]
       hashRanges = parseRanges(configHash["ranges"])
       hashLabels = parseLabels(configHash["labels"])


    }


    public void setParent( Config c ) {
        parent = c
    }


    String getFilepath() {
        if (filepath == null) {
           filepath = parent.getSearchDirectory()
        }
        return filepath
    }

    String getName() {
        return name
    }

//   String getAnalysisPluginName() {
//      return analysisPluginName
//   }
//
//   String getHistogramPlugin() {
//      return histogramPluginName
//   }

    String getFileRegexp() {
        return fileRegexp
    }

    def getRanges() {
        return hashRanges
    }

    def getLabels() {
        return hashLabels
    }


    def parseRanges( String ranges ) {
       return null
    }

   def parseLabels( String labels ) {
      return null
   }


   def isValid() {

      if (name == null || ( analysisPluginName == null && histogramPluginName == null)) {
         return false
      }

      return true

   }


}