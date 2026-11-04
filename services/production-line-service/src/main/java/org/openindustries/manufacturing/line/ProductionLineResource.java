package org.openindustries.manufacturing.line;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/production-lines")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Production Lines", description = "Production line registry per plant.")
public class ProductionLineResource {

    @Inject
    ProductionLineService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a productionline")
    public Response create(@Valid CreateProductionLineRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<ProductionLine> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public ProductionLine get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/pause")
    @Operation(summary = "Pause this productionline (lifecycle transition, no body)")
    public ProductionLine pause(@PathParam("id") UUID id) {
        return service.pause(id);
    }

    @POST
    @Path("/{id}/retire")
    @Operation(summary = "Retire this productionline (lifecycle transition, no body)")
    public ProductionLine retire(@PathParam("id") UUID id) {
        return service.retire(id);
    }
}
