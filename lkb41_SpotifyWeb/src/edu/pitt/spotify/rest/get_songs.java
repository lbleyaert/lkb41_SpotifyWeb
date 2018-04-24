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
 * Servlet implementation class get_songs
 */
@WebServlet("/api/get_songs")
public class get_songs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_songs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//response.setContentType("text/html");
		response.setContentType("application/json");
		
		//want to search for songs by title
		String searchTerm = "";
		String sql = "";
		//JSONObject searchResults = new JSONObject();
		final int RESULTS_LIMIT=100;
		
		if(request.getParameter("searchTerm") !=null) {
			searchTerm = request.getParameter("searchTerm");
			if(!searchTerm.equals("")) {
				
				try {
					sql = "SELECT * FROM song WHERE title LIKE '" + searchTerm + "%';";
					JSONArray songList = new JSONArray();
					
					DbUtilities db = new DbUtilities();
					ResultSet rs = db.getResultSet(sql);
					while(rs.next()) {
						JSONObject song = new JSONObject();
						song.put("song_id", rs.getString("song_id"));
						song.put("title", rs.getString("title"));
						song.put("release_date", rs.getString("release_date"));
						song.put("record_date", rs.getString("record_date"));
						song.put("length", rs.getDouble("length"));
						song.put("file_path", rs.getString("file_path"));
						songList.put(song);
					}
					
					response.getWriter().write(songList.toString());
				
					// Store song list in searchResults JSON object
				//	searchResults.put("songs", songList);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block8
					e.printStackTrace();
				}
			
			
			
			}
		}
		//response.getWriter().write(sql);
		
		
	}//end of doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
