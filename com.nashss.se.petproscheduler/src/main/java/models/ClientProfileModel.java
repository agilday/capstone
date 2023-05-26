package models;

import java.util.List;
import java.util.Objects;

public class ClientProfileModel {
    private final String id;
    private final String name;
    private final String customerId;
    private final String customerName;
    private final int songCount;
    private final List<String> tags;

    private AppointmentModel(String id, String name, String customerId,
                             String customerName, int songCount, List<String> tags) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.customerName = customerName;
        this.songCount = songCount;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getSongCount() {
        return songCount;
    }

    public List<String> getTags() {
        return copyToList(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppointmentModel that = (AppointmentModel) o;

        return songCount == that.songCount &&
                Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, customerId, customerName, songCount, tags);
    }

    //CHECKSTYLE:OFF:Builder
    public static AppointmentModel.Builder builder() {
        return new AppointmentModel.Builder();
    }

    public String getCustomerName() {
        return customerName;
    }

    public static class Builder {
        private String id;
        private String name;
        private String customerId;
        private String customerName;
        private int songCount;
        private List<String> tags;

        public AppointmentModel.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public AppointmentModel.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public AppointmentModel.Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public AppointmentModel.Builder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public AppointmentModel.Builder withSongCount(int songCount) {
            this.songCount = songCount;
            return this;
        }

        public AppointmentModel.Builder withTags(List<String> tags) {
            this.tags = copyToList(tags);
            return this;
        }

        public PlaylistModel build() {
            return new PlaylistModel(id, name, customerId, customerName, songCount, tags);
        }
    }

}
