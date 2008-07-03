package com.stelligent.fusemetrics.engine

import com.stelligent.fusemetrics.Config

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 1:47:48 PM
 * To change this template use File | Settings | File Templates.
 */
interface Engine {

   void run( Config config, Map data )

}