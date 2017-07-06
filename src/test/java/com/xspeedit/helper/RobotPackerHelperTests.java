package com.xspeedit.helper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.xspeedit.exception.RobotPackerInvalidInputException;
import com.xspeedit.service.RobotPackerBasicServiceImplTests;

public class RobotPackerHelperTests {

	private static final Logger LOGGER = Logger.getLogger(RobotPackerBasicServiceImplTests.class);

	@Test
	public void test_toListOfInteger_success() {
		try {
			List<Integer> expected = Arrays.asList(1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3);
			assertEquals(expected, RobotPackerHelper.toListOfInteger("163841689525773"));
		} catch (RobotPackerInvalidInputException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Test(expected = RobotPackerInvalidInputException.class)
	public void test_toListOfInteger_invalidInput() throws RobotPackerInvalidInputException {
		RobotPackerHelper.toListOfInteger("163841689525773a");
	}

}
