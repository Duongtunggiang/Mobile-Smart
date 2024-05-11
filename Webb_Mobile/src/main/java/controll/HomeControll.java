package controll;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import dao.DAO;
import entity.Product;

@WebServlet("/Home")
public class HomeControll extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeControll() {
        super();

    }
	public void init(ServletConfig config) throws ServletException {
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		boolean isLoggedIn = checkLoginStatus(request);
		 
		 if (isLoggedIn) {
			String action = request.getParameter("action");
			if (action == null) {
	            action = "list1";
			}
			switch(action) {
				case "new":
	//				showNewForm(request, response);
					break;
//				case "redirect":
//	                roleBasedRedirect(request, response);
//	                break;
				case "productDetails":
//				    productDetails(request, response);
				    break;
				default:
					home(request, response);
					break;
			} 
		 } else {
		        // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
			 String action = request.getParameter("action");
		        if (action == null) {
		            action = "list1";
		        }
		        switch(action) {
		            case "new":
		                // Do something
		                break;
		            case "productDetails":
//		                productDetails(request, response);
		                break;
		            case "customer":
//		            	customer(request,response);
		            	break;
//		            case "changeAccount":
//		                changeAccount(request, response);
//		                break;
		            default:
		                home(request, response);
		                break;
		        }
		 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		doGet(request, response);
	}
	private void home(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		
		DAO dao = new DAO();
		List<Product> list = dao.getAllProduct();
		request.setAttribute("listP", list);
		
		DAO top1 = new DAO();
		List<Product> top = dao.getTop1();
		request.setAttribute("Top1", top);
		///Lam tiep
		DAO cate = new DAO();
//		List<Product> cid = cate.getProductsByCategoryId(0);
//		request.setAttribute("Cate", cid);
		
		request.getRequestDispatcher("Home.jsp").forward(request, response);
		
	}
	private boolean checkLoginStatus(HttpServletRequest request) {
		 
	    HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    return username != null; 
	}
}
