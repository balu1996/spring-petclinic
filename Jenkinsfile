pipeline {
    agent any 
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
    }
}
