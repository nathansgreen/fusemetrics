package com.stelligent.fusemetrics.engine.rendering

import com.stelligent.fusemetrics.Config
import com.stelligent.fusemetrics.datastore.DataStore
import com.stelligent.fusemetrics.MetricConfig
import com.stelligent.fusemetrics.plugin.histogram.HistogramPlugin
import com.stelligent.fusemetrics.graph.GraphBuilder
import com.stelligent.fusemetrics.datastore.SimpleDataStore

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 4:10:01 PM
 * To change this template use File | Settings | File Templates.
 */
class SimpleRenderingEngine implements RenderingEngine {

   public void run(Config config, Map data) {

      DataStore ds = SimpleDataStore.load(config.getPersistDirectory())



      // we need to copy these or we end up with a concurrent exception, as we add the image_path for each
      // graph back into the datasphere
      def keys = []

      data.keySet().each{ key -> keys << key }     // probably an easier way to do this, but clone() doesn't


      keys.each { String key ->

         def x = data[key]

         MetricConfig mc = config.getMetricConfig(key)


         if (mc != null) {
            if (x instanceof ArrayList ) {

               generateHistogram(key, data, x, mc, config)

            } else if (x instanceof Number || x instanceof Double || x instanceof Float ) {

               generateSparkline(key, data, ds, config.getOutputDirectory() )

            }
         }

      }


   }

   void generateHistogram(String key, Map data, ArrayList populationData, MetricConfig mc, Config config) {

      String directory = config.getOutputDirectory()

      HistogramPlugin hp =  config.getPluginRegistry().getHistogramPlugin(mc.getHistogramPluginName())

      if (hp != null) {

         hp.accept(populationData)

         def filename = GraphBuilder.buildHistogram(key, hp, directory)

         data["${key}_img_path"] = filename
      }
      
   }

   void generateSparkline(String key, Map data, DataStore ds, String directory) {

      def metricData = ds.retrieve(key)


      println "DEBUG: generateSparklines: metricData for ${key} -> ${metricData}"

      def filename = GraphBuilder.buildSparkline(key, metricData, directory)

      data["${key}_img_path"] = filename

   }
}