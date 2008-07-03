package com.stelligent.fusemetrics

import com.stelligent.fusemetrics.engine.analysis.SimpleAnalysisEngine
import com.stelligent.fusemetrics.engine.persistence.SimplePersistenceEngine
import com.stelligent.fusemetrics.engine.rendering.SimpleRenderingEngine
import com.stelligent.fusemetrics.engine.presentation.SimplePresentationEngine

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 12:00:38 PM
 * To change this template use File | Settings | File Templates.
 */
class FuseMetrics {


   def configFilePath = null

   def workingData = [:]


   def analysisPhaseEngine = null
   def persistencePhaseEngine = null
   def renderingPhaseEngine = null
   def presentationPhaseEngine = null


   def config  = null


   static main( args) {

      FuseMetrics fm = new FuseMetrics()

      processCommandLineArguments(fm, args)


      fm.run()


      fm.debug_dump()

    }



   private static processCommandLineArguments( FuseMetrics fm, String[] args ) {

      def configFile = "fusemetrics_config.xml"

      for (def i = 0; i < args.length; i++ ) {

          if (args[i] == "-c") {
              i++;
              configFile = args[i]
          }

          if (args[i] == "-h") {
              help()
              System.exit(0)
          }

      }


      fm.setConfigurationFile(configFile)


   }



   private initialize() {


      config = Config.load(configFilePath)

      if (config == null) {
         println "Unable to find ${configFilePath}"
         help()
         System.exit(1)
      }


      config.setPluginRegistry( new PluginRegistry() )

      defineEngines( config )

      workingData["project_name"] = config.getProjectName()



   }

    private help() {

        println """
            -c <config_file>   - a file that contains the fusemetrics configuration

        """

    }



   void defineEngines(Config config) {

      analysisPhaseEngine = new SimpleAnalysisEngine()
      persistencePhaseEngine = new SimplePersistenceEngine()
      renderingPhaseEngine = new SimpleRenderingEngine()
      presentationPhaseEngine = new SimplePresentationEngine()

   }

   void setConfigurationFile(String s) {

      configFilePath = s

   }

   void run() {
      initialize()

      exec()

   }

   void exec() {


      analysisPhaseEngine.run( config, workingData )

      persistencePhaseEngine.run( config, workingData )

      renderingPhaseEngine.run( config, workingData )

      presentationPhaseEngine.run( config, workingData )

   }

   void debug_dump( show_cached = false) {

      workingData.keySet().each { key ->

         if (!key.contains("cache_docs") || show_cached ) {
            println "DEBUG: ${key} -> ${workingData[key]}."
         }
      }
      
   }


   public Map getWorkingData() {
      return workingData
   }

}