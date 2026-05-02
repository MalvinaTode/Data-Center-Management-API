package org.example;
/* Clasa Location folosind Builder , sintaxa luata din labul de patternuri, lab11
*/
public class Location {
    private String country; //camp obligatoriu

    //campuri optionale
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;

    //constructor privat pentru Builder
    private Location(Builder builder) {
        this.country = builder.country;
        this.city = builder.city;
        this.address = builder.address;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    //constructor public pentru compatibilitate
    public Location(String country) {
        this.country = country;
    }

    //Builder pattern
    public static class Builder {
        private String country;  //obligatoriu
        private String city;
        private String address;
        private Double latitude;
        private Double longitude;

        public Builder(String country) {
            this.country = country;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }

    //gettere + settere
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}