package org.uvsq.ds.springmvc101.project;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectController {
	
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(value = "/projects", method=RequestMethod.GET)
	@ResponseBody
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}
	
	@PostMapping("/projects-random")
	@ResponseBody
	public Project createRandomProject()
	{
		Project project = new Project();
		project.setName(UUID.randomUUID().toString());
		project.setNature(Nature.OPEN);
		Project created = projectService.createProject(project);
		return created;
	}
	
	@PostMapping("/projects")
	@ResponseBody
	public Project createProject(@RequestBody Project project)
	{
		
		return projectService.createProject(project);
	}
	
	@RequestMapping( value = "/projects/{id}", method=RequestMethod.GET)
	public Project getProjectBy(@PathVariable("id") Long id)
	{
		return projectService.getProjectById(id);
	}
}
