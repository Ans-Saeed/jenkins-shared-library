import com.example.Docker

def call(String name){
 return  new Docker(this).dockerPush(name)
}