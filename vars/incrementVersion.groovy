import com.example.Docker

def call(String location){
    def docker = new Docker(this)
    def imageName = docker.incrementVersion(location)
    return imageName
}