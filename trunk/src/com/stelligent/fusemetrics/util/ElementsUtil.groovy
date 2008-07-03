package com.stelligent.fusemetrics.util

import nu.xom.Elements

/**
 * Created by IntelliJ IDEA.
 * User: johnbr
 * Date: Jul 1, 2008
 * Time: 10:05:11 PM
 * To change this template use File | Settings | File Templates.
 */
class ElementsUtil {

   static ArrayList toArray(Elements elements) {

      def list = []

      for (int i = 0; i < elements.size(); i++) {
         list << elements.get(i)
      }

      return list

   }
}