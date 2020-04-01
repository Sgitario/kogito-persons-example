package org.sgitario.kogito;

import org.kie.api.runtime.KieSession;
import org.kie.kogito.rules.KieRuntimeBuilder;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/check-age")
public class CheckAgeResource {
	
	private static final Person JOSE = new Person("Jose", 18);
 
    @Inject
    KieRuntimeBuilder runtimeBuilder;
 
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public boolean checkPerson(Person p) {
    	Result result = new Result();
        KieSession ksession = runtimeBuilder.newKieSession();
        ksession.insert(JOSE);
        ksession.insert(p);
        ksession.insert(result);
        ksession.fireAllRules();
        return result.isOlder();
 
    }
}
