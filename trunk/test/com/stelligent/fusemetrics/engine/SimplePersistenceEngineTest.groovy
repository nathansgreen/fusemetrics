package com.stelligent.fusemetrics.engine

import com.stelligent.fusemetrics.datastore.SimpleDataStore

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 12:25:37 PM
 * To change this template use File | Settings | File Templates.
 */
class SimplePersistenceEngineTest extends GroovyTestCase {

   
   public void testLoadEmptyDatastore() {

      SimpleDataStore sds = SimpleDataStore.load("etc/persist/", "_nonexistant.file")

      assertNotNull(sds)


      def val = sds.retrieve("loc")

      assertNotNull(val)
      assertEquals(0, val.size)


      sds.add([ loc: 100 ])


      val = sds.retrieve("loc")

      assertNotNull(val)
      assertEquals(1, val.size)

      println sds.toXml()

   }


   public void testLoadDatastoreFromFile() {

      SimpleDataStore sds = SimpleDataStore.load("etc/persist" )  // default filename fusemetrics_datastore.xml

      assertNotNull(sds)

      def val = sds.retrieve("loc")

      assertEquals(10, val.size())

      sds.add([ loc: 200])

      assertEquals(11, sds.retrieve("loc").size)
   }


   public void testLoadDatastoreFromAlreadyGeneratedFile() {

      SimpleDataStore sds = SimpleDataStore.load("etc/persist2" )  // default filename fusemetrics_datastore.xml

      assertNotNull(sds)

      def val = sds.retrieve("loc")

      assertEquals(41, val.size())

      sds.add([ loc: 200])

      assertEquals(42, sds.retrieve("loc").size)
   }


   public void testDataStoreIgnoresArrays() {


      SimpleDataStore sds = SimpleDataStore.load("etc/persist" )  // default filename fusemetrics_datastore.xml

      sds.add([ loc_population: [1,1,1,1,1,1,1,1,1,1,1,1,11,1,1,1]])

      assertEquals(0, sds.retrieve("loc_population").size)

   }


   public void testFileWrite() {

      File tmp = new File("etc/persist/dummy.xml")

      if (tmp.exists()) {
         tmp.delete();
      }

      SimpleDataStore sds = SimpleDataStore.load("etc/persist", "dummy.xml")

      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])
      sds.add([ loc: 100])

      sds.save()


      File tmp2 = new File("etc/persist/dummy.xml")

      assertTrue(tmp2.exists())


   }

}