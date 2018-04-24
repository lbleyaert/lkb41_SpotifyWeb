

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pitt.spotify.utils.DbUtilities;

/**
 * Servlet implementation class SongList
 */
@WebServlet("/SongList")
public class SongList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String songTitle = "";
		response.setContentType("text/html");
		
		if(request.getParameter("songTitle") != null) {
			songTitle = request.getParameter("songTitle");
			
			
			try {
				DbUtilities db = new DbUtilities();
				String sql = "SELECT * FROM song WHERE title LIKE '%" + songTitle + "%';";
				response.getWriter().println(sql);
				ResultSet rs = db.getResultSet(sql);
				String tbl = "<table class='tblList' border='1' cellpadding='5' cellspacing='0'>";
				tbl += "<tr>";
				tbl += "<th>ID</th>";
				tbl += "<th>Song Title</th>";
				tbl += "</tr>";
				while(rs.next()){
					tbl += "<tr>";
					tbl += "<td>" + rs.getString("song_id") + "</td>";
					tbl += "<td>" + rs.getString("title") + "</td>";
					tbl += "</tr>";
				}
				
				tbl += "</table>";
				response.getWriter().write(tbl);
				//response.getWriter().write("<a href='SessionTest'>Session Test</a>");
				
				
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// response.getWriter().println(songTitle);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
