@Grab('org.yaml:snakeyaml:1.17')
    
import org.yaml.snakeyaml.Yaml

def call(name){
   echo "Hey ${name}, How are you?"
}

def anothercall(name){
   echo "Hey ${name}, Another how are you ? "
}

def _load() {

  Yaml parser = new Yaml()
  application = parser.load(("${env.WORKSPACE}/application.yaml" as File).text)
  return application

}

def _save(application) {

  Yaml parser = new Yaml()
  parser.dump(application, new FileWriter("${env.WORKSPACE}/application.yaml"))

}

def getVersion() {

    newtag = _load()
    tag = "${newtag['version']}"
    echo "HEy VErsion id is - ${tag}"
    return tag

}

def getBuild(){
    
    newtag = _load()
    tag = "${newtag['build']}"
    echo "HEy Build id is - ${tag}"
    return tag

}

def incrementbyone(number){
    number=number.toInteger()+1
    echo "updated number is ${number}"
    return number
}


def updateApplication(){
    application = _load()
    application['build']++
    _save(application)
    sh "git add ${env.WORKSPACE}/application.yaml"
    sh "cat ${env.WORKSPACE}/application.yaml"
    sh "echo 'The current build is: ${version}'"   
    sh "git commit -m 'increment version: ${version}'"
    sh "git tag -a -m 'version ${version}' ${version}"
}
