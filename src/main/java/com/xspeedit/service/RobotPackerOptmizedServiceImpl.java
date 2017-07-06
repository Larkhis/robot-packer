package com.xspeedit.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.xspeedit.exception.RobotPackerInvalidInputException;
import com.xspeedit.helper.RobotPackerHelper;
import static com.xspeedit.constant.RobotPackerConstant.*;

/**
 * Implementation of RobotPackerService based on the bin packing BFD Best Fit Decreasing algorithm
 * 
 * <p>This algorithm consist of :</p>
 * <ul>
 * 	<li>sorting the articles by decreasing order</li>
 * 	<li>select object by abject on the order of the sort and by adding the heaviest article that fits to the carton</li>
 * </ul>
 * 
 * <p>Example</p>
 * With this list of articles 345628<br/><br/>
 * First step :<br/>
 * Articles : 8|6|5|4|3|2<br/><br/>
 * Second step :<br/>
 * Articles : 8|6|5|4|3|2<br/>
 * Cartons :  1|2|3|2|3|1<br/><br/>
 * So we get this result : 82/64/53<br/><br/>
 * 
 * @author lokmanearkhis
 */
public class RobotPackerOptmizedServiceImpl implements RobotPackerService {

	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = Logger.getLogger(RobotPackerOptmizedServiceImpl.class);

	@Override
	public String pack(String articlesChain) throws RobotPackerInvalidInputException {

		LOGGER.debug("Entering RobotPackerOptmizedServiceImpl.pack(String articlesChain) method");
		LOGGER.debug("Articles chain to pack : " + articlesChain);

		StringBuilder packedArticles = new StringBuilder(); // the result
		
		CopyOnWriteArrayList<Integer> articlesList = new CopyOnWriteArrayList<>(RobotPackerHelper.toListOfInteger(articlesChain));
		Collections.sort(articlesList, Collections.reverseOrder()); // Sorting articlesList by decreasing order
		LOGGER.debug("articlesList : " + articlesList.toString());

		int usedCapacity = 0;
		List<Integer> nonPackedArticlesList = new ArrayList<>(articlesList); // list of non packed articles used get an article that best fits to the current carton
		LOGGER.debug("articlesSubList : " + nonPackedArticlesList.toString());

		// while the articles are not all packed
		while (!articlesList.isEmpty()) {
			Integer currentArticle = articlesList.get(0);

			LOGGER.debug("currentArticle : " + currentArticle);

			// If the current article fit to the carton, we ad it
			if (usedCapacity + currentArticle <= CARTON_CAPACITY) {
				packedArticles.append(currentArticle);
				articlesList.remove(currentArticle);
				nonPackedArticlesList.remove(nonPackedArticlesList.indexOf(currentArticle));
				usedCapacity += currentArticle;
			} 
			// Otherwise
			else {
				// We look for the heaviest article that fits to the carton
				Integer foundedArticle = getAndRemoveArticleFromListThatBestFits(nonPackedArticlesList, CARTON_CAPACITY - usedCapacity);
				// If we find it, we add it
				if (foundedArticle != null) {
					packedArticles.append(foundedArticle);
					articlesList.remove(foundedArticle);
					usedCapacity += foundedArticle;
				} 
				// If we don't find it, we add the next article on the next carton
				else {
					packedArticles.append(CARTON_SEPARATOR);
					packedArticles.append(currentArticle);
					articlesList.remove(currentArticle);
					nonPackedArticlesList.remove(nonPackedArticlesList.indexOf(currentArticle));
					usedCapacity = currentArticle;
				}

			}
			
			LOGGER.debug("articlesList : " + articlesList.toString());
			LOGGER.debug("articlesSubList : " + nonPackedArticlesList.toString());
			LOGGER.debug("packedArticles : " + packedArticles);
		}

		LOGGER.debug("Packed articles : " + packedArticles.toString());
		LOGGER.debug("Number of used cartons : " + packedArticles.toString().split(CARTON_SEPARATOR).length);
		LOGGER.debug("Ending RobotPackerOptmizedServiceImpl.pack(String articlesChain) method");
		
		return packedArticles.toString();
	}

	/**
	 * Gets and removes the non packed article that best fit to the carton
	 * @param nonPackedArticlesList - list of non packed articles used get an article that best fits to the current carton
	 * @param remainedCapacity - remained capacity of the current carton
	 * @return return the article that best fit to the carton if founded and remove it from the list otherwise null
	 */
	private Integer getAndRemoveArticleFromListThatBestFits(List<Integer> nonPackedArticlesList, int remainedCapacity) {
		Integer foundedArticle = null;

		for (Integer article : nonPackedArticlesList) {
			if (article <= remainedCapacity) {
				foundedArticle = article;
				nonPackedArticlesList.remove(article);
				break;
			}
		}

		return foundedArticle;
	}

}
