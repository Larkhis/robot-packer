package com.xspeedit.service;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.xspeedit.exception.RobotPackerInvalidInputException;
import static com.xspeedit.constant.RobotPackerConstant.*;

/**
 * RobotPackerOptmizedServiceImpl junit test cases
 * @author lokmanearkhis
 */
public class RobotPackerOptmizedServiceImplTests {
	
	private static final Logger LOGGER = Logger.getLogger(RobotPackerOptmizedServiceImplTests.class);
	private RobotPackerService target = new RobotPackerOptmizedServiceImpl();
	
	@Test
	public void test_optmized_pack_success() {
		try {
			String actual = target.pack("163841689525773");
			assertEquals("91/82/81/73/73/64/6/55", actual);
			assertEquals(8, actual.split(CARTON_SEPARATOR).length);
		} catch (RobotPackerInvalidInputException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Test(expected=RobotPackerInvalidInputException.class)
	public void test_optmized_pack_invalidInput() throws RobotPackerInvalidInputException {
		target.pack("163841689525773a");
	}
	
}
