package app

import javax.ws.rs.*
import javax.ws.rs.core.* 

@ApplicationPath('/api')
class Api extends Application {

}

@Path('/hello')
@Produces('application/json')
class HelloResources {

	@GET @Path('/{msg}') Hello hello(@PathParam('msg') String msg) {

		new Hello(message: msg)
	}
} 
