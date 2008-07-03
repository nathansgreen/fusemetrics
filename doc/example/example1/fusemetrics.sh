#!/bin/sh

LIBDIR=../../../lib
JFREE_LIB=../../../lib/jfreechart-1.0.9
DIST=../../../dist


JAVA=`which java`

#JAVA=/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/bin/java


#
#  usage - com.stelligent.fusemetrics.FuseMetrics
#
#     -c  <config_file>  - default is fusemetrics_config.xml
#
#     -d                 - debug mode (prints lots of debugging info)
#
#
$JAVA -classpath "$DIST/fusemetrics.jar:$LIBDIR/xom-1.1.jar:$JFREE_LIB/jcommon-1.0.12.jar:$JFREE_LIB/jfreechart-1.0.9.jar:$LIBDIR/groovy-all-1.5.4.jar" com.stelligent.fusemetrics.FuseMetrics

# note - make sure the version of java that you're calling can handle the graphics generation requirements of JFreeChart
# .... I'm looking at you, SoyLatte!
#