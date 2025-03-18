package io.cloudtype.Demo.template;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class Loader {
	
	private static PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
	
	@Autowired
	private static ResourceLoader loader;
	
	public Loader(ResourceLoader loader) {
		this.loader = loader;
	}
	
	public static Resource getResource(String path) {
		return loader.getResource("classpath:" + path);
	}
	
	public static Resource[] getResources(String path) throws IOException {
		return pathResolver.getResources("classpath:" + path);
	}

}
