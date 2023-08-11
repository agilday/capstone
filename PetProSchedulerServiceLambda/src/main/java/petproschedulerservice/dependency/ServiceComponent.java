package petproschedulerservice.dependency;


import com.amazonaws.services.lambda.runtime.RequestHandler;
import dagger.Component;
import petproschedulerservice.activity.*;
import petproschedulerservice.activity.requests.CreateServiceRequest;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the PetPro Scheduler.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     */
    UpdateClientProfileActivity provideUpdateClientProfileActivity();

    CreateClientProfileActivity provideCreateClientProfileActivity();

    GetAllClientProfilesActivity provideGetAllClientProfilesActivity();

    GetClientProfileActivity provideGetClientProfileActivity();

    CreateGroomerProfileActivity provideCreateGroomerProfileActivity();

    CreateAppointmentActivity provideCreateAppointmentActivity();

    GetAllAppointmentsActivity provideGetAllAppointmentsActivity();

    GetServiceMenuActivity provideGetServiceMenuActivity();

    UpdateServiceActivity provideUpdateServiceActivity();

    CreateServiceActivity provideCreateServiceActivity();
    DeleteAppointmentActivity provideDeleteAppointmentActivity();
    DeleteServiceActivity provideDeleteServiceActivity();

}
