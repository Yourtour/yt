package com.yt.core.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.yt.core.utils.Constants.ErrorEnum;
import com.yt.core.utils.Constants.StatusEnum;

public class TestEnumUtils {

	@Test
	public void testValueOf1() {
		StatusEnum seDraft = null;
		assertNull(seDraft);
		seDraft = StatusEnum.DRAFT;
		assertNotNull(seDraft);
		assertTrue("Draft".equals(seDraft.code));
		assertTrue("草稿".equals(seDraft.name));
		try {
			assertTrue("Draft".equals(EnumUtils.valueOf(seDraft)));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		seDraft = null;
		assertNull(seDraft);
		seDraft = StatusEnum.CANCELED;
		assertNotNull(seDraft);
		assertTrue("Canceled".equals(seDraft.code));
		assertTrue("取消".equals(seDraft.name));
		try {
			assertTrue("Canceled".equals(EnumUtils.valueOf(seDraft)));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testValueOf2() {
		StatusEnum seDraft = null;
		assertNull(seDraft);
		try {
			seDraft = (StatusEnum) EnumUtils.valueOf("Draft", StatusEnum.class);
			assertNotNull(seDraft);
			assertTrue("Draft".equals(seDraft.code));
			assertTrue("草稿".equals(seDraft.name));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		seDraft = null;
		assertNull(seDraft);
		try {
			seDraft = (StatusEnum) EnumUtils
					.valueOf("Closed", StatusEnum.class);
			assertNotNull(seDraft);
			assertTrue("Closed".equals(seDraft.code));
			assertTrue("关闭".equals(seDraft.name));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		seDraft = null;
		assertNull(seDraft);
		try {
			seDraft = (StatusEnum) EnumUtils
					.valueOf("aabbcc", StatusEnum.class);
			fail("Here must throw a exception.");
		} catch (Exception ex) {
			// do nothing
		}

		seDraft = null;
		assertNull(seDraft);
		try {
			seDraft = (StatusEnum) EnumUtils.valueOf("", StatusEnum.class);
			fail("Here must throw a exception.");
		} catch (Exception ex) {
			// do nothing
		}
	}

	@Test
	public void testCreateDefault() {
		StatusEnum se = (StatusEnum) EnumUtils.createDefault(StatusEnum.class);
		assertNotNull(se);
		assertTrue("Draft".equals(se.code));
		assertTrue("草稿".equals(se.name));
	}

	@Test
	public void testError() {
		ErrorEnum error = null;
		assertNull(error);
		error = (ErrorEnum)EnumUtils.createDefault(ErrorEnum.class);
		assertNotNull(error);
		try {
			EnumUtils.valueOf(error);
			fail("Here must has a exception.");
		} catch (Exception ex) {
			// do nothing
		}

		try {
			EnumUtils.valueOf("high", ErrorEnum.class);
			fail("Here must has a exception.");
		} catch (Exception ex) {
			// do nothing
		}
	}

}
