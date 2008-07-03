package com.stelligent.fusemetrics.engine.persistence

import com.stelligent.fusemetrics.Config
import com.stelligent.fusemetrics.datastore.DataStore
import com.stelligent.fusemetrics.datastore.SimpleDataStore

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 4:09:04 PM
 * To change this template use File | Settings | File Templates.
 */
class SimplePersistenceEngine implements PersistenceEngine {

   public void run(Config config, Map data) {


      // open up the database
      DataStore ds = SimpleDataStore.load(config.getPersistDirectory())

      // add some records to the end
      ds.add(data)

      // save it
      ds.save()


   }

}