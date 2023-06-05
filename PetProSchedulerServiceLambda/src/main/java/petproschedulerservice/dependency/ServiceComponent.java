package petproschedulerservice.dependency;


import dagger.Component;
import petproschedulerservice.activity.*;

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
 //   UpdateProfileActivity provideUpdateProfileActivity();

    CreateClientProfileActivity provideCreateClientProfileActivity();

  //  GetAllProfilesActivity provideGetAllProfilesActivity();

  //  GetProfileActivity provideGetProfileActivity();

    CreateAppointmentActivity provideCreateAppointmentActivity();

  //  GetAppointmentsActivity provideGetAppointmentsActivity();

  //  GetServiceMenuActivity provideGetServiceMenuActivity();

  //  UpdateServiceMenuActivity provideUpdateServiceMenuActivity();


}
