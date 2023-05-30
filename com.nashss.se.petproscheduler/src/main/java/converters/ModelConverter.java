package converters;

import com.amazonaws.auth.profile.internal.Profile;
import dynamodb.ClientProfile;
import dynamodb.Pet;
import models.ClientProfileModel;
import models.ProfileModel;

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
     * Converts a provided AlbumTrack into a SongModel representation.
     *
     * @param albumTrack the AlbumTrack to convert to SongModel
     * @return the converted SongModel with fields mapped from albumTrack
     */
    public SongModel toSongModel(AlbumTrack albumTrack) {
        return SongModel.builder()
                .withAsin(albumTrack.getAsin())
                .withTrackNumber(albumTrack.getTrackNumber())
                .withAlbum(albumTrack.getAlbumName())
                .withTitle(albumTrack.getSongTitle())
                .build();
    }

    /**
     * Converts a list of AlbumTracks to a list of SongModels.
     *
     * @param albumTracks The AlbumTracks to convert to SongModels
     * @return The converted list of SongModels
     */
    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
        List<SongModel> songModels = new ArrayList<>();

        for (AlbumTrack albumTrack : albumTracks) {
            songModels.add(toSongModel(albumTrack));
        }

        return songModels;
    }

    /**
     * Converts a list of Playlists to a list of PlaylistModels.
     *
     * @param playlists The Playlists to convert to PlaylistModels
     * @return The converted list of PlaylistModels
     */
    public List<PlaylistModel> toPlaylistModelList(List<Playlist> playlists) {
        List<PlaylistModel> playlistModels = new ArrayList<>();

        for (Playlist playlist : playlists) {
            playlistModels.add(toPlaylistModel(playlist));
        }

        return playlistModels;
    }
}
