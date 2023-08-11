package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.CreateGroomerProfileRequest;
import petproschedulerservice.activity.results.CreateGroomerProfileResult;

import javax.management.InvalidAttributeValueException;

public class CreateGroomerProfileLambda extends LambdaActivityRunner<CreateGroomerProfileRequest, CreateGroomerProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateGroomerProfileRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateGroomerProfileRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateGroomerProfileRequest unauthenticatedRequest = input.fromBody(CreateGroomerProfileRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateGroomerProfileRequest.builder()
                                    .withLname(unauthenticatedRequest.getLname())
                                    .withFname(unauthenticatedRequest.getFname())
                                    .withPhone(unauthenticatedRequest.getPhone())
                                    .withAvailability(unauthenticatedRequest.getAvailability())
                                    .withNotes(unauthenticatedRequest.getNotes())
                                    .withGroomsCats(unauthenticatedRequest.getGroomsCats())
                                    .build());

                },
                (request, serviceComponent) ->
                {
                    try {
                        return serviceComponent.provideCreateGroomerProfileActivity().handleRequest(request);
                    } catch (InvalidAttributeValueException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}
