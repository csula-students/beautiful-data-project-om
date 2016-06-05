package edu.csula.datascience.acquisition;

import edu.csula.datascience.acquisition.CrimeModel.Location;

public class Crime {

	public  String  _id;
    public  boolean arrest;
    public  String beat;
    public  String block;
    public  String case_number;
    public  String community_area;
    public  String date;
    public  String description;
    public  String district;
    public  boolean domestic;
    public  String fbi_code;
    public  String id;
    public  String iucr;
    public  String latitude;
    public  Location location;
    public  String location_description;
    public  String longitude;
    public  String primary_type;
    public  String updated_on;
    public  String ward;
    public  String x_coordinate;
    public  String y_coordinate;
    public  String year;
    
    
    
    
	public Crime() {
		super();
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public boolean isArrest() {
		return arrest;
	}
	public void setArrest(boolean arrest) {
		this.arrest = arrest;
	}
	public String getBeat() {
		return beat;
	}
	public void setBeat(String beat) {
		this.beat = beat;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getCase_number() {
		return case_number;
	}
	public void setCase_number(String case_number) {
		this.case_number = case_number;
	}
	public String getCommunity_area() {
		return community_area;
	}
	public void setCommunity_area(String community_area) {
		this.community_area = community_area;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public boolean isDomestic() {
		return domestic;
	}
	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}
	public String getFbi_code() {
		return fbi_code;
	}
	public void setFbi_code(String fbi_code) {
		this.fbi_code = fbi_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIucr() {
		return iucr;
	}
	public void setIucr(String iucr) {
		this.iucr = iucr;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLocation_description() {
		return location_description;
	}
	public void setLocation_description(String location_description) {
		this.location_description = location_description;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPrimary_type() {
		return primary_type;
	}
	public void setPrimary_type(String primary_type) {
		this.primary_type = primary_type;
	}
	public String getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getX_coordinate() {
		return x_coordinate;
	}
	public void setX_coordinate(String x_coordinate) {
		this.x_coordinate = x_coordinate;
	}
	public String getY_coordinate() {
		return y_coordinate;
	}
	public void setY_coordinate(String y_coordinate) {
		this.y_coordinate = y_coordinate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
    
    
    
}
