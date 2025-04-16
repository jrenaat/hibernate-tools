/*
 * Hibernate Tools, Tooling for your Hibernate Projects
 *
 * Copyright 2004-2025 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hibernate.tool.ant.GenericExport;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.hibernate.tools.test.util.AntUtil;
import org.hibernate.tools.test.util.FileUtil;
import org.hibernate.tools.test.util.JdbcUtil;
import org.hibernate.tools.test.util.ResourceUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TestCase {
	
	@TempDir
	public File outputFolder = new File("output");
	
	private File destinationDir = null;
	private File resourcesDir = null;
	
	@BeforeEach
	public void setUp() {
		destinationDir = new File(outputFolder, "destination");
		destinationDir.mkdir();
		resourcesDir = new File(outputFolder, "resources");
		resourcesDir.mkdir();
		JdbcUtil.createDatabase(this);
	}
	
	@AfterEach
	public void tearDown() {
		JdbcUtil.dropDatabase(this);
	}
	
	@Test
	public void testGenericExport() {

		String[] resources = new String[] {"build.xml", "TopDown.hbm.xml", "generic-class.ftl"};
		ResourceUtil.createResources(this, resources, resourcesDir);
		File buildFile = new File(resourcesDir, "build.xml");	
		ResourceUtil.createResources(this, new String[] { "/hibernate.properties" }, resourcesDir);
		
		AntUtil.Project project = AntUtil.createProject(buildFile);
		project.setProperty("destinationDir", destinationDir.getAbsolutePath());
		project.setProperty("resourcesDir", resourcesDir.getAbsolutePath());
		
		File xTopDownJava = new File(destinationDir, "Xorg/hibernate/tool/hbm2x/ant/TopDown.java");
		assertFalse(xTopDownJava.exists());
		
		File topDownJava = new File(destinationDir, "org/hibernate/tool/hbm2x/ant/TopDown.java");
		assertFalse(topDownJava.exists());		

		File topDownQuote = new File(destinationDir, "org/hibernate/tool/hbm2x/ant/TopDown.quote");
		assertFalse(topDownQuote.exists());		

		File topDownPojo = new File(destinationDir, "org/hibernate/tool/hbm2x/ant/TopDown.pojo");
		assertFalse(topDownPojo.exists());		

		project.executeTarget("testGenericExport");
		
		assertTrue(xTopDownJava.exists());
		assertTrue(FileUtil
				.findFirstString("TopDown", xTopDownJava)
				.contains("TopDown generated by hbm2java"));

		assertTrue(topDownJava.exists());
		assertTrue(FileUtil
				.findFirstString("TopDown", topDownJava)
				.contains("TopDown generated by hbm2java"));

		assertTrue(topDownQuote.exists());
		assertTrue(FileUtil
				.findFirstString("TopDown", topDownQuote)
				.contains("pojo=TopDown"));

		assertTrue(topDownPojo.exists());
		assertTrue(FileUtil
				.findFirstString("TopDown", topDownPojo)
				.contains("TopDown generated by hbm2java"));

	}
	
	
}
