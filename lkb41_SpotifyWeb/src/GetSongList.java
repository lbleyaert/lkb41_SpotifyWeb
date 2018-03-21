

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



/**
 * Servlet implementation class GetSongList
 */
@WebServlet("/GetSongList")
public class GetSongList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSongList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//setting servlet context to json --explicitly saying data that comes back is json
		response.setContentType("application/json");
		
		
		String title = "";
		
		/*if(request.getParameter("title") != null) {
			
		}*/

			
		

		try {
					DbUtilities db = new DbUtilities();
		String sql = "SELECT * FROM song ";
		if(! title .equals("")) {
			sql += " WHERE title LIKE '%" + title + "%'";
		}
		
		sql += " ORDER BY title ASC;";
				
		
		ResultSet rs = db.getResultSet(sql);
		
	
		JSONArray songList = new JSONArray();
		
		//next, iterating through data set - going to keep adding key value pairs to JSON object song
		while(rs.next()) {
			JSONObject song = new JSONObject();
			song.put("id", rs.getString("song_id"));
			song.put("title", rs.getString("title"));
			
			//add the song to the array of songs
			songList.put(song);
		}
		
		response.getWriter().write(songList.toString());	
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
