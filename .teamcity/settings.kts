import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.1"

project {

    buildType(Mr)
    buildType(Buildcnf)
}

object Buildcnf : BuildType({
    name = "buildcnf"

    steps {
        step {
            type = "Metarunner_Mr"
            param("githubtokenroot", "credentialsJSON:4b7d9c29-fd7e-47df-b48f-c2bae8c224d2")
            param("USE_DAEMON", "true")
        }
        script {
            scriptContent = "n"
        }
    }
})

object Mr : BuildType({
    name = "mr"

    params {
        param("env.JAVA_HOMEs", "s")
        param("mr_defpar", "1")
    }

    steps {
        script {
            scriptContent = "echo a"
        }
        script {
            scriptContent = "echo b"
        }
    }
})
