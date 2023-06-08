package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.UpdateServiceRequest;
import petproschedulerservice.activity.results.UpdateServiceResult;

public class UpdateServiceLambda
        extends LambdaActivityRunner<UpdateServiceRequest, UpdateServiceResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateServiceRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateServiceRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateServiceRequest unauthenticatedRequest = input.fromBody(UpdateServiceRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateServiceRequest.builder()
                                    .withTitle(unauthenticatedRequest.getTitle())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateServiceActivity().handleRequest(request)
        );
    }
}
