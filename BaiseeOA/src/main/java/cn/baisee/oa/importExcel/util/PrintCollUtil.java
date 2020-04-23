package cn.baisee.oa.importExcel.util;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;

public class PrintCollUtil {

	public static void printList(Collection<?> list) {
		if (CollectionUtils.isEmpty(list))
			return;
		System.out
				.println("\n--------------------------------------------------------------------------------------------------------------------");
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object obj = (Object) iterator.next();
			System.out.println(obj);
		}
		System.out
				.println("\n--------------------------------------------------------------------------------------------------------------------");
	}
}
