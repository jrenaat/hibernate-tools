package org.hibernate.tool.orm.jbt.wrp;

import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Subclass;
import org.hibernate.tool.orm.jbt.wrp.PersistentClassWrapperFactory.RootClassWrapperImpl;

public interface PersistentClassWrapper {

	default PersistentClass getWrappedObject() { return (PersistentClass)this; }
	String getEntityName();
	String getClassName();
	boolean isAssignableToRootClass();
	default boolean isRootClass() { return getWrappedObject().getClass() == RootClassWrapperImpl.class; }
	Property getIdentifierProperty();
	boolean hasIdentifierProperty();
	default boolean isInstanceOfRootClass() { return isAssignableToRootClass(); }
	default boolean isInstanceOfSubclass() { return Subclass.class.isAssignableFrom(getWrappedObject().getClass()); }

}