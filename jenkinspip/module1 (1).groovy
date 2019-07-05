
node {
	def config = new CIConfig().getConfig();
	echo "thi is ${config.gitURL}"
	try {
	properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10')), parameters([string(defaultValue: '', description: '', name: 'branchName', trim: false)]), pipelineTriggers([pollSCM('* * 1 1 *')])])
	stage('checkout')
	checkoutCodeBase(config);
	
	echo "this is a string ${params.branchName}";
	}catch (Exception e){
			 echo "Failure occured at some stage in pipeline with ${params.branchName}";
			//sendEmailNotification(config: config,statusJob: statusJob)
			throw e;
		}
	
	
}
def checkoutCodeBase(config) {
	gitTools.checkOut(URL: config.gitURL)	
}
