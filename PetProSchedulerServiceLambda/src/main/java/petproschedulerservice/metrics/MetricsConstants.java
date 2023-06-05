package petproschedulerservice.metrics;

public class MetricsConstants {

    /**
     * Constant values for use with metrics.
     */
        public static final String GETPROFILE_PROFILENOTFOUND_COUNT = "GetProfile.ProfileNotFoundException.Count";
        public static final String CREATEPROFILE_INVALIDATTRIBUTE_COUNT = "CreateProfile.InvalidAttributeException.Count";
        public static final String DELETEPROFILE_PROFILENOTFOUND_COUNT = "DeleteProfile.ProfileNotFoundException.Count";
        public static final String DELETEAPPOINTMENT_APPOINTMENTNOTFOUND_COUNT =
                "DeleteAppointment.AppointmentNotFoundException.Count";
        public static final String UPDATEPROFILE_PROFILENOTFOUND_COUNT =
                "UpdateProfile.ProfileNotFoundException.Count";
        public static final String UPDATEPROFILE_INVALIDATTRIBUTE_COUNT =
                "UpdateProfile.InvalidAttributeException.Count";
        public static final String SERVICE = "Service";
        public static final String SERVICE_NAME = "petproscheduler";
        public static final String NAMESPACE_NAME = "U3/petproscheduler";
        public static final String GETAPPOINTMENT_APPOINTMENTNOTFOUND_COUNT = "GetAppointment.AppointmentNotFoundException";
}

