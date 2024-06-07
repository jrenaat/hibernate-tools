package org.hibernate.tool.orm.jbt.api.factory;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Any;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Bag;
import org.hibernate.mapping.BasicValue;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.DependantValue;
import org.hibernate.mapping.IdentifierBag;
import org.hibernate.mapping.KeyValue;
import org.hibernate.mapping.List;
import org.hibernate.mapping.ManyToOne;
import org.hibernate.mapping.OneToMany;
import org.hibernate.mapping.OneToOne;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.PrimitiveArray;
import org.hibernate.mapping.Set;
import org.hibernate.mapping.Table;
import org.hibernate.tool.api.reveng.RevengStrategy;
import org.hibernate.tool.ide.completion.HQLCompletionProposal;
import org.hibernate.tool.internal.reveng.strategy.TableFilter;
import org.hibernate.tool.orm.jbt.api.wrp.PersistentClassWrapper;
import org.hibernate.tool.orm.jbt.api.wrp.PropertyWrapper;
import org.hibernate.tool.orm.jbt.internal.factory.ArtifactCollectorWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.Cfg2HbmToolWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.ColumnWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.ConfigurationWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.DatabaseReaderWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.EnvironmentWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.HqlCompletionProposalWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.NamingStrategyWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.OverrideRepositoryWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.PersistentClassWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.PropertyWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.RevengSettingsWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.RevengStrategyWrapperFactory;
import org.hibernate.tool.orm.jbt.internal.factory.TableWrapperFactory;
import org.hibernate.tool.orm.jbt.util.DummyMetadataBuildingContext;
import org.hibernate.tool.orm.jbt.util.MetadataHelper;
import org.hibernate.tool.orm.jbt.wrp.ExporterWrapperFactory;
import org.hibernate.tool.orm.jbt.wrp.HbmExporterWrapper;
import org.hibernate.tool.orm.jbt.wrp.HqlCodeAssistWrapper;
import org.hibernate.tool.orm.jbt.wrp.SchemaExportWrapper;
import org.hibernate.tool.orm.jbt.wrp.TypeFactoryWrapper;
import org.hibernate.tool.orm.jbt.wrp.ValueWrapperFactory;
import org.hibernate.tool.orm.jbt.wrp.Wrapper;

public class WrapperFactory {
	
	private WrapperFactory() {}

	public static Object createArtifactCollectorWrapper() {
		return ArtifactCollectorWrapperFactory.createArtifactCollectorWrapper();
	}
	
	public static Object createCfg2HbmWrapper() {
		return Cfg2HbmToolWrapperFactory.createCfg2HbmToolWrapper();
	}

	public static Object createNamingStrategyWrapper(String namingStrategyClassName) {
		return NamingStrategyWrapperFactory.createNamingStrategyWrapper(namingStrategyClassName);
	}
	
	public static Object createOverrideRepositoryWrapper() {
		return OverrideRepositoryWrapperFactory.createOverrideRepositoryWrapper();
	}
	
	public static Object createRevengStrategyWrapper(Object...objects) {
		return RevengStrategyWrapperFactory.createRevengStrategyWrapper(objects);
	}

	public static Object createRevengSettingsWrapper(Object revengStrategy) {
		return RevengSettingsWrapperFactory.createRevengSettingsWrapper(revengStrategy);
	}
	
	public static Object createNativeConfigurationWrapper() {
		return ConfigurationWrapperFactory.createNativeConfigurationWrapper();
	}
	
	public static Object createRevengConfigurationWrapper() {
		return ConfigurationWrapperFactory.createRevengConfigurationWrapper();
	}

	public static Object createJpaConfigurationWrapper(String persistenceUnit, Map<?,?> properties) {
		return ConfigurationWrapperFactory.createJpaConfigurationWrapper(persistenceUnit, properties);
	}

	public static Object createColumnWrapper(String name) {
		return ColumnWrapperFactory.createColumnWrapper(name);
	}

	public static Object createRootClassWrapper() {
		return PersistentClassWrapperFactory.createRootClassWrapper();
	}

	public static Object createSingleTableSubClassWrapper(Object persistentClassWrapper) {
		return PersistentClassWrapperFactory.createSingleTableSubClassWrapper((PersistentClassWrapper)persistentClassWrapper);
	}

	public static Object createJoinedTableSubClassWrapper(Object persistentClassWrapper) {
		return PersistentClassWrapperFactory.createJoinedTableSubClassWrapper((PersistentClassWrapper)persistentClassWrapper);
	}

	public static Object createSpecialRootClassWrapper(Object propertyWrapper) {
		return PersistentClassWrapperFactory.createSpecialRootClassWrapper(((PropertyWrapper)propertyWrapper));
	}

	public static Object createPropertyWrapper() {
		return PropertyWrapperFactory.createPropertyWrapper();
	}

	public static Object createHqlCompletionProposalWrapper(Object hqlCompletionProposalTarget) {
		return HqlCompletionProposalWrapperFactory.createHqlCompletionProposalWrapper(
				(HQLCompletionProposal)hqlCompletionProposalTarget);
	}
	
	public static Object createArrayWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new Array(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}

	public static Object createBagWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new Bag(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}

	public static Object createListWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new List(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}
	
	public static Object createDatabaseReaderWrapper(Properties properties, Object revengStrategy) {
		return DatabaseReaderWrapperFactory.createDatabaseReaderWrapper(
				properties, 
				(RevengStrategy)revengStrategy);
	}

	public static Object createTableWrapper(String name) {
		return TableWrapperFactory.createTableWrapper(name);
	}

	public static Object createManyToOneWrapper(Object table) {
		return ValueWrapperFactory.createValueWrapper(
				new ManyToOne(
						DummyMetadataBuildingContext.INSTANCE, 
						(Table)table));
	}

	public static Object createMapWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new org.hibernate.mapping.Map(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}

	public static Object createOneToManyWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new OneToMany(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}

	public static Object createOneToOneWrapper(Object persistentClassWrapper) {
		PersistentClass pc = (PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject();
		return ValueWrapperFactory.createValueWrapper(
				new OneToOne(
						DummyMetadataBuildingContext.INSTANCE, 
						pc.getTable(),
						pc));
	}

	public static Object createPrimitiveArrayWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new PrimitiveArray(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}

	public static Object createSetWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new Set(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}

	public static Object createSimpleValueWrapper() {
		return ValueWrapperFactory.createValueWrapper(new BasicValue(DummyMetadataBuildingContext.INSTANCE));
	}

	public static Object createComponentWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new Component(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}
	
	public static Object createDependantValueWrapper(Object table, Object valueWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new DependantValue(
						DummyMetadataBuildingContext.INSTANCE, 
						(Table)table, 
						(KeyValue)((Wrapper)valueWrapper).getWrappedObject()));
	}

	public static Object createAnyValueWrapper(Object table) {
		return ValueWrapperFactory.createValueWrapper(
				new Any(
						DummyMetadataBuildingContext.INSTANCE, 
						(Table)table));
	}

	public static Object createIdentifierBagValueWrapper(Object persistentClassWrapper) {
		return ValueWrapperFactory.createValueWrapper(
				new IdentifierBag(
						DummyMetadataBuildingContext.INSTANCE, 
						(PersistentClass)((Wrapper)persistentClassWrapper).getWrappedObject()));
	}

	public static Object createTableFilterWrapper() {
		return new TableFilter();
	}

	public static Object createTypeFactoryWrapper() {
		return TypeFactoryWrapper.INSTANCE;
	}

	public static Object createEnvironmentWrapper() {
		return EnvironmentWrapperFactory.createEnvironmentWrapper();
	}

	public static Object createSchemaExport(Object configuration) {
		return new SchemaExportWrapper((Configuration)configuration);
	}
	
	public static Object createHbmExporterWrapper(Object configuration, File file) {
		return new HbmExporterWrapper((Configuration)configuration, file);
	}

	public static Object createExporterWrapper(String exporterClassName) {
		return ExporterWrapperFactory.create(exporterClassName);
	}
	
	public static Object createHqlCodeAssistWrapper(Object configuration) {
		return new HqlCodeAssistWrapper(MetadataHelper.getMetadata((Configuration)configuration));
	}

}