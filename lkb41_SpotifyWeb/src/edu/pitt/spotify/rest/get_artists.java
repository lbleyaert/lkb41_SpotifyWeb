package edu.pitt.spotify.rest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import edu.pitt.spotify.utils.DbUtilities;

/**
 * Servlet implementation class get_artists
 */
@WebServlet("/api/get_artists")
public class get_artists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_artists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("text/html");
		response.setContentType("application/json");
		
		String searchTerm = "";
		String sql = "";
		final int RESULTS_LIMIT=100;
		
		if(request.getParameter("searchTerm") != null) {
			searchTerm = request.getParameter("searchTerm");
			if(!searchTerm.equals("")) {
				
				try {
								
				sql= "SELECT * FROM artist WHERE first_name LIKE '" + searchTerm + "%' ";
				sql += "OR last_name LIKE '" + searchTerm + "%' ";
				sql += "OR band_name LIKE '" + searchTerm + "%';";
					JSONArray artistList = new JSONArray();
					
					DbUtilities db = new DbUtilities();
					ResultSet rs = db.getResultSet(sql);
					while(rs.next()) {
						JSONObject artist = new JSONObject();
						artist.put("artist_id", rs.getString("artist_id"));
						artist.put("first_name", rs.getString("first_name"));
						artist.put("last_name", rs.getString("last_name"));
						artist.put("band_name", rs.getString("band_name"));
						artist.put("bio", rs.getString("bio"));
						artistList.put(artist);
					}
					
					//response.getWriter().write(sql);
					response.getWriter().write(artistList.toString());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
			}//end of inner if statement
		}//end of outer if
		
		
		/* FOLLOWING CODE IS FOR RETURNING SONG INFO RELATED TO ARTIST
		if(request.getParameter("searchTerm") != null) {
			searchTerm = request.getParameter("searchTerm");
			if(!searchTerm.equals("")) {
			 sql = "SELECT artist.first_name, artist.last_name, artist.band_name, song.title, song.length ";
			 sql += "FROM artist JOIN song_artist ON artist_id = fk_artist_id JOIN song ON song_id = fk_song_id ";
			 sql += "WHERE artist.first_name LIKE '" + searchTerm + "%' ";
			 sql += "OR artist.last_name LIKE '" + searchTerm + "%' ";
			 sql += "OR artist.band_name LIKE '" + searchTerm + "%' ";
			 sql += "OR song.title LIKE '" + searchTerm + "%';";	
			}
		}
		
		
		//if it doesn't find any specific song/artist pairs, it'll return all of them
		if(sql.equals("")) {
			sql = "SELECT artist.first_name, artist.last_name, artist.band_name, song.title, song.length ";
			sql += "FROM artist JOIN song_artist ON artist_id = fk_artist_id JOIN song ON song_id = fk_song_id;";
		} 

		*/
		//response.getWriter().write(sql);
		
	/*	
		JSONArray returnList = new JSONArray();
		
		try {
			DbUtilities db = new DbUtilities();
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				JSONObject searchReturn = new JSONObject();
				searchReturn.put("first_name", rs.getString("first_name"));
				searchReturn.put("last_name", rs.getString("last_name"));
				searchReturn.put("band_name", rs.getString("band_name"));
				searchReturn.put("title", rs.getString("title"));
				searchReturn.put("length", rs.getDouble("length"));
				returnList.put(searchReturn);
				
				//response.getWriter().write("JSON object created");
			}
			
			
			response.getWriter().write(returnList.toString());
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
