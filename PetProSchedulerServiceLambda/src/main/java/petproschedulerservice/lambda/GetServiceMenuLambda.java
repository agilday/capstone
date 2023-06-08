package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.GetServiceMenuRequest;
import petproschedulerservice.activity.results.GetServiceMenuResult;

public class GetServiceMenuLambda extends LambdaActivityRunner<GetServiceMenuRequest, GetServiceMenuResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetServiceMenuRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetServiceMenuRequest> input, Context context) {

        return super.runActivity(
                () -> input.fromPath(path ->
                        GetServiceMenuRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetServiceMenuActivity().handleRequest());
    }
}
