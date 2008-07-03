package com.stelligent.fusemetrics.engine.presentation

import com.stelligent.fusemetrics.engine.presentation.SimplePresentationEngine
import com.stelligent.fusemetrics.Config

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 2, 2008
 * Time: 11:59:52 AM
 * To change this template use File | Settings | File Templates.
 */
class SimplePresentationEngineTest extends GroovyTestCase {

   public void testStringTemplateConversion() {


      SimplePresentationEngine spe = new SimplePresentationEngine()

      def data = [ loc: 100, coverage: "5.0%"]

      def templateString = '<b>LOC: $loc</b><p /><b>COV: $coverage</b>'

      def out = spe.processTemplate(templateString, data)

      def expected = '<b>LOC: 100</b><p /><b>COV: 5.0%</b>'

      assertEquals(expected, out)

   }


   public void testFileTemplateConversion() {

      // cleanup

      File f = new File('etc/presentation/test1.html')
      if (f.exists()) {
         f.delete()
      }


      Config c = new Config([ html: 'etc/presentation/test1.html', template: 'etc/presentation/test1.tmpl'])

      SimplePresentationEngine spe = new SimplePresentationEngine()

      def data = [ loc: 100, coverage: "5.0%"]

      spe.run(c, data)


      f = new File('etc/presentation/test1.html')

      def expected = "<p>LOC: 100</p><p>Coverage: 5.0%</p>"


      assertTrue(f.exists())

      println f.text

      assertEquals(expected, f.text)





   }



   public void testFileSizeConsistency() {

      // cleanup

      File f = new File('etc/presentation/test1.html')
      if (f.exists()) {
         f.delete()
      }


      Config c = new Config([ html: 'etc/presentation/test1.html', template: 'etc/presentation/test1.tmpl'])

      SimplePresentationEngine spe = new SimplePresentationEngine()

      def data = [ loc: 100, coverage: "5.0%"]

      spe.run(c, data)

      f = new File('etc/presentation/test1.html')

      def expected = "<p>LOC: 100</p><p>Coverage: 5.0%</p>"


      assertTrue(f.exists())
      assertEquals(expected, f.text)

//      assertEquals(36, f.size())

      // now, run it again

      spe.run(c, data)
      f = new File('etc/presentation/test1.html')
      assertTrue(f.exists())
      assertEquals(expected, f.text)
//      assertEquals(36, f.size())






   }


}