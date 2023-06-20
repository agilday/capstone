package petproschedulerservice.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.DeleteAppointmentRequest;
import petproschedulerservice.activity.results.DeleteAppointmentResult;

public class DeleteAppointmentLambda extends LambdaActivityRunner<DeleteAppointmentRequest, DeleteAppointmentResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteAppointmentRequest>, LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteAppointmentRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteAppointmentRequest.builder()
                                .withId(path.get("id"))
                                .build()),
                (request,serviceComponent)->
                        serviceComponent.provideDeleteAppointmentActivity().handleRequest(request));
    }
}
