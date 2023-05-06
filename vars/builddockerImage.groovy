#!/usr/bin/env groovy
import com.example.Docker
def call(String name, String location){
    return new Docker(this).builddockerImage(name,location)
}
