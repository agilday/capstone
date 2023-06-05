package petproschedulerservice.converters;

import petproschedulerservice.dynamodb.Appointment;
import petproschedulerservice.dynamodb.ClientProfile;
import petproschedulerservice.dynamodb.Pet;
import petproschedulerservice.models.AppointmentModel;
import petproschedulerservice.models.ClientProfileModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link ClientProfile} into a {@link ClientProfileModel} representation.
     *
     * @param profile the profile to convert
     * @return the converted profile
     */
    public ClientProfileModel toProfileModel(ClientProfile profile) {
        List<Pet> pets = null;
        if (profile.getPets() != null) {
            pets = new ArrayList<>(profile.getPets());
        }
        List<String> notes = null;
        if (profile.getNotes() != null) {
            notes = new ArrayList<>(profile.getNotes());
        }

        return ClientProfileModel.builder()
                .withId(profile.getId())
                .withName(profile.getName())
                .withPhone(profile.getPhone())
                .withNotes(notes)
                .withPets(pets)
                .build();
    }

    /**
     * Converts a provided Appointment into a AppointmentModel representation.
     *
     * @param appointment the Appointment to convert to AppointmentModel
     * @return the converted AppointmentModel with fields mapped from appointment
     */
    public AppointmentModel toAppointmentModel(Appointment appointment) {
        return AppointmentModel.builder()
                .withId(appointment.getId())
                .withClient(appointment.getClient())
                .withDateTime(appointment.getDateTime())
                .withPet(appointment.getPet())
                .withService(appointment.getService())
                .build();
    }
//
//    /**
//     * Converts a list of AlbumTracks to a list of SongModels.
//     *
//     * @param albumTracks The AlbumTracks to convert to SongModels
//     * @return The converted list of SongModels
//     */
//    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
//        List<SongModel> songModels = new ArrayList<>();
//
//        for (AlbumTrack albumTrack : albumTracks) {
//            songModels.add(toSongModel(albumTrack));
//        }
//
//        return songModels;
//    }
//
//    /**
//     * Converts a list of Playlists to a list of PlaylistModels.
//     *
//     * @param playlists The Playlists to convert to PlaylistModels
//     * @return The converted list of PlaylistModels
//     */
//    public List<PlaylistModel> toPlaylistModelList(List<Playlist> playlists) {
//        List<PlaylistModel> playlistModels = new ArrayList<>();
//
//        for (Playlist playlist : playlists) {
//            playlistModels.add(toPlaylistModel(playlist));
//        }
//
//        return playlistModels;
//    }
}
