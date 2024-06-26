package org.hibernate.tool.orm.jbt.api.wrp;

import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

public interface ExporterWrapper extends Wrapper {
	
	void setConfiguration(ConfigurationWrapper configuration);
	
	void setArtifactCollector(ArtifactCollectorWrapper artifactCollectorWrapper);
	
	void setOutputDirectory(File dir);

	void setTemplatePath(String[] templatePath);

	void start();

	Properties getProperties();

	GenericExporterWrapper getGenericExporter();

	DdlExporterWrapper getHbm2DDLExporter();

	QueryExporterWrapper getQueryExporter();

	void setCustomProperties(Properties properties);

	void setOutput(StringWriter stringWriter);

}
