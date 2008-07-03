package com.stelligent.fusemetrics.datastore
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 11:51:06 AM
 * To change this template use File | Settings | File Templates.
 */
class SimpleDataStore implements DataStore {

   def _doc = null

   def directory = null
   def filename = null

   def records = []
   def unsaved_records = []


   public static DataStore load( String directory, String filename = "fusemetrics_datastore.xml" ) {

      def sds = new SimpleDataStore(directory, filename)

      return sds

   }



   public SimpleDataStore( String directory, String filename) {


      def _doc = null

      File f = new File("${directory}/${filename}")

      if (f.exists()) {

         _doc = new XmlSlurper().parse(f)
      }

      this.directory = directory
      this.filename = filename


      if (_doc != null) {
         _doc.record.each{ el ->

            def map = createTypedMap(el.attributes())

            records << map

         }
      }
   }




   private Map createTypedMap( Map map) {
      def typedMap = [:]

      map.keySet().each { key ->

         def obj = map[key]

         if (obj instanceof String) {

            String x = (String)obj

            if (x.isNumber()) {
               if (x.isDouble()) {
                  typedMap[key] = x.toDouble().doubleValue()
               } else if (x.isLong()) {
                  typedMap[key] = x.toLong().longValue()
               } else {
                  println "WARN: SimpleDataStore unsupported type conversion for x: ${x}"
               }
            }

         } else {

            // println "obj isa: ${obj.class.name}"

            if (obj instanceof Double || obj instanceof Long || obj instanceof Integer || obj instanceof Float) {
                typedMap[key] = obj

            }  else {
                // println "WARN: SimpleDataStore unsupported type conversion for obj: ${obj}, ${obj.class.name}"

            }

         }
      }

      return typedMap
   }


   public void add(Map map) {

      records << createTypedMap(map)

   }

   public void save() {

      def xml = toXml( records )

      File dataFile = new File("${directory}/${filename}")

      println "DEBUG: Writing persistence records to: ${dataFile}"

      FileWriter fwriter = new FileWriter( dataFile )

      fwriter.write(xml)

      fwriter.close()

   }



   public retrieve(String metricName, boolean padWithNulls = false) {

      def values = []

      records.each{ Map rec ->

         if (rec.containsKey(metricName)) {
            values << rec[metricName]
         } else if (padWithNulls) {
            values << null
         }

      }

      return values

   }


   public String toXml() {
      return toXml(records)
   }
   

   private String toXml( arr ) {

      def writer = new StringWriter()

      writer.write("<records>\n")


      writeRecords( writer, arr )

      writer.write("</records>\n")


      return writer.toString()

   }



   private def writeRecords(writer, arr) {
      arr.each {Map map ->

         writer.write("<record ")

         map.keySet().each {key ->
            writer.write(" ${key}=");

            def x = map[key]

           // println "class of x: ${x.class.name}"

            if (x instanceof Number || x instanceof Double ||  x instanceof Float || x instanceof String) {
               writer.write("\"${x}\"")

            } else {

               // don't know what to do with arrays yet, ignoring for the moment.

            }

         }

         writer.write(" />\n")

      }
   }


   public retrieve(String metricName) {
      return retrieve(metricName, false)
   }
}