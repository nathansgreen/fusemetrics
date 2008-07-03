package com.stelligent.fusemetrics

import com.stelligent.fusemetrics.plugin.analysis.AnalysisPlugin
import com.stelligent.fusemetrics.plugin.analysis.AnalysisPlugin
import com.stelligent.fusemetrics.plugin.histogram.HistogramPlugin

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 2:33:55 PM
 * To change this template use File | Settings | File Templates.
 */
class PluginRegistry {

   def analysisRegistry = [:]
   def histogramRegistry = [:]


   def PluginRegistry() {

      setDefaults()

   }


   private setDefaults() {

      addAnalysisPlugin("javancss", "com.stelligent.fusemetrics.plugin.analysis.JavaNcssAnalysisPlugin")
      addAnalysisPlugin("pmd", "com.stelligent.fusemetrics.plugin.analysis.PmdAnalysisPlugin")
      addAnalysisPlugin("findbugs", "com.stelligent.fusemetrics.plugin.analysis.FindbugsAnalysisPlugin")
      addAnalysisPlugin("jdepend", "com.stelligent.fusemetrics.plugin.analysis.JDependAnalysisPlugin")
      addAnalysisPlugin("junit", "com.stelligent.fusemetrics.plugin.analysis.JUnitAnalysisPlugin")
      addAnalysisPlugin("cpd", "com.stelligent.fusemetrics.plugin.analysis.CpdAnalysisPlugin")
      addAnalysisPlugin("simian", "com.stelligent.fusemetrics.plugin.analysis.SimianAnalysisPlugin")
      addAnalysisPlugin("cobertura", "com.stelligent.fusemetrics.plugin.analysis.CoberturaAnalysisPlugin")
      addAnalysisPlugin("clover", "com.stelligent.fusemetrics.plugin.analysis.CloverAnalysisPlugin")


      addHistogramPlugin("javancss", "com.stelligent.fusemetrics.plugin.histogram.JavaNcssHistogramPlugin")
      addHistogramPlugin("clover", "com.stelligent.fusemetrics.plugin.histogram.CloverHistogramPlugin")
      addHistogramPlugin("cobertura", "com.stelligent.fusemetrics.plugin.histogram.CoberturaHistogramPlugin")
      addHistogramPlugin("findbugs", "com.stelligent.fusemetrics.plugin.histogram.FindbugsHistogramPlugin")
      addHistogramPlugin("jdpened", "com.stelligent.fusemetrics.plugin.histogram.JDependHistogramPlugin")
      addHistogramPlugin("junit", "com.stelligent.fusemetrics.plugin.histogram.JUnitHistogramPlugin")
      addHistogramPlugin("pmd", "com.stelligent.fusemetrics.plugin.histogram.PmdHistogramPlugin")
      addHistogramPlugin("cpd", "com.stelligent.fusemetrics.plugin.histogram.CpdHistogramPlugin")

   }


   public addHistogramPlugin(name, classname) {
      addPlugin(histogramRegistry, name, classname)
   }

   public addAnalysisPlugin(name, className) {

      addPlugin( analysisRegistry, name, className)
   }


   private addPlugin( Map registry, String name, String classname) {
      registry[name] = classname
   }


   public AnalysisPlugin getAnalysisPlugin( name ) {

      return getPlugin(analysisRegistry, name)

   }

   public HistogramPlugin getHistogramPlugin( name ) {
      return getPlugin(histogramRegistry, name)
   }

   private getPlugin( Map registry, String name ) {
      def str = registry[name]

      if (str == null) {
         return null
      }

      def plugin = Class.forName(str).newInstance()

      return plugin

   }


}