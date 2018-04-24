package edu.pitt.spotify.rest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.pitt.spotify.utils.DbUtilities;

/**
 * Servlet implementation class get_albums
 */
@WebServlet("/api/get_albums")
public class get_albums extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_albums() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("text/html");
		response.setContentType("application/json");
	

		//want to search for albums by their title
		String searchTerm = "";
		String sql = "";
		//JSONObject searchResults = new JSONObject();
		final int RESULTS_LIMIT=100;
	
		if(request.getParameter("searchTerm") !=null) {
			searchTerm = request.getParameter("searchTerm");
			if(!searchTerm.equals("")) {
				
				try {
					sql = "SELECT * FROM album WHERE title LIKE '" + searchTerm + "%';";
					JSONArray albumList = new JSONArray();
					
					DbUtilities db = new DbUtilities();
					ResultSet rs = db.getResultSet(sql);
					while(rs.next()) {
						JSONObject album = new JSONObject();
						album.put("album_id", rs.getString("album_id"));
						album.put("title", rs.getString("title"));
						album.put("release_date", rs.getString("release_date"));
						album.put("cover_image_path", rs.getString("cover_image_path"));
						album.put("recording_company_name", rs.getString("recording_company_name"));
						album.put("number_of_tracks", rs.getInt("number_of_tracks"));
						album.put("PMRC_rating", rs.getString("PMRC_rating"));
						album.put("length", rs.getDouble("length"));
						albumList.put(album);
					
					}//end of while loop
					
					response.getWriter().write(albumList.toString());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//end of inner if
		}//end of outer if
		
	}//end of doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
