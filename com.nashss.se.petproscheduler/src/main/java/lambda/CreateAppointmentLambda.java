package lambda;

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
                                    .withName(unauthenticatedRequest.getName())
                                    .withTags(unauthenticatedRequest.getTags())
                                    .withCustomerId(claims.get("email"))
                                    .withCustomerName(claims.get("name"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateAppointmentActivity().handleRequest(request)
        );
    }
}
