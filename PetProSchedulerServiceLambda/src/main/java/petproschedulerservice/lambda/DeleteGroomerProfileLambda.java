package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.DeleteGroomerProfileRequest;
import petproschedulerservice.activity.results.DeleteGroomerProfileResult;

public class DeleteGroomerProfileLambda extends LambdaActivityRunner<DeleteGroomerProfileRequest, DeleteGroomerProfileResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteGroomerProfileRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteGroomerProfileRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteGroomerProfileRequest.builder()
                                .withLname(path.get("lname"))
                                .build()),
                (request,serviceComponent)->
                        serviceComponent.provideDeleteGroomerProfileActivity().handleRequest(request));
    }

}
