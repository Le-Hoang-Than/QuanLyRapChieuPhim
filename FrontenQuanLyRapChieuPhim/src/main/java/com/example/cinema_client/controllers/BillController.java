package com.example.cinema_client.controllers;

import com.example.cinema_client.constants.Api;
import com.example.cinema_client.models.BookingRequestDTO;
import com.example.cinema_client.models.JwtResponseDTO;
import com.example.cinema_client.models.ScheduleDTO;
import com.example.cinema_client.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/bill")
public class BillController {
    @PostMapping
    public String displayBillPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        // 1. Lấy danh sách ghế từ request
        String[] seats = request.getParameterValues("seats");

        // --- ĐOẠN SỬA ĐỔI CHÍNH ---
        // Kiểm tra nếu người dùng không chọn ghế nào (seats sẽ là null)
        if (seats == null || seats.length == 0) {
            ScheduleDTO schedule = (ScheduleDTO) session.getAttribute("schedule");

            // Đặt thông báo lỗi vào session để trang seats.jsp hiển thị
            session.setAttribute("bookedError", "Vui lòng chọn ít nhất một chỗ ngồi trước khi tiếp tục!");

            // Điều hướng quay trở lại trang chọn ghế với đầy đủ tham số URL
            return "redirect:/seat-selection?movieId=" + schedule.getMovie().getId() +
                    "&branchId=" + schedule.getBranch().getId() +
                    "&startDate=" + schedule.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                    "&startTime=" + schedule.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) +
                    "&roomId=" + schedule.getRoom().getId();
        }
        // --- KẾT THÚC ĐOẠN SỬA ĐỔI ---

        // GIỮ LẠI: Logic xử lý khi đã có ghế hợp lệ
        List<Integer> listSeatIds = Arrays.stream(seats)
                .map(seat -> Integer.parseInt(seat))
                .collect(Collectors.toList());
        session.setAttribute("listSelectedSeatIds", listSeatIds);

        // Đếm số ghế
        Integer numberOfSelectedSeats = listSeatIds.size();
        model.addAttribute("numberOfSelectedSeats", numberOfSelectedSeats);

        // Lấy ra tổng tiền
        ScheduleDTO scheduleFromSession = (ScheduleDTO) session.getAttribute("schedule");
        Double totalAmount = scheduleFromSession.getPrice() * numberOfSelectedSeats;
        model.addAttribute("totalAmount", totalAmount);

        // Format lại ngày
        model.addAttribute("formattedDate",
                scheduleFromSession.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        model.addAttribute("user", new User());

        // Xóa lỗi cũ nếu có để tránh hiển thị nhầm khi dữ liệu đã đúng
        session.removeAttribute("bookedError");

        return "bill";
    }
}
