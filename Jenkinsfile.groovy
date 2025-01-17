//Jenkinsfile for Build and check codequality changes //
pipeline { 
    tools {
        maven 'mymaven'
    }
    agent any
 environment {
        registry = "190344882422.dkr.ecr.ap-south-1.amazonaws.com"
         ACCESS_KEY = credentials('AWS_ACCESS_KEY_ID')
        SECRET_KEY = credentials('AWS_SECRET_ACCESS_KEY')
         region ="ap-south-1"
         cluster_name ="demo-cluster1"
    }
    stages {  
		  stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/balu1996/spring-petclinic.git']]])     
            }
        }

          // stage('Build') {
            // steps {
			// script {
			     // echo 'export M2_HOME=/usr/share/maven' >> ~/.bashrc
			     // sh 'mvn -Dmaven.test.failure.ignore=true clean package' 
			     //sh 'mvn clean install'
                //}
				//}
				//}
    //stage('Integration Test') {
      //steps {
        //sh 'mvn verify'
          // }
        // post {
          //always {
          //  junit 'target/surefire-reports/*.xml'
       //}
           //success {
          //emailext body: "Integration tests have completed. Please see attached test results.",
          //subject: "Integration test results",
          //attachmentsPattern: '**/surefire-reports/*.xml',
          //to:'balumahendranss@gmail.com'
         // }
       //}
     //}
   //stage("Sonarqube analysis"){
           //steps{
                //script{
                  //   withSonarQubeEnv('sonarserver') {
                //     sh 'mvn sonar:sonar'
              //    }
            //    }
          //   	}
        //        }
      //stage('Quality Gate') {
            //steps {
                //script {
                    //def qualityGate = waitForQualityGate()
                  //  if (qualityGate.status != 'OK') {
                //        error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
              //      }
            //    }
          //  }
        //}
       //stage('Building image') {
      //steps{
       // script {
        //  dockerImage = docker.build registry 
        //}
      //}
    //}
   
     //Uploading Docker images into AWS ECR
     //stage('Building and Pushing to ECR') {
     //steps{  
        // script {
               // sh """
                //  aws configure set aws_access_key_id "$ACCESS_KEY"
                 // aws configure set aws_secret_access_key "$SECRET_KEY"
                  // aws configure set region "$region"
                   //"""
                  //sh 'aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin  "$registry" '
                  //sh 'docker build -t hello . '
                  //sh  'docker tag hello:latest 190344882422.dkr.ecr.ap-south-1.amazonaws.com/hello:latest'
                  //sh 'docker push 190344882422.dkr.ecr.ap-south-1.amazonaws.com/hello:latest'

         //}
       // }
     //}
      
    }
}


