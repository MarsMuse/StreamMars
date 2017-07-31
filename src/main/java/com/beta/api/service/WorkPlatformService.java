package com.beta.api.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="batchData")
public interface WorkPlatformService {
	@GET
    @Path("/singleCharge")
    @Produces({ MediaType.APPLICATION_JSON })
	String test(String meesage);
	
	@POST
    @Path("/batchCharge")
    @Produces({ MediaType.APPLICATION_JSON })
	String getBatchData(String meesage);
}
