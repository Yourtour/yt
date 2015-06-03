package com.yt.core.utils;

import static org.junit.Assert.*;

import org.junit.Test;

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
			seDraft = (StatusEnum) EnumUtils.valueOf("Closed", StatusEnum.class);
			assertNotNull(seDraft);
			assertTrue("Closed".equals(seDraft.code));
			assertTrue("关闭".equals(seDraft.name));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testCreateDefault() {
		StatusEnum se = (StatusEnum) EnumUtils.createDefault(StatusEnum.class);
		assertNotNull(se);
		assertTrue("Draft".equals(se.code));
		assertTrue("草稿".equals(se.name));
	}

}
