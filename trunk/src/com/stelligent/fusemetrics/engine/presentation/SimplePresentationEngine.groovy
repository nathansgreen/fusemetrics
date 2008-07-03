package com.stelligent.fusemetrics.engine.presentation

import com.stelligent.fusemetrics.Config
import groovy.text.SimpleTemplateEngine

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 4:09:43 PM
 * To change this template use File | Settings | File Templates.
 */
class SimplePresentationEngine implements PresentationEngine {

   public void run(Config config, Map data) {

      def templateFile = config.getTemplate()

      def outputFile = config.getHtml()



      File tf = new File(templateFile)

      if (tf.exists()) {

         def outputString = processTemplate(tf.text, data)


         FileWriter fw = new FileWriter(outputFile)

         fw << outputString

         fw.close()

      } else {

         data["error"] += "Unable to open template file: ${templateFile} for reading\n"
      }



   }

   String processTemplate(String templateString, Map data) {

      def templateEngine = new SimpleTemplateEngine()

      
      def template = templateEngine.createTemplate(templateString).make(data)

      return template.toString()


   }
}