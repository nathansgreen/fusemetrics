package com.stelligent.fusemetrics.datastore
/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 27, 2008
 * Time: 11:50:30 AM
 * To change this template use File | Settings | File Templates.
 */
interface DataStore {

   public void add( Map m)

   public void save()

   def retrieve( String metricName, boolean padWithNulls )

   def retrieve( String metricName )

}