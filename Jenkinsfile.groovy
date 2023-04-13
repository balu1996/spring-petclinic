//Jenkinsfile for Build and check codequality changes //
pipeline { 
    tools {
        maven 'mymaven'
    }
    agent any
//environment{
   //-- M2_HOME='/usr/share/maven' 
//}
    stages {  
		  stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/balu1996/spring-petclinic.git']]])     
            }
        }

           stage('Build') {
            steps {
			 script {
			    // echo 'export M2_HOME=/usr/share/maven' >> ~/.bashrc
			    //sh 'mvn -Dmaven.test.failure.ignore=true clean package' 
			    sh 'mvn clean package'
                }
				}
				}
	  stage('Integration Test') {
      steps {
        sh 'mvn verify'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
       }
        success {
       emailext body: "Integration tests have completed. Please see attached test results.",
        subject: "Integration test results",
        attachmentsPattern: '**/surefire-reports/*.xml',
        to:'balumahendranss@gmail.com'
      }
    }
  }
   stage("Sonarqube analysis"){
            steps{
                script{
                     withSonarQubeEnv('sonarserver') {
                     sh 'mvn sonar:sonar'
                  }
                }
             	}
                }
      stage('Quality Gate') {
            steps {
                script {
                    def qualityGate = waitForQualityGate()
                    if (qualityGate.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
                    }
                }
            }
        }
}
}

