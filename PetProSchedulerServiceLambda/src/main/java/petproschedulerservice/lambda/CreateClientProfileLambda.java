package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.CreateClientProfileRequest;
import petproschedulerservice.activity.results.CreateClientProfileResult;

import javax.management.InvalidAttributeValueException;

public class CreateClientProfileLambda extends LambdaActivityRunner<CreateClientProfileRequest, CreateClientProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateClientProfileRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateClientProfileRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateClientProfileRequest unauthenticatedRequest = input.fromBody(CreateClientProfileRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateClientProfileRequest.builder()
                            .withName(unauthenticatedRequest.getName())
                            .withPhone(unauthenticatedRequest.getPhone())
                            .withAddress(unauthenticatedRequest.getAddress())
                            .withNotes(unauthenticatedRequest.getNotes())
                            .withPets(unauthenticatedRequest.getPets())
                            .build());

                },
                (request, serviceComponent) ->
                {
                    try {
                        return serviceComponent.provideCreateClientProfileActivity().handleRequest(request);
                    } catch (InvalidAttributeValueException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
