package pl.sda.library.domain.model;


public enum OwnershipStatus {
    HAVE("Mam"), WONT("Chce"), READ_ONLY("Tylko czytam");

    private String description;

    OwnershipStatus(String description) {

        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
