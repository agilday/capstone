package dependency;

import activity.*;
import dagger.Component;

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
    UpdateProfileActivity provideUpdateProfileActivity();

    CreateProfileActivity provideCreateProfileActivity();

    GetAllProfilesActivity provideGetAllProfilesActivity();

    GetProfileActivity provideGetProfileActivity();

    CreateAppointmentActivity provideCreateAppointmentActivity();

    GetAppointmentsActivity provideGetAppointmentsActivity();

    GetServiceMenuActivity provideGetServiceMenuActivity();

    UpdateServiceMenuActivity provideUpdateServiceMenuActivity();


}
