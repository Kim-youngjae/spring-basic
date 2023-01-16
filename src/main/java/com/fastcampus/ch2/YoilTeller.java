package com.fastcampus.ch2;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller // 원격 호출이 가능한 프로그램임을 의미
@RequestMapping("/yoil") // 이걸 붙이면 모든 메서드를 호출할 때 /yoil/** 매핑된 메서드 이렇게 사용해야 함
public class YoilTeller { // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
        @RequestMapping("/getYoil")
        public void test() { // public이든 private이든 호출이 된다 reflection API에 의해 호출됨 @RequestMapping이 되있어 url이름으로 호출 됨
                System.out.println("test() is called");
                // return 타입이 void이면 호출하는 뷰가 없다. 하지만 이 경우 서버 자체에서 매핑된 [url].jsp의 뷰를 호출함
        } // 컨트롤러 안에는 몇개의 메서드가 있어도 상관이 없다.

        @RequestMapping("/getYoil")
        public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
                // 1. 입력
                String year = request.getParameter("year");
                String month = request.getParameter("month");
                String day = request.getParameter("day");

                // 2. 작업
                int yyyy = Integer.parseInt(year);
                int mm = Integer.parseInt(month);
                int dd = Integer.parseInt(day);

                Calendar cal = Calendar.getInstance();
                cal.set(yyyy, mm - 1, dd);

                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일, 2:월요일 ...
                char yoil = " 일월화수목금토".charAt(dayOfWeek);

                // 3. 출력
                response.setContentType("text/html");
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter(); // response객체에서 브라우져로의 출력 스트림을 얻는다.
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println(year + "년 " + month + "월 " + day + "일은 ");
                out.println(yoil + "요일입니다.");
                out.println("</body>");
                out.println("</html>");
        }
}