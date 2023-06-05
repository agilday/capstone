package petproschedulerservice.lambda;



import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import petproschedulerservice.activity.requests.CreateAppointmentRequest;
import petproschedulerservice.activity.results.CreateAppointmentResult;

public class CreateAppointmentLambda extends LambdaActivityRunner<CreateAppointmentRequest, CreateAppointmentResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateAppointmentRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateAppointmentRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateAppointmentRequest unauthenticatedRequest = input.fromBody(CreateAppointmentRequest.class);
                    return CreateAppointmentRequest.builder()
                            .withClient(unauthenticatedRequest.getClient())
                            .withDateTime(unauthenticatedRequest.getDateTime())
                            .withPet(unauthenticatedRequest.getPet())
                            .withService(unauthenticatedRequest.getService())
                            .build();

                    },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateAppointmentActivity().handleRequest(request)
        );
    }
}
