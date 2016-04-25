package edu.csula.datascience.acquisition;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/*public class CrimeModel
{
    private String beat;

    private String case_number;

    private CrimeLocation crimeLocation;

    private String x_coordinate;

    private String primary_type;

    private String block;

    private String location_description;

    private String iucr;

    private String date;

    private String domestic;

    private String id;

    private String ward;

    private String _id;

    private String arrest;

    private String description;

    private String y_coordinate;

    private String updated_on;

    private String fbi_code;

    private String year;

    private String longitude;

    private String community_area;

    private String latitude;

    private String district;

    public String getBeat ()
    {
        return beat;
    }

    public void setBeat (String beat)
    {
        this.beat = beat;
    }

    public String getCase_number ()
    {
        return case_number;
    }

    public void setCase_number (String case_number)
    {
        this.case_number = case_number;
    }



    public String getX_coordinate ()
    {
        return x_coordinate;
    }

    public void setX_coordinate (String x_coordinate)
    {
        this.x_coordinate = x_coordinate;
    }

    public String getPrimary_type ()
    {
        return primary_type;
    }

    public void setPrimary_type (String primary_type)
    {
        this.primary_type = primary_type;
    }

    public String getBlock ()
    {
        return block;
    }

    public void setBlock (String block)
    {
        this.block = block;
    }

    public String getLocation_description ()
    {
        return location_description;
    }

    public void setLocation_description (String location_description)
    {
        this.location_description = location_description;
    }

    public String getIucr ()
    {
        return iucr;
    }

    public void setIucr (String iucr)
    {
        this.iucr = iucr;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getDomestic ()
    {
        return domestic;
    }

    public void setDomestic (String domestic)
    {
        this.domestic = domestic;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getWard ()
    {
        return ward;
    }

    public void setWard (String ward)
    {
        this.ward = ward;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getArrest ()
    {
        return arrest;
    }

    public void setArrest (String arrest)
    {
        this.arrest = arrest;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getY_coordinate ()
    {
        return y_coordinate;
    }

    public void setY_coordinate (String y_coordinate)
    {
        this.y_coordinate = y_coordinate;
    }

    public String getUpdated_on ()
    {
        return updated_on;
    }

    public void setUpdated_on (String updated_on)
    {
        this.updated_on = updated_on;
    }

    public String getFbi_code ()
    {
        return fbi_code;
    }

    public void setFbi_code (String fbi_code)
    {
        this.fbi_code = fbi_code;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getCommunity_area ()
    {
        return community_area;
    }

    public void setCommunity_area (String community_area)
    {
        this.community_area = community_area;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }
    
    

    public CrimeLocation getCrimeLocation() {
		return crimeLocation;
	}

	public void setCrimeLocation(CrimeLocation crimeLocation) {
		this.crimeLocation = crimeLocation;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [beat = "+beat+", case_number = "+case_number+", crimeLocation = "+crimeLocation+", x_coordinate = "+x_coordinate+", primary_type = "+primary_type+", block = "+block+", location_description = "+location_description+", iucr = "+iucr+", date = "+date+", domestic = "+domestic+", id = "+id+", ward = "+ward+", _id = "+_id+", arrest = "+arrest+", description = "+description+", y_coordinate = "+y_coordinate+", updated_on = "+updated_on+", fbi_code = "+fbi_code+", year = "+year+", longitude = "+longitude+", community_area = "+community_area+", latitude = "+latitude+", district = "+district+"]";
    }
}
*/


public final class CrimeModel {
    public final _id _id;
    public final boolean arrest;
    public final String beat;
    public final String block;
    public final String case_number;
    public final String community_area;
    public final String date;
    public final String description;
    public final String district;
    public final boolean domestic;
    public final String fbi_code;
    public final String id;
    public final String iucr;
    public final String latitude;
    public final Location location;
    public final String location_description;
    public final String longitude;
    public final String primary_type;
    public final String updated_on;
    public final String ward;
    public final String x_coordinate;
    public final String y_coordinate;
    public final String year;
    
    

    
	@JsonCreator
    public CrimeModel(@JsonProperty("_id") _id _id, @JsonProperty("arrest") boolean arrest, @JsonProperty("beat") String beat, @JsonProperty("block") String block, @JsonProperty("case_number") String case_number, @JsonProperty("community_area") String community_area, @JsonProperty("date") String date, @JsonProperty("description") String description, @JsonProperty("district") String district, @JsonProperty("domestic") boolean domestic, @JsonProperty("fbi_code") String fbi_code, @JsonProperty("id") String id, @JsonProperty("iucr") String iucr, @JsonProperty("latitude") String latitude, @JsonProperty("location") Location location, @JsonProperty("location_description") String location_description, @JsonProperty("longitude") String longitude, @JsonProperty("primary_type") String primary_type, @JsonProperty("updated_on") String updated_on, @JsonProperty("ward") String ward, @JsonProperty("x_coordinate") String x_coordinate, @JsonProperty("y_coordinate") String y_coordinate, @JsonProperty("year") String year){
        this._id = _id;
        this.arrest = arrest;
        this.beat = beat;
        this.block = block;
        this.case_number = case_number;
        this.community_area = community_area;
        this.date = date;
        this.description = description;
        this.district = district;
        this.domestic = domestic;
        this.fbi_code = fbi_code;
        this.id = id;
        this.iucr = iucr;
        this.latitude = latitude;
        this.location = location;
        this.location_description = location_description;
        this.longitude = longitude;
        this.primary_type = primary_type;
        this.updated_on = updated_on;
        this.ward = ward;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.year = year;
    }

    public static final class _id {
        public final String $oid;

        @JsonCreator
        public _id(@JsonProperty("$oid") String $oid){
            this.$oid = $oid;
        }
    }

    public static final class Location {
        public final String type;
        public final double[] coordinates;

        @JsonCreator
        public Location(@JsonProperty("type") String type, @JsonProperty("coordinates") double[] coordinates){
            this.type = type;
            this.coordinates = coordinates;
        }

		public String getType() {
			return type;
		}

		public double[] getCoordinates() {
			return coordinates;
		}
    }
    
    @Override
    public String toString()
    {
        return "ClassPojo [beat = "+beat+", case_number = "+case_number+", crimeLocation = "+location+", x_coordinate = "+x_coordinate+", primary_type = "+primary_type+", block = "+block+", location_description = "+location_description+", iucr = "+iucr+", date = "+date+", domestic = "+domestic+", id = "+id+", ward = "+ward+", _id = "+_id+", arrest = "+arrest+", description = "+description+", y_coordinate = "+y_coordinate+", updated_on = "+updated_on+", fbi_code = "+fbi_code+", year = "+year+", longitude = "+longitude+", community_area = "+community_area+", latitude = "+latitude+", district = "+district+"]";
    }
    
}