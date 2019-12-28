package org.uvsq.ds.springmvc101.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.uvsq.ds.springmvc101.project.Nature;
import org.uvsq.ds.springmvc101.project.Project;
import org.uvsq.ds.springmvc101.project.ProjectService;

@Component
public class DevConfigurator implements ApplicationListener<ContextRefreshedEvent>{
	
	private Logger logger = LoggerFactory.getLogger(DevConfigurator.class);
	
	public DevConfigurator() {
		logger.info(this.getClass().getSimpleName()+" Creation");
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		logger.info("Configure projectService");
		
		ProjectService projectService = event.getApplicationContext().getBean(ProjectService.class);
		Map<String, Nature> setup = new HashMap<>();
		setup.put("MagicCakeFactory", Nature.OFFICIAL);
		setup.put("BloodyMary", Nature.OPEN);
		setup.put("AwesomeBanck", Nature.OFFICIAL);
		setup.put("Open-sea-action", Nature.OPEN);
		setup.put("save-zi-planet", Nature.OPEN);
		
		setup.keySet().forEach(key -> {
			Project p = new Project();
			p.setName(key);
			p.setNature(setup.get(key));
			projectService.createProject(p);
		});
		
	}

}