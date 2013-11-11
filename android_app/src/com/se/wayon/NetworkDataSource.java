package com.se.wayon;



import java.util.List;

import org.json.JSONObject;

public abstract class NetworkDataSource extends DataSource {

    
    protected List<Marker> markersCache = null;
    
   
    
    public abstract List<Marker> parse(JSONObject json); 

    public List<Marker> getMarkers() {
        return markersCache;
    }
    

}