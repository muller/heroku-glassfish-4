import javax.ws.rs.client.*

import org.glassfish.embeddable.*
import org.glassfish.embeddable.archive.*

int port = System.getenv('PORT') ? System.getenv('PORT').toInteger() : 8000

GlassFishProperties glassfishProperties = new GlassFishProperties()
glassfishProperties.setPort("http-listener", port)

println '### Bootstrap GlassFish '

def glassfish = GlassFishRuntime.bootstrap().newGlassFish(glassfishProperties)
glassfish.start()

println '### Create archive'

def file = new File(getClass().protectionDomain.codeSource.location.path)

def war = new ScatteredArchive('heroku-glassfish-4', ScatteredArchive.Type.WAR)
war.addClassPath(file)

println "### Deploy archive ${file}"

glassfish.deployer.deploy(war.toURI(), '--contextroot=/')
