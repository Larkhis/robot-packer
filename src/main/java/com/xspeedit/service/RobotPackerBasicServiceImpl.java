package com.xspeedit.service;

import java.util.List;

import org.apache.log4j.Logger;

import static com.xspeedit.constant.RobotPackerConstant.*;
import com.xspeedit.exception.RobotPackerInvalidInputException;
import com.xspeedit.helper.RobotPackerHelper;

/**
 * Implementation of RobotPackerService based on a basic algorithm of bin
 * packing
 * 
 * <p>
 * This algorithm consist of :
 * </p>
 * <ul>
 * <li>Adding object by abject on the order of the list and by satisfying the
 * capacity constraint</li>
 * </ul>
 * 
 * <p>
 * Example
 * </p>
 * With this list of articles 345628<br/>
 * <br/>
 * First step :<br/>
 * Articles : 3|4|5|6|2|8<br/>
 * Cartons : 1|1|2|3|3|4<br/>
 * <br/>
 * So we get this result : 34/5/62/8<br/>
 * <br/>
 * 
 * @author lokmanearkhis
 */
public class RobotPackerBasicServiceImpl implements RobotPackerService {

	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = Logger.getLogger(RobotPackerBasicServiceImpl.class);

	@Override
	public String pack(String articlesChain) throws RobotPackerInvalidInputException {

		LOGGER.debug("Entering RobotPackerBasicServiceImpl.pack(String articlesChain) method");
		LOGGER.debug("Articles chain to pack : " + articlesChain);

		StringBuilder packedArticles = new StringBuilder(); // the result
		List<Integer> articlesList = RobotPackerHelper.toListOfInteger(articlesChain); // the list of articles to pack

		int usedCapacity = 0;
		for (Integer article : articlesList) {
			usedCapacity += article;
			if (usedCapacity <= CARTON_CAPACITY) {
				packedArticles.append(article);
			} else {
				packedArticles.append(CARTON_SEPARATOR);
				packedArticles.append(article);
				usedCapacity = article;
			}
		}

		LOGGER.debug("Packed articles : " + packedArticles.toString());
		LOGGER.debug("Number of used cartons : " + packedArticles.toString().split(CARTON_SEPARATOR).length);
		LOGGER.debug("Ending RobotPackerBasicServiceImpl.pack(String articlesChain) method");

		return packedArticles.toString();
	}

}
