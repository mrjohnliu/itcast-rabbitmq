pipeline {
   agent any

   stages {
      stage('pull code') {
         steps {
           checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'dd6c24b0-bd7f-4a74-8817-53ccf14d1968', url: 'https://github.com/mrjohnliu/itcast-rabbitmq.git']]])         }
      }
         stage('build') {
         steps {
         bat label: '', script: 'mvn clean package'
             
         }
      }
   }
}
