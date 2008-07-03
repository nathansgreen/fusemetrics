package com.stelligent.fusemetrics
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 12:13:05 PM
 * To change this template use File | Settings | File Templates.
 */
class Config {


   def projectName
   def searchDirectory
   def persistDirectory
   def outputDirectory
   def presentationFilename
   def presentationTemplate

   def metricConfigHash = [:]

   def pluginRegistry = null;



   public Config() {

   }


   public Config( String dir ) {
      searchDirectory = dir;
   }


   public Config( Map hash ) {

      projectName = hash["project"]
      searchDirectory = hash["search"]
      persistDirectory = hash["persist"]
      outputDirectory = hash["output"]
      presentationFilename = hash["html"]
      presentationTemplate = hash["template"]

   }


   String getSearchDirectory() {

      if (searchDirectory == null){
         searchDirectory = new File().absolutePath
      }

      return searchDirectory
   }


   String getPersistDirectory() {
      if (persistDirectory == null) {
         return getSearchDirectory()
      }

      return persistDirectory
   }

   String getOutputDirectory() {
      if (outputDirectory == null) {
         return getSearchDirectory()
      }
      return outputDirectory
   }

   String getHtml() {
      if (presentationFilename == null ) {
         return "dashboard.html"
      }

      return presentationFilename
   }

   String getTemplate() {
      if (presentationTemplate == null) {
         return "dashboard.xslt"
      }
      return presentationTemplate
   }


   void addMetricConfig( mName, MetricConfig mConfig ) {

      mConfig.setParent(this)

      metricConfigHash[mName] = mConfig

   }


   MetricConfig getMetricConfig( mName ) {
      return metricConfigHash[mName]
   }


   PluginRegistry getRegistry() {
      return pluginRegistry
   }

   def setRegistry( PluginRegistry pr ) {
      pluginRegistry = pr
   }


   def getAllMetricConfigs() {
      return metricConfigHash.values()
   }



   public static Config load( filename ) {


      def doc = new XmlParser().parse(new File(filename))


      Config c = new Config( doc.attributes() )

      doc.metric.each { m ->

         MetricConfig mc = new MetricConfig(m.attributes())

         if (mc.isValid()) {
            c.addMetricConfig( mc.name, mc )
         } else {
            return null
         }

      }

      return c




   }


}