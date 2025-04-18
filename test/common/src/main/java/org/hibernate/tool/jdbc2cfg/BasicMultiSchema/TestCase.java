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
package org.hibernate.tool.jdbc2cfg.BasicMultiSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.hibernate.boot.Metadata;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.Table;
import org.hibernate.tool.api.metadata.MetadataDescriptorFactory;
import org.hibernate.tools.test.util.HibernateUtil;
import org.hibernate.tools.test.util.JUnitUtil;
import org.hibernate.tools.test.util.JdbcUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author max
 * @author koen
 */
public class TestCase {

	private Metadata metadata = null;

	@BeforeEach
	public void setUp() {
		JdbcUtil.createDatabase(this);
		metadata = MetadataDescriptorFactory
				.createReverseEngineeringDescriptor(null, null)
				.createMetadata();
	}

	@AfterEach
	public void tearDown() {
		JdbcUtil.dropDatabase(this);
	}

	@Test
	public void testBasic() throws SQLException {

		JUnitUtil.assertIteratorContainsExactly(
				"There should be three tables!", 
				metadata.getEntityBindings().iterator(),
				3);

		Table table = HibernateUtil.getTable(
				metadata, 
				JdbcUtil.toIdentifier(this, "BASIC" ) );

		assertEquals( 
				JdbcUtil.toIdentifier(this, "BASIC"), 
				JdbcUtil.toIdentifier(this, table.getName()) );
		assertEquals( 2, table.getColumnSpan() );

		Column basicColumn = table.getColumn( 0 );
		assertEquals( 
				JdbcUtil.toIdentifier(this, "A"), 
				JdbcUtil.toIdentifier(this, basicColumn.getName() ));

		PrimaryKey key = table.getPrimaryKey();
		assertNotNull(key, "There should be a primary key!" );
		assertEquals( key.getColumnSpan(), 1 );

		Column column = key.getColumn( 0 );
		assertTrue( column.isUnique() );

		assertSame( basicColumn, column );

	}

	@Test
	public void testScalePrecisionLength() {
		Table table = HibernateUtil.getTable(
				metadata, 
				JdbcUtil.toIdentifier(this, "BASIC" ) );
		Column nameCol = table.getColumn( new Column( JdbcUtil.toIdentifier(this, "NAME" ) ) );
		assertEquals( nameCol.getLength().intValue(), 20 );
		assertEquals( nameCol.getPrecision(), null );
		assertEquals( nameCol.getScale(), null );
	}

	@Test
	public void testCompositeKeys() {
		Table table = HibernateUtil.getTable(
				metadata, 
				JdbcUtil.toIdentifier(this, "MULTIKEYED"));
		PrimaryKey primaryKey = table.getPrimaryKey();
		assertEquals( 2, primaryKey.getColumnSpan() );
	}

}
