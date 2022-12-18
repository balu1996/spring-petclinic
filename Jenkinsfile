pipeline {
    agent any 
    environment {
        //once you create ACR in Azure cloud, use that here
        registryName = "balumahendran"
        //- update your credentials ID after creating credentials for connecting to ACR
        registryCredential = 'ACR'
        dockerImage = ''
        registryUrl = 'balumahendran.azurecr.io'
    }
    tools {
        maven "myymaven"
    }
     stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/balu1996/spring-petclinic.git'

                // Run Maven on a Unix agent.
                 sh 'mvn clean package'

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Image Build and Push to respository') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'ACR', passwordVariable: 'balu-pass', usernameVariable: 'balu')]) 
                    {
               dockerImage =  docker.build registryName
               docker.withRegistry( "http://${registryUrl}", registryCredential ) {
               dockerImage.push()
            } }
                }
            }
        }
        stage ('Deploy application into cluster') {
          steps {
            script {
                withKubeConfig([credentialsId: 'K8S', serverUrl: '']) {
                sh ('kubectl apply -f  jenkins-aks-deploy-from-acr.yaml')
                }
            }
        }
     }
    }
}

