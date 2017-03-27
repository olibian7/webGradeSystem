package com.academy.ssit;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


//javax.servlet.http.HttpServlet Ŭ������ ��� ���� Ŭ������ ���� Ŭ������ �ȴ�.
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8796791337729580753L;

	//�������� main() �޼ҵ� ��� doGet(), doPost() �޼ҵ尡 �ִ�.
	//�������� Ŭ���̾�Ʈ���� ��û�� ������ doGet() �Ǵ� doPost() �޼ҵ尡 �ڵ� ȣ��ȴ�.
	//����) ���� Ŭ������ ���������� �ƴϹǷ� ���������� ���� ��û �Ұ� -> ��Ĺ������ ���� �ּҿ� Ŭ���̾�Ʈ ��û �ּҸ� �����ϴ� ���� �ʿ� -> web.xml
	//doGet() �޼ҵ��� �Ű��������� request, response�� ������ ��
	@Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		System.out.println(uri);
		String methodName = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		System.out.println(methodName);
		
		Action action = new Action();
		/*if(methodName.equals("memberlist")){
			action.memberlist();
		}
		if(methodName.equals("memberinsert")){
			action.memberinsert();
		}*/
		//->�ּ� �м� ���ؼ� ���� �޼ҵ� �̸��� ������ ���� ȣ��-> invoke();
		String result = "error";
		java.lang.reflect.Method m;
		try {			
			m = Action.class.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			result = (String)m.invoke(action, request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//4�ܰ�. ��ȯ�� �м� -> ������ �̵� ����
				if (result.contains("redirect:")) {
					//5�ܰ�. ������ �̵� -> ���� �ּ�
					result = result.substring(9);
					System.out.println("�̵������� ���:"+result);
					response.sendRedirect(result+".it"); //"redirect:�����ּ�.it" -> "�����ּ�.it"
				}else if(result.contains("get:")){
					result = result.substring(4);
					System.out.println("�̵������� ���:"+result);
					response.sendRedirect(result);
				}else if(result.contains("hold")){
					
				}else {
					//5�ܰ�. ������ �̵� -> JSP ������		
					System.out.println("�̵������� ���:"+result);
					RequestDispatcher dispatcher = request.getRequestDispatcher(result+".jsp");
					dispatcher.forward(request, response);
				}
	}

	//doPost() �޼ҵ��� �Ű��������� request, response�� ������ ��
	@Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
