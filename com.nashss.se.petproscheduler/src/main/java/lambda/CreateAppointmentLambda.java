package lambda;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import requests.CreateAppointmentRequest;
import results.CreateAppointmentResult;

import com.amazonaws.services.lambda.runtime.Context;

public class CreateAppointmentLambda extends LambdaActivityRunner<CreateAppointmentRequest, CreateAppointmentResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateAppointmentRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateAppointmentRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateAppointmentRequest unauthenticatedRequest = input.fromBody(CreateAppointmentRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateAppointmentRequest.builder()
                                    .withId(unauthenticatedRequest.getId())
                                    .withClient(unauthenticatedRequest.getClient())
                                    .withCustomerId(claims.get("email"))
                                    .withCustomerName(claims.get("name"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateAppointmentActivity().handleRequest(request)
        );
    }
}
