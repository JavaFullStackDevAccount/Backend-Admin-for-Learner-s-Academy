package com.learnersAcademy.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryResultToArrayList {

	public QueryResultToArrayList() {
		super();
	}

	public static ArrayList<String> extract(List<String> resultOfQuery) {

		try {
			ArrayList<String> finalDatArrayList = new ArrayList<String>();
			for (Iterator itrIterator = resultOfQuery.iterator(); itrIterator.hasNext();) {
				Object object[] = (Object[]) itrIterator.next();
				for (Object i : object) {
					if(i == null)
						finalDatArrayList.add("Na");
					else
						finalDatArrayList.add(i.toString());
				}
			}

			return finalDatArrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

}

/**
 * Class to extract the data from the result of the query and convert to
 * arraylist
 */
