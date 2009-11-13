package wb;

import java.util.HashMap;

import org.w3c.dom.Document;

import cma.fitness.IObjectiveFunction;

public class EaServer {
	HashMap<String,EaInstance> instances;
	
	public EaServer(){
		instances = new HashMap<String,EaInstance>();
	}
	
	public void AddJob(String params, String jobName){
		String paramsFilePath = Util.WirteParamsFile(params,jobName);
		instances.put(jobName, new EaInstance(paramsFilePath,jobName,Util.GetSeed()));
		System.out.println("starting1 instance ");
		instances.get(jobName).spawnPlayThread();
		System.out.println("starting2 instance ");
	}
	
	public void StopJob(String jobName){
		instances.get(jobName).tellThreadToStop();
	}
	
	public String GetStatus(String jobName){
		return instances.get(jobName).getStatus();
	}
	
	public String GetStatus(){
		return "all jobs status";
	}	
		
	public void PauseJob(String jobName){
		instances.get(jobName).setPaused(true);
	}		
	
	public void ResumeJob(String jobName){
		instances.get(jobName).setPaused(false);
	}

	public String GetStatistics(String jobName){
		boolean doesExists = false;
		if(instances.containsKey(jobName)){
			return instances.get(jobName).getState().document.getDocumentElement().toString();
		
					
		}
		else{
			return " jobName not found: " + jobName;
		}

	}
	

}