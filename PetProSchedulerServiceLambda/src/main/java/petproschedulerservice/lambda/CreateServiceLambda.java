package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.CreateServiceRequest;
import petproschedulerservice.activity.results.CreateServiceResult;


public class CreateServiceLambda extends LambdaActivityRunner<CreateServiceRequest, CreateServiceResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateServiceRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateServiceRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateServiceRequest unauthenticatedRequest = input.fromBody(CreateServiceRequest.class);
                    return CreateServiceRequest.builder()
                            .withTitle(unauthenticatedRequest.getTitle())
                            .withDescription(unauthenticatedRequest.getDescription())
                            .build();

                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateServiceActivity().handleRequest(request)
        );
    }
}
