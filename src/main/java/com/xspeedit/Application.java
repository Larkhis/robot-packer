package com.xspeedit;

import org.apache.log4j.Logger;

import static com.xspeedit.constant.RobotPackerConstant.*;
import com.xspeedit.exception.RobotPackerInvalidInputException;
import com.xspeedit.service.RobotPackerBasicServiceImpl;
import com.xspeedit.service.RobotPackerOptmizedServiceImpl;
import com.xspeedit.service.RobotPackerService;

/**
 * Program entry point class
 *  
 * @author lokmanearkhis
 */
public class Application {
	
	private static final String DEFAULT_ARTICLES = "163841689525773";
	private static final Logger LOGGER = Logger.getLogger(Application.class);

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		String articles = DEFAULT_ARTICLES;
		
		// If no args are passed, the default articles are taken into account
		if(args.length == 1) {
			articles = args[0];
		} 
		
		RobotPackerService basicRobotPacker = new RobotPackerBasicServiceImpl();
		RobotPackerService optmizedRobotPacker = new RobotPackerOptmizedServiceImpl();
		try {
			String basicSolution = basicRobotPacker.pack(articles);
			String optmizedSolution = optmizedRobotPacker.pack(articles);
			int nbUsedCartons = basicSolution.split(CARTON_SEPARATOR).length;
			int nbOptmizedUsedCartons = optmizedSolution.split(CARTON_SEPARATOR).length;
			System.out.println("Robot actuel  : " + basicSolution + " => " + nbUsedCartons + " cartons utilisés");
			System.out.println("Robot optimisé  : " + optmizedSolution + " => " + nbOptmizedUsedCartons + " cartons utilisés");
		} catch (RobotPackerInvalidInputException e) {
			LOGGER.error(e.getMessage());
		}
		
	}

}
