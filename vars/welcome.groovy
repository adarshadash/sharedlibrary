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

def getTag() {

    newtag = _load()
    tag = "${newtag['version']}.${newtag['build']}"
    return tag

}
