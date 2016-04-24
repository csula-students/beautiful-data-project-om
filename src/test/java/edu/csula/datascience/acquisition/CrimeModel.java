package edu.csula.datascience.acquisition;

public class CrimeModel
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
