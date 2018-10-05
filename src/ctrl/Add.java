package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Brain;

@WebServlet("/Add.do")
public class Add extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession s = request.getSession();
		System.out.println("My ID is: "+  s.getId());
		if (s.getAttribute("history") == null)
		{
			s.setAttribute("history", new StringBuffer());
		}	
		
		//----------------------------Context-wide:
		if (this.getServletContext().getAttribute("history") == null) {
			this.getServletContext().setAttribute("history", new StringBuffer());
		}
		

		if (request.getParameter("doit") == null)
		{
			this.getServletContext().getRequestDispatcher("/Form.html").forward(request, response);
		} else
		{
			Writer out = response.getWriter();
			response.setContentType("text/plain");
			
			out.write("max Interval = "+s.getMaxInactiveInterval()+"\n");

			Brain b = Brain.getInstance(); // new Brain();
			String x = request.getParameter("x");
			String y = request.getParameter("y");
			((StringBuffer) s.getAttribute("history")).append(x+", "+y+"</br>");
			
			((StringBuffer) this.getServletContext().getAttribute("history")).append(x+", "+y+"</br>");
			
			try
			{
				String result = b.doAdd(x, y);
				out.write("The answer is:\n" + result);
			} catch (Exception e)
			{
				out.write(e.getMessage());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
