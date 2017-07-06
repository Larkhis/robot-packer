package com.xspeedit.service;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.xspeedit.exception.RobotPackerInvalidInputException;
import static com.xspeedit.constant.RobotPackerConstant.*;

/**
 * RobotPackerBasicServiceImpl junit test cases
 * @author lokmanearkhis
 */
public class RobotPackerBasicServiceImplTests {
	
	private static final Logger LOGGER = Logger.getLogger(RobotPackerBasicServiceImplTests.class);
	private RobotPackerService target = new RobotPackerBasicServiceImpl();
	
	@Test
	public void test_basic_pack_success() {
		try {
			String result = target.pack("163841689525773");
			assertEquals("163/8/41/6/8/9/52/5/7/73", result);
			assertEquals(10, result.split(CARTON_SEPARATOR).length);
		} catch (RobotPackerInvalidInputException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Test(expected=RobotPackerInvalidInputException.class)
	public void test_basic_pack_invalidInput() throws RobotPackerInvalidInputException {
		target.pack("163841689525773a");
	}
	
}
