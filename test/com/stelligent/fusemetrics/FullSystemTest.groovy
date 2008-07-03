package com.stelligent.fusemetrics
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 9:42:19 PM
 * To change this template use File | Settings | File Templates.
 */
class FullSystemTest extends GroovyTestCase {

   public void testFuseMetrics_With_BigSample() {

      FuseMetrics fm = new FuseMetrics()

      fm.setConfigFilePath("etc/big_sample/fusemetrics_config.xml")

      fm.run()


      def data = fm.getWorkingData()

      fm.debug_dump()


      assertTrue( data["project_name"] == "BigSample")

      assertTrue( data.containsKey("duplication"))
      assertTrue( data.containsKey("duplication_img_path"))
      
      assertTrue( data.containsKey("loc_img_path"))
      assertTrue( data.containsKey("loc_population_img_path"))
      assertTrue( data.containsKey("bugs_img_path"))
      assertTrue( data.containsKey("bug_population_img_path"))

      assertTrue( data["loc_img_path"].endsWith(".png"))


      File tmp = new File("etc/big_sample/output/dashboard.html")

      assertTrue(tmp.exists())
            

   }

}