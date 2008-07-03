package com.stelligent.fusemetrics.engine.analysis

import com.stelligent.fusemetrics.Config
import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.plugin.analysis.AnalysisPlugin

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 4:00:25 PM
 * To change this template use File | Settings | File Templates.
 */
class SimpleAnalysisEngine implements AnalysisEngine{

   public void run(Config config, Map data) {

      def registry = config.getPluginRegistry()
      def metrics = config.getAllMetricConfigs()

      metrics.each{ MetricConfig mc ->

         AnalysisPlugin plugin = registry.getAnalysisPlugin(mc.getAnalysisPluginName())

         if (plugin == null) {
//            println "WARN: Unknown plugin: ${mc.getAnalysisPlugin()}"

         } else {
            plugin.exec(mc, data)
         }
      }

   }

}