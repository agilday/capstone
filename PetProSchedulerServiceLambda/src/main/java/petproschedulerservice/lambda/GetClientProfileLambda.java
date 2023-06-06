package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.GetClientProfileRequest;
import petproschedulerservice.activity.results.GetClientProfileResult;

public class GetClientProfileLambda extends LambdaActivityRunner<GetClientProfileRequest, GetClientProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetClientProfileRequest>, LambdaResponse> {


    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetClientProfileRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetClientProfileRequest.builder()
                                .withId(path.get("id"))
                                .build()),
                (request,serviceComponent)->
                        serviceComponent.provideGetClientProfileActivity().handleRequest(request));
    }
}
