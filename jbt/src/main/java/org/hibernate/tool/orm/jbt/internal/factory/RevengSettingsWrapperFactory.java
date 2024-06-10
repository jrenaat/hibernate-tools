package org.hibernate.tool.orm.jbt.internal.factory;

import org.hibernate.tool.api.reveng.RevengSettings;
import org.hibernate.tool.api.reveng.RevengStrategy;
import org.hibernate.tool.orm.jbt.api.wrp.RevengSettingsWrapper;
import org.hibernate.tool.orm.jbt.api.wrp.RevengStrategyWrapper;

public class RevengSettingsWrapperFactory {
	
	public static RevengSettingsWrapper createRevengSettingsWrapper(RevengStrategyWrapper revengStrategyWrapper) {
		RevengStrategy revengStrategy = 
				revengStrategyWrapper == null ? 
						null : (RevengStrategy)revengStrategyWrapper.getWrappedObject();
		RevengSettings wrappedRevengSettings = new RevengSettings(revengStrategy);
		return createRevengSettingsWrapper(wrappedRevengSettings);
	}

	static RevengSettingsWrapper createRevengSettingsWrapper(RevengSettings wrappedRevengSettings) {
		return new RevengSettingsWrapperImpl(wrappedRevengSettings);
	}
	
	private static class RevengSettingsWrapperImpl implements RevengSettingsWrapper {
		
		private RevengSettings revengSettings = null;
		
		private RevengSettingsWrapperImpl(RevengSettings revengSettings) {
			this.revengSettings = revengSettings;
		}
		
		@Override 
		public RevengSettings getWrappedObject() { 
			return revengSettings; 
		}
		
		@Override 
		public void setDefaultPackageName(String s) { 
			revengSettings.setDefaultPackageName(s); 
		}
		
		@Override 
		public void setDetectManyToMany(boolean b) { 
			revengSettings.setDetectManyToMany(b); 
		}
		
		@Override 
		public void setDetectOneToOne(boolean b) { 
			revengSettings.setDetectOneToOne(b); 
		}
		
		@Override 
		public void setDetectOptimisticLock(boolean b) { 
			revengSettings.setDetectOptimisticLock(b); 
		}

	}

}
