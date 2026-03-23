//package Session10.Bai1;
//
//Phần 1 – Phân tích
//
//Đoạn code hiện tại mỗi lần gọi getHospitalConn() đều tạo kết nối mới nhưng không bao giờ đóng lại. Điều này cực kỳ nguy hiểm, đặc biệt với hệ thống bệnh viện cần chạy 24/7:
//
//Rò rỉ kết nối (Connection leak)
//Mỗi connection chiếm tài nguyên (RAM + socket). Không đóng → tích lũy dần → hết tài nguyên.
//Giới hạn số connection của MySQL
//Database chỉ cho phép số lượng kết nối tối đa. Khi vượt ngưỡng → lỗi
// → Communications link failure
//Hệ thống bị treo sau vài tiếng
//Ban đầu chạy bình thường → càng lâu càng nhiều connection chưa đóng → cuối cùng:
//Không mở được connection mới
//Query bị treo
//App “đơ”
//Không kiểm soát trạng thái connection
//Hardcode IP + không check kết nối còn sống hay không → dễ gây lỗi khi mạng chập chờn
//Không phù hợp hệ thống y tế
//Bệnh viện yêu cầu:
//ổn định
//không downtime
// → lỗi kiểu này có thể ảnh hưởng trực tiếp đến việc truy xuất hồ sơ bệnh nhân
