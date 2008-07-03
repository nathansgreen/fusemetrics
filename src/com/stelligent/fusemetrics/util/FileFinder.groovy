package com.stelligent.fusemetrics.util

import java.util.regex.Pattern

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jun 26, 2008
 * Time: 2:44:36 PM
 * To change this template use File | Settings | File Templates.
 */
class FileFinder {


   def findFiles( fileDir, Pattern regexp ) {

      def files = []

      if (isDir(fileDir)) {

         new File(fileDir).eachFileRecurse{ file ->
					if (file.isFile() && file.name ==~ regexp) {
						files << file.path
					}
				}

      } else {

         files << fileDir

      }


      return files
   }

   def isDir( fileDir ) {

      def f = new File(fileDir)

      return f.isDirectory()

   }

}