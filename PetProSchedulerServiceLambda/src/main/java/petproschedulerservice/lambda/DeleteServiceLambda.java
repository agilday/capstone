package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.DeleteServiceRequest;
import petproschedulerservice.activity.results.DeleteServiceResult;

public class DeleteServiceLambda extends LambdaActivityRunner<DeleteServiceRequest, DeleteServiceResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteServiceRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteServiceRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteServiceRequest.builder()
                                .withTitle(path.get("title"))
                                .build()),
                (request,serviceComponent)->
                        serviceComponent.provideDeleteServiceActivity().handleRequest(request));
    }
}
