package com.kh.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청시 전달한 값들은 request의 parameter에 담겨있다.
        // 다만, POST방식의 요청의 경우 값을 뽑기 전에 utf-8방식으로 인코딩 설정을 해야한다.
        //      POST방식의 기본 인코딩 설정은 ISO-8859-1이기 때문.
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name"); // "신용수", ""(텍스트 상자가 빈 경우 빈 문자열이 넘어간다)
        String gender = request.getParameter("gender"); // "M"/"F"/null => 라디오 버튼 체크 안한경우
        int age = Integer.parseInt(request.getParameter("age")); // "20", ""(값이 없는 경우, NumberFormatException 발생)

        String[] foods = request.getParameterValues("food"); // ["한식","일식"] / null (체크 없다면 null이 반환)

        // 응답페이지 출력..

        // GET방식에서 다뤄본 내용은 순수 Servlet만 이용한 방식 ==> java코드내에 html을 작성..
        // JSP (Java Server Page) : html내에 Java코드를 쓸 수 있는 기술

        // 응답페이지를 만드는 과정을 jsp에게 위임(떠넘기기)할 예정

        // 단, 그 응답화면(jsp)에서 필요로 하는 데이터들은 request객체에 담아서 전달해줘야한다.
        // => request에 attribute라는 영역에 데이터 담아서 전달해주기(키 - 벨류 세트로)
        // request.setAttribute("키", "벨류");
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        request.setAttribute("gender", gender);
        request.setAttribute("foods", foods);

        // 위임 시 필요한 객체 : RequestDispatcher 객체
        // 1) 응답하고자 하는 뷰 선택후 생성
        RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");

        // 2) 포워딩
        view.forward(request, response);



    }
}
