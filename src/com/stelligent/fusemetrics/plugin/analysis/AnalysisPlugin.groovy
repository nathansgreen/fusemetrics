package com.stelligent.fusemetrics.plugin.analysis

import com.stelligent.fusemetrics.Config
import com.stelligent.fusemetrics.MetricConfig

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 2:38:06 PM
 * To change this template use File | Settings | File Templates.
 */
interface AnalysisPlugin {

   void exec( MetricConfig config, Map data )

}