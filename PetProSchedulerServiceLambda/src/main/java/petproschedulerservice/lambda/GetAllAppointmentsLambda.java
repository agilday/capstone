package petproschedulerservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.GetAllAppointmentsRequest;
import petproschedulerservice.activity.results.GetAllAppointmentsResult;
import petproschedulerservice.lambda.AuthenticatedLambdaRequest;
import petproschedulerservice.lambda.LambdaActivityRunner;
import petproschedulerservice.lambda.LambdaResponse;

public class GetAllAppointmentsLambda  extends LambdaActivityRunner<GetAllAppointmentsRequest, GetAllAppointmentsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllAppointmentsRequest>, LambdaResponse> {

    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllAppointmentsRequest> input, Context context) {

        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllAppointmentsRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllAppointmentsActivity().handleRequest());
    }


}
