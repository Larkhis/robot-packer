package com.xspeedit.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xspeedit.exception.RobotPackerInvalidInputException;

/**
 * RobotPacker helper class
 * @author lokmanearkhis
 */
public final class RobotPackerHelper {

	/**
	 * Regex pattern for articles
	 */
	private static final String ARTICLES_PATTERN = "^[1-9]*$";
	
	/**
	 * Private constructor to prevent instantiation
	 */
	private RobotPackerHelper() {
		
	}
	
	/**
	 * Utility method that transform a chain of articles under String into a list of Integers
	 * @param chainArticles - String
	 * @return list of Integer - List<Integer>
	 * @throws RobotPackerInvalidInputException in case of non matching the {@link #ARTICLES_PATTERN} regex pattern
	 */
	public static List<Integer> toListOfInteger(String chainArticles) throws RobotPackerInvalidInputException {
		
		Pattern pattern = Pattern.compile(ARTICLES_PATTERN);
		Matcher matcher = pattern.matcher(chainArticles);
		List<Integer> result = new ArrayList<>();
		
		if(matcher.matches()) {
			String[] articles = chainArticles.split("");
			for(String article : articles) {
				result.add(Integer.valueOf(article));
			}
		} else {
			throw new RobotPackerInvalidInputException(chainArticles + " MUST BE A LIST OF INTEGERS BETWEEN 1 AND 9 AS 1234769");
		}
		
		return result;
	}
}
