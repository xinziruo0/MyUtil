package com.lsz.utils.es;

import java.util.Map;
import java.util.Set;

import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;
/**
 * es的配置
 * @author lsz
 *
 */
public class BeanEsSetting implements MySettings{
	private Map<String, String> settingMap;
	private Builder settings = ImmutableSettings.settingsBuilder();
	public Settings build() {
		
		settings.put(settingMap);
		return settings.build();
	}
	public Map<String, String> getSettingMap() {
		return settingMap;
	}
	public void setSettingMap(Map<String, String> settingMap) {
		this.settingMap = settingMap;
	}

	
}
