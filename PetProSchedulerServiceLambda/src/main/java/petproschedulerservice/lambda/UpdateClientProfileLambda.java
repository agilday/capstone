package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.UpdateClientProfileRequest;
import petproschedulerservice.activity.results.UpdateClientProfileResult;

public class UpdateClientProfileLambda extends LambdaActivityRunner<UpdateClientProfileRequest, UpdateClientProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateClientProfileRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateClientProfileRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateClientProfileRequest unauthenticatedRequest = input.fromBody(UpdateClientProfileRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateClientProfileRequest.builder()
                                    .withId(unauthenticatedRequest.getId())
                                    .withName(unauthenticatedRequest.getName())
                                    .withPhone(unauthenticatedRequest.getPhone())
                                    .withAddress(unauthenticatedRequest.getAddress())
                                    .withNotes(unauthenticatedRequest.getNotes())
                                    .withPets(unauthenticatedRequest.getPets())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateClientProfileActivity().handleRequest(request)
        );
    }
}
