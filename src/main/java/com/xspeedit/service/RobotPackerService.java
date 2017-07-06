package com.xspeedit.service;

import com.xspeedit.exception.RobotPackerInvalidInputException;

/**
 * RobotPacker service interface
 * @author lokmanearkhis
 */
public interface RobotPackerService {
	
	/**
	 * Packs the articles into cartons
	 * @param articles - chain of articles where each article is represented by his size
	 * @return return the articles packed into cartons of capacity {@link #CARTON_CAPACITY} separated by {@link #CARTON_SEPARATOR}
	 * @throws RobotPackerInvalidInputException
	 */
	public String pack(String articles) throws RobotPackerInvalidInputException;
	
}
