package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.GetAllClientProfilesRequest;
import petproschedulerservice.activity.results.GetAllClientProfilesResult;

public class GetAllClientProfilesLambda  extends LambdaActivityRunner<GetAllClientProfilesRequest, GetAllClientProfilesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllClientProfilesRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllClientProfilesRequest> input, Context context) {

        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllClientProfilesRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllClientProfilesActivity().handleRequest());
    }


}
